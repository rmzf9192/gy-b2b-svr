package com.el.cfg.jdbc;

import com.el.core.DevError;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.el.cfg.jdbc.SqlInjector.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/04
 */
@Slf4j
public class SqlInjectorTest {

    private static final String FILTER = "TENANT_ID = 1";
    private static final EdpSqlTenantInfoResolver tenantInfoResolver = new EdpSqlTenantInfoResolver();
    private static final Function<String, Boolean> isTenantTable = tenantInfoResolver::isTenantTable;
    private static final UnaryOperator<JdbcParsedTableInfo> tableInfoResolver = ti -> {
        ti.setTenantId(tenantInfoResolver.tenantId());
        ti.setUserId(0L);
        return ti;
    };

    @Test
    public void regex_find() {
        Pattern p = Pattern.compile("\\w+ (\\w+)");
        Matcher m = p.matcher("hello world Hello World");
        while (m.find()) {
            log.info("{} at {}-{}", m.group(), m.start(), m.end());
            log.info("{} at {}-{}", m.group(1), m.start(1), m.end(1));
        }
    }

    @Test(expected = JdbcSelfServicedException.class)
    public void selfServicedSql() {
        String original = "select PERM_CODE from DUAL where ROLE_ID = #{roleId}";
        resolveSelectSql(original, FILTER, isTenantTable);
    }

