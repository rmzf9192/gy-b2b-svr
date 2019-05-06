package com.el.cfg.jdbc;

import com.el.core.DevError;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author neo.pan
 * @since 2018/03/26
 */
@Slf4j
public abstract class SqlInjector {

    private static final Set<String> fromFollowedStmts = new HashSet<>(Arrays.asList(
        "WHERE", "INNER", "LEFT", "RIGHT", "GROUP", "ORDER", "UNION"));

    private static final Pattern wordStmt = Pattern.compile("\\w+");
    private static final Pattern subqStmt = Pattern.compile(",\\s*\\(\\s*SELECT\\s");
    private static final Pattern fromStmt = Pattern.compile("\\sFROM\\s+(\\w+|\\()");
    private static final Pattern joinStmt = Pattern.compile("\\sJOIN\\s+(\\w+|\\()");
    private static final Pattern joinAliasStmt = Pattern.compile("\\s(\\w+)\\s+ON\\s");
    private static final Pattern whereStmt = Pattern.compile("\\sWHERE\\s");
    private static final Pattern aggStmts = Pattern.compile("\\s(ORDER|GROUP)\\s");

    private static final Pattern valuesStmt = Pattern.compile("\\)\\s*VALUES\\s*\\(");

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";

    /**
     * 将指定的过滤条件插入到SQL(SELECT)中
     *
     * @param originalSql   原来的SQL
     * @param sqlFilter     过滤条件
     * @param isTenantTable 传入表名(大写)判断该表是否需要增加过滤条件
     * @return 加工后的SQL
     */
    public static String resolveSelectSql(
        final String originalSql, final String sqlFilter, final Function<String, Boolean> isTenantTable) {

        val sqlToParse = originalSql.toUpperCase();
        int parseAt = 0; // 解析走到的位置

        String whereFilter = null;

        val sb = new StringBuilder();

        // find `FROM` table or subquery
        Matcher tm = fromStmt.matcher(sqlToParse);
        if (!tm.find()) {
            throw DevError.unexpected("[EDP-JDBC] `FROM` is not found: " + originalSql);
        }

        // find subquerys before `FROM`
        Matcher matcher = subqStmt.matcher(sqlToParse);
        while (matcher.find() && matcher.end() < tm.start()) {
            int endAt = matcher.end() - "SELECT ".length(); // 'S'
            sb.append(originalSql.substring(parseAt, endAt));
            parseAt = endAt;
            endAt = endOfSubquery(sqlToParse, matcher.end()); // ')'
            val subQuerySql = originalSql.substring(parseAt, endAt);
            sb.append(resolveSelectSql(subQuerySql, sqlFilter, isTenantTable));
            parseAt = endAt;
            if (!tm.find(parseAt)) {
                throw DevError.unexpected("[EDP-JDBC] `FROM` is not found: " + originalSql);
            }
        }

        // find `FROM` table or subquery
        matcher = tm;
        sb.append(originalSql.substring(parseAt, matcher.end()));
        parseAt = matcher.end(); // '(_'
        val fromTable = matcher.group(1);
        if (fromTable.charAt(0) == '(') { // subquery
            val endAt = endOfSubquery(sqlToParse, parseAt); // ')'
            if (endAt == -1) {
                throw DevError.unexpected("[EDP-JDBC] Subquery in `FROM` is broken: "
                    + originalSql.substring(parseAt));
            }
            val subQuerySql = originalSql.substring(parseAt, endAt);
            sb.append(resolveSelectSql(subQuerySql, sqlFilter, isTenantTable));
            parseAt = endAt;
        } else { // table
            if (!isTenantTable.apply(fromTable)) {
                throw new JdbcSelfServicedException();
            }
            whereFilter = sqlFilter;
            // find `FROM` table alias
            matcher = wordStmt.matcher(sqlToParse);
            if (matcher.find(parseAt)) {
                if (!fromFollowedStmts.contains(matcher.group())) { // valid alias
                    val fromAlias = originalSql.substring(matcher.start(), matcher.end());
                    whereFilter = fromAlias + "." + whereFilter;
                    sb.append(originalSql.substring(parseAt, matcher.end()));
                    parseAt = matcher.end();
                }
            }
        }

        // find `JOIN ... ON` table and alias
        val joinMatcher = joinStmt.matcher(sqlToParse);
        if (joinMatcher.find(parseAt)) {
            do {
                String joinTable = joinMatcher.group(1);
                sb.append(originalSql.substring(parseAt, joinMatcher.end()));
                parseAt = joinMatcher.end(); // '(_'
                if (joinTable.charAt(0) == '(') { // subquery
                    val endAt = endOfSubquery(sqlToParse, parseAt); // ')'
                    if (endAt == -1) {
                        throw DevError.unexpected("[EDP-JDBC] Subquery in `JOIN` is broken: "
                            + originalSql.substring(parseAt));
                    }
                    val subQuerySql = originalSql.substring(parseAt, endAt);
                    sb.append(resolveSelectSql(subQuerySql, sqlFilter, isTenantTable));
                    parseAt = endAt;
                } else { // table
                    if (isTenantTable.apply(joinTable)) {
                        matcher = joinAliasStmt.matcher(sqlToParse);
                        if (matcher.find(parseAt)) {
                            val joinAlias = originalSql.substring(matcher.start(1), matcher.end(1));
                            sb.append(originalSql.substring(parseAt, matcher.end()));
                            sb.append(joinAlias).append('.').append(sqlFilter).append(SQL_AND);
                            parseAt = matcher.end();
                        }
                    }
                }
            } while (joinMatcher.find(parseAt));
        }

        // find `WHERE`
        if (whereFilter != null) {
            matcher = whereStmt.matcher(sqlToParse);
            if (matcher.find(parseAt)) {
                sb.append(originalSql.substring(parseAt, matcher.end()));
                sb.append(whereFilter).append(SQL_AND);
                sb.append(originalSql.substring(matcher.end()));
            } else {
                matcher = aggStmts.matcher(sqlToParse);
                if (matcher.find(parseAt)) {
                    sb.append(originalSql.substring(parseAt, matcher.start()));
                    sb.append(SQL_WHERE).append(whereFilter);
                    sb.append(originalSql.substring(matcher.start()));
                } else {
                    sb.append(originalSql.substring(parseAt));
                    sb.append(SQL_WHERE).append(whereFilter);
                }
            }
        } else {
            sb.append(originalSql.substring(parseAt));
        }

        return sb.toString();
    }