    @Test
    public void resolveSelectSql_no_where_no_join() {
        String original = "select ID, NAME from PS_AUTH_USER";
        String expected = "select ID, NAME from PS_AUTH_USER WHERE TENANT_ID = 1";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_no_join() {
        String original = "select ID, NAME from PS_AUTH_USER where DELETE_FLAG=0";
        String expected = "select ID, NAME from PS_AUTH_USER where TENANT_ID = 1 AND DELETE_FLAG=0";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_joins_where() {
        String original = "select ID, NAME from PS_AUTH_USER u" +
            " join PS_OU ou on ou.ID = u.ENT_ID" +
            " where DELETE_FLAG=0";
        String expected = "select ID, NAME from PS_AUTH_USER u" +
            " join PS_OU ou on ou.TENANT_ID = 1 AND ou.ID = u.ENT_ID" +
            " where u.TENANT_ID = 1 AND DELETE_FLAG=0";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_multi_joins_where() {
        String original = "select 1 FROM PB_ITEM T\n" +
            "JOIN PB_ITEM_C1 T1 ON T1.ID = T.C1_CODE\n" +
            "JOIN PB_ITEM_C2 T2 ON T2.ID = T.C2_CODE\n" +
            "JOIN PB_ITEM_C3 T3 ON T3.ID = T.C3_CODE\n" +
            "JOIN SRM_SUPP_ITEM T4 ON T4.ITEM_ID = T.ID\n" +
            "JOIN PB_SUPP T5 ON T5.SUPP_CODE = T4.SUPP_ID\n" +
            "WHERE T.DELETE_FLAG = 0";
        String expected = "select 1 FROM PB_ITEM T\n" +
            "JOIN PB_ITEM_C1 T1 ON T1.TENANT_ID = 1 AND T1.ID = T.C1_CODE\n" +
            "JOIN PB_ITEM_C2 T2 ON T2.TENANT_ID = 1 AND T2.ID = T.C2_CODE\n" +
            "JOIN PB_ITEM_C3 T3 ON T3.TENANT_ID = 1 AND T3.ID = T.C3_CODE\n" +
            "JOIN SRM_SUPP_ITEM T4 ON T4.TENANT_ID = 1 AND T4.ITEM_ID = T.ID\n" +
            "JOIN PB_SUPP T5 ON T5.TENANT_ID = 1 AND T5.SUPP_CODE = T4.SUPP_ID\n" +
            "WHERE T.TENANT_ID = 1 AND T.DELETE_FLAG = 0";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_with_valq() {
        String valq = "select count(1) from SRM_SUPP_ITEM T4\n" +
            "where T4.SUPP_ID = T.SUPP_ID";
        String original = "select 1, (" + valq + ") t4n FROM PB_ITEM T\n" +
            "JOIN PB_ITEM_C1 T1 ON T1.ID = T.C1_CODE\n" +
            "JOIN PB_ITEM_C2 T2 ON T2.ID = T.C2_CODE\n" +
            "JOIN PB_ITEM_C3 T3 ON T3.ID = T.C3_CODE\n" +
            "JOIN SRM_SUPP_ITEM T4 ON T4.ITEM_ID = T.ID\n" +
            "JOIN PB_SUPP T5 ON T5.SUPP_CODE = T4.SUPP_ID\n" +
            "WHERE T.DELETE_FLAG = 0";
        String valqExpected = "select count(1) from SRM_SUPP_ITEM T4\n" +
            "where T4.TENANT_ID = 1 AND T4.SUPP_ID = T.SUPP_ID";
        String expected = "select 1, (" + valqExpected + ") t4n FROM PB_ITEM T\n" +
            "JOIN PB_ITEM_C1 T1 ON T1.TENANT_ID = 1 AND T1.ID = T.C1_CODE\n" +
            "JOIN PB_ITEM_C2 T2 ON T2.TENANT_ID = 1 AND T2.ID = T.C2_CODE\n" +
            "JOIN PB_ITEM_C3 T3 ON T3.TENANT_ID = 1 AND T3.ID = T.C3_CODE\n" +
            "JOIN SRM_SUPP_ITEM T4 ON T4.TENANT_ID = 1 AND T4.ITEM_ID = T.ID\n" +
            "JOIN PB_SUPP T5 ON T5.TENANT_ID = 1 AND T5.SUPP_CODE = T4.SUPP_ID\n" +
            "WHERE T.TENANT_ID = 1 AND T.DELETE_FLAG = 0";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_joins_only() {
        String original = "SELECT 1 FROM SRM_SUPP_PERFORMANCE P JOIN PB_SUPP S ON S.SUPP_CODE = P.SUPP_ID";
        String expected = "SELECT 1 FROM SRM_SUPP_PERFORMANCE P JOIN PB_SUPP S ON S.TENANT_ID = 1 AND S.SUPP_CODE = P.SUPP_ID WHERE P.TENANT_ID = 1";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_joins_and_orderby() {
        String original = "SELECT 1 FROM SRM_SUPP_PERFORMANCE P JOIN PB_SUPP S ON S.SUPP_CODE = P.SUPP_ID ORDER BY P.CTG";
        String expected = "SELECT 1 FROM SRM_SUPP_PERFORMANCE P JOIN PB_SUPP S ON S.TENANT_ID = 1 AND S.SUPP_CODE = P.SUPP_ID WHERE P.TENANT_ID = 1 ORDER BY P.CTG";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_from_subquery() {
        String original = "select ID, NAME from (" +
            "select ID, NAME from PS_AUTH_USER) t where t.DELETE_FLAG=0";
        String expected = "select ID, NAME from (" +
            "select ID, NAME from PS_AUTH_USER WHERE TENANT_ID = 1) t where t.DELETE_FLAG=0";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_with_paging() {
        String original = "select * from ( select all_.*, rownum rn_ from ( \n"
            + "SELECT 1 FROM SRM_SUPP_PERFORMANCE P JOIN PB_SUPP S ON S.SUPP_CODE = P.SUPP_ID\n"
            + ") all_ where rownum <= 20 ) where rn_ >= 1";
        String actual = resolveSelectSql(original, FILTER, isTenantTable);
        log.info(actual);
    }

    @Test(expected = DevError.class)
    public void resolveSelectSql_from_subquery_bad() {
        String original = "select ID, NAME from ((" +
            "select ID, NAME from PS_AUTH_USER) t where t.DELETE_FLAG=0";
        resolveSelectSql(original, FILTER, isTenantTable);
    }

    @Test
    public void resolveInsertSql_all_type_values() {
        LinkedHashMap<String, SqlExpr> sqlValues = new LinkedHashMap<String, SqlExpr>() {{
            put(" UNDEFINED", SqlExpr.nil);
            put("CREATED_AT", SqlExpr.now);
            put("CREATED_BY", SqlExpr.num(8080L));
            put("EVIL_VALUE", SqlExpr.str("I'm evil;"));
        }};
        String original = "insert into PS_AUTH_USER (ID, NAME) values (1, 'Neo')";
        String expected = "insert into PS_AUTH_USER (ID, NAME,  UNDEFINED, CREATED_AT, CREATED_BY, EVIL_VALUE)" +
            " values (1, 'Neo', NULL, CURRENT_DATE, 8080, 'I''m evil;')";
        String actual = resolveInsertSql(original, sqlValues, tableInfoResolver);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = DevError.class)
    public void resolveInsertSql_no_values() {
        String original = "insert into PS_AUTH_USER (ID, NAME) value (1, 'Neo')";
        resolveInsertSql(original, Collections.emptyMap(), tableInfoResolver);
    }

    @Test(expected = DevError.class)
    public void resolveInsertSql_bad_values() {
        String original = "insert into PS_AUTH_USER (ID, NAME) value (1, 'Neo'";
        resolveInsertSql(original, Collections.emptyMap(), tableInfoResolver);
    }

    @Test
    public void resolveUpdateSql_all_type_values() {
        LinkedHashMap<String, SqlExpr> sqlValues = new LinkedHashMap<String, SqlExpr>() {{
            put(" UNDEFINED", SqlExpr.nil);
            put("CREATED_AT", SqlExpr.now);
            put("CREATED_BY", SqlExpr.num(8080L));
            put("EVIL_VALUE", SqlExpr.str("I'm evil;"));
        }};
        String original = "update PS_AUTH_USER set NAME='Neo' where ID=1";
        String expected = "update PS_AUTH_USER set NAME='Neo'" +
            ",  UNDEFINED = NULL, CREATED_AT = CURRENT_DATE, CREATED_BY = 8080" +
            ", EVIL_VALUE = 'I''m evil;' where ID=1 AND TENANT_ID = 1";
        String actual = resolveUpdateSql(original, FILTER, sqlValues, tableInfoResolver);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveUpdateSql_no_where() {
        LinkedHashMap<String, SqlExpr> sqlValues = new LinkedHashMap<String, SqlExpr>() {{
            put(" UNDEFINED", SqlExpr.nil);
            put("CREATED_AT", SqlExpr.now);
            put("CREATED_BY", SqlExpr.num(8080L));
            put("EVIL_VALUE", SqlExpr.str("I'm evil;"));
        }};
        String original = "update PS_AUTH_USER set NAME='Neo', ID=1";
        String expected = original +
            ",  UNDEFINED = NULL, CREATED_AT = CURRENT_DATE, CREATED_BY = 8080" +
            ", EVIL_VALUE = 'I''m evil;' WHERE TENANT_ID = 1";
        String actual = resolveUpdateSql(original, FILTER, sqlValues, tableInfoResolver);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveDeleteSql_with_filter() {
        String original = "delete from PS_AUTH_USER where ID=1";
        String expected = original + " AND " + FILTER;
        String actual = resolveDeleteSql(original, FILTER, tableInfoResolver);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveDeleteSql_without_filter() {
        String original = "delete from PS_AUTH_USER";
        String expected = original + " WHERE " + FILTER;
        String actual = resolveDeleteSql(original, FILTER, tableInfoResolver);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_join_non_tenant_table() {
        String sql = "SELECT t.KEY key" +
            ", listagg(ur.USER_ID, ',') within group(order by ur.USER_ID) candidates\n" +
            "FROM PS_FLOW_TASK t JOIN PS_AUTH_USER_ROLE ur on ur.ROLE_ID = t.ROLE_ID\n" +
            "WHERE (t.SETTLED = 'Y') AND (t.DEF_KEY = ?)\n" +
            "GROUP BY t.KEY";
        String expected = "SELECT t.KEY key, listagg(ur.USER_ID, ',') within group(order by ur.USER_ID) candidates\n" +
            "FROM PS_FLOW_TASK t JOIN PS_AUTH_USER_ROLE ur on ur.ROLE_ID = t.ROLE_ID\n" +
            "WHERE t.TENANT_ID = 1 AND (t.SETTLED = 'Y') AND (t.DEF_KEY = ?)\n" +
            "GROUP BY t.KEY";
        String actual = resolveSelectSql(sql, FILTER, t -> !t.equals("PS_AUTH_USER_ROLE"));
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_with_paging_and_subquery_but_no_where() {
        String sql = "select * from ( select all_.*, rownum rn_ from (" +
            "select u.ID id, (select sum(t.QTY_SUM) from SRM_PO t\n" +
            "where t.OU_ID = u.OU_ID) a from SRM_CONTRACT u\n" +
            "PB_OU t1 ON u.OU_ID = t1.ID AND t1.DELETE_FLAG = 0\n" +
            "PB_SUPP t2 ON u.SUPP_ID = t2.ID AND t2.DELETE_FLAG = 0\n" +
            ") all_ where rownum <= 20 ) where rn_ >= 1";
        String expected = "select * from ( select all_.*, rownum rn_ from (" +
            "select u.ID id, (select sum(t.QTY_SUM) from SRM_PO t\n" +
            "where t.TENANT_ID = 1 AND t.OU_ID = u.OU_ID) a from SRM_CONTRACT u\n" +
            "PB_OU t1 ON u.OU_ID = t1.ID AND t1.DELETE_FLAG = 0\n" +
            "PB_SUPP t2 ON u.SUPP_ID = t2.ID AND t2.DELETE_FLAG = 0\n" +
            " WHERE u.TENANT_ID = 1" +
            ") all_ where rownum <= 20 ) where rn_ >= 1";
        String actual = resolveSelectSql(sql, FILTER, t -> true);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void resolveSelectSql_with_join_subquery() {
        String sql = "SELECT SG.SCORE_GRADE scoreGrade\n" +
            "  FROM PB_SUPP T\n" +
            "  JOIN (\n" +
            "    SELECT * FROM (\n" +
            "      SELECT SCORE_GRADE FROM SRM_SUPP_PERFORMANCE WHERE SCORE_GRADE IS NOT NULL ORDER BY YEAR_MONTH DESC\n" +
            "    ) WHERE ROWNUM = 1\n" +
            "  ) SG ON SG.SUPP_ID = T . ID\n" +
            "WHERE ((T.DELETE_FLAG <> 1 OR T .DELETE_FLAG IS NULL) AND (SA.DELETE_FLAG <> 1 OR SA.DELETE_FLAG IS NULL))";
        String actual = resolveSelectSql(sql, FILTER, t -> true);
        log.info(actual);
    }
}