    private static int endOfSubquery(String sql, int start) {
        char ch;
        int depth = 1; // subquery nest depth
        for (int i = start, n = sql.length(); i < n; i++) {
            ch = sql.charAt(i);
            if (ch == '(') {
                ++depth;
            } else if (ch == ')') {
                if (--depth == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /*---------------+
     |   增、删、改   |
     +==============*/

    private static final Pattern insertStmt = Pattern.compile("\\sINTO\\s+(\\w+)");
    private static final Pattern updateStmt = Pattern.compile("\\s?UPDATE\\s+(\\w+)");
    private static final Pattern deleteStmt = Pattern.compile("\\sFROM\\s+(\\w+)");

    private static JdbcParsedTableInfo parseTable(
        String originalSql, String sqlToParse, Pattern tableStmt) {
        val matcher = tableStmt.matcher(sqlToParse);
        if (!matcher.find()) {
            throw DevError.unexpected("[EDP-JDBC] `FROM` is not found: " + originalSql);
        }
        return JdbcParsedTableInfo.of(matcher.group(1), matcher.end());
    }

    public static String resolveDeleteSql(
        String originalSql, String sqlFilter, UnaryOperator<JdbcParsedTableInfo> resolveTableInfo) {

        val sqlToParse = originalSql.toUpperCase();
        val parsedTable = resolveTableInfo.apply(parseTable(originalSql, sqlToParse, deleteStmt));

        val matcher = whereStmt.matcher(sqlToParse);
        if (matcher.find(parsedTable.getParseAt())) {
            return originalSql + SQL_AND + sqlFilter;
        } else {
            return originalSql + SQL_WHERE + sqlFilter;
        }
    }

    public static String resolveUpdateSql(
        String originalSql, String sqlFilter, Map<String, SqlExpr> sqlValues,
        UnaryOperator<JdbcParsedTableInfo> resolveTableInfo) {

        val sqlToParse = originalSql.toUpperCase();
        val parsedTable = resolveTableInfo.apply(parseTable(originalSql, sqlToParse, updateStmt));

        val kvs = sqlValues.entrySet();
        ArrayList<String> sqlSetters = new ArrayList<>(kvs.size());
        for (val kv : kvs) {
            sqlSetters.add(kv.getKey() + " = " + kv.getValue().get());
        }

        StringBuilder sb = new StringBuilder();
        val matcher = whereStmt.matcher(sqlToParse);
        if (matcher.find(parsedTable.getParseAt())) {
            sb.append(originalSql.substring(0, matcher.start()));
            joinSqlStmts(sb, sqlSetters);
            sb.append(originalSql.substring(matcher.start()));
            sb.append(SQL_AND).append(sqlFilter);
        } else {
            sb.append(originalSql);
            joinSqlStmts(sb, sqlSetters);
            sb.append(SQL_WHERE).append(sqlFilter);
        }
        return sb.toString();
    }

    public static String resolveInsertSql(
        String originalSql, Map<String, SqlExpr> sqlValues, UnaryOperator<JdbcParsedTableInfo> resolveTableInfo) {

        val sqlToParse = originalSql.toUpperCase();
        val parsedTable = resolveTableInfo.apply(parseTable(originalSql, sqlToParse, insertStmt));
        val matcher = valuesStmt.matcher(sqlToParse);
        if (!matcher.find(parsedTable.getParseAt())) {
            throw DevError.unexpected("[EDP-JDBC] Invalid insert sql statement: " + originalSql);
        }

        int lastValueAt = sqlToParse.lastIndexOf(')');
        if (lastValueAt == -1 || lastValueAt < matcher.end()) {
            throw DevError.unexpected("[EDP-JDBC] Invalid insert sql statement: " + originalSql);
        }

        val kvs = sqlValues.entrySet();
        ArrayList<String> cols = new ArrayList<>(kvs.size());
        ArrayList<String> vals = new ArrayList<>(kvs.size());
        for (val kv : kvs) {
            cols.add(kv.getKey());
            vals.add(kv.getValue().get());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(originalSql.substring(0, matcher.start()));
        joinSqlStmts(sb, cols);
        sb.append(originalSql.substring(matcher.start(), lastValueAt));
        joinSqlStmts(sb, vals);
        sb.append(originalSql.substring(lastValueAt));
        return sb.toString();
    }

    private static void joinSqlStmts(StringBuilder sb, Collection<String> sqlStmts) {
        for (String sqlStmt : sqlStmts) {
            sb.append(", ").append(sqlStmt);
        }
    }

}
