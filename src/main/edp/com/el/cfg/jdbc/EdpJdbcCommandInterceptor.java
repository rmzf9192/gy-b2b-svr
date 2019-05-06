package com.el.cfg.jdbc;

import com.el.core.DevError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.el.cfg.jdbc.SqlInjector.*;

/**
 * @author neo.pan
 * @since 2018/03/26
 */
@Slf4j
@Intercepts(value = {
    @Signature(type = Executor.class, method = "update", args = {
        MappedStatement.class, Object.class
    }),
})
@RequiredArgsConstructor
public class EdpJdbcCommandInterceptor extends JdbcCommandInterceptor {

    private final SqlAuditInfoResolver auditInfoResolver;
    private final SqlTenantInfoResolver tenantInfoResolver;

    private final static String auditCreatedUser = "CREATE_USER_ID";
    private final static String auditCreatedTime = "CREATE_TIME";
    private final static String auditUpdatedUser = "MODIFY_USER_ID";
    private final static String auditUpdatedTime = "MODIFY_TIME";
    private final static String auditDataVersion = "AUDIT_DATA_VERSION";

    private String buildSqlFilter() {
        return tenantInfoResolver.tenantIdColumnName() + " = " + tenantInfoResolver.tenantId();
    }

    private Map<String, SqlExpr> buildSqlValuesForInsert() {
        HashMap<String, SqlExpr> vals = new HashMap<>();
        vals.put(tenantInfoResolver.tenantIdColumnName(), SqlExpr.num(tenantInfoResolver.tenantId()));
        val userId = SqlExpr.num(auditInfoResolver.userId());
        vals.put(auditCreatedUser, userId);
        vals.put(auditCreatedTime, SqlExpr.now);
        vals.put(auditUpdatedUser, userId);
        vals.put(auditUpdatedTime, SqlExpr.now);
        return vals;
    }

    private Map<String, SqlExpr> buildSqlValuesForUpdate() {
        val userId = SqlExpr.num(auditInfoResolver.userId());
        HashMap<String, SqlExpr> vals = new HashMap<>();
        vals.put(auditUpdatedUser, userId);
        vals.put(auditUpdatedTime, SqlExpr.now);
        return vals;
    }

    private JdbcParsedTableInfo resolveTableInfo(JdbcParsedTableInfo ti) {
        val tableName = ti.getTableName();
        if (tenantInfoResolver.isTenantTable(tableName)) {
            ti.setTenantId(tenantInfoResolver.tenantId());
        }
        if (auditInfoResolver.isAuditTable(tableName)) {
            ti.setUserId(auditInfoResolver.userId());
        }
        if (ti.selfServiced()) {
            throw new JdbcSelfServicedException();
        }
        return ti;
    }

    @Override
    protected String resolveSql(
        String originalSql, SqlCommandType sqlType, List<ParameterMapping> mappings, Object parameter) {
        String sql;
        switch (sqlType) {
            case INSERT:
                sql = resolveInsertSql(originalSql, buildSqlValuesForInsert(), this::resolveTableInfo);
                break;
            case UPDATE:
                val revision = revisionOfPayload(originalSql, parameter);
                val filter = buildSqlFilter() + revision.map(
                    r -> " AND " + auditDataVersion + " = " + r).orElse("");
                val vals = buildSqlValuesForUpdate();
                revision.ifPresent(
                    r -> vals.put(auditDataVersion, SqlExpr.sql(auditDataVersion + " + 1")));
                sql = resolveUpdateSql(originalSql, filter, vals, this::resolveTableInfo);
                break;
            case DELETE:
                sql = resolveDeleteSql(originalSql, buildSqlFilter(), this::resolveTableInfo);
                break;
            default:
                throw DevError.unexpected("[EDP-JDBC] Unknown sql command type: " + sqlType);
        }
        log.trace("[EDP-JDBC] Resolved sql statement: {} - {}", sql, parameter);
        return sql;
    }

    private Optional<Long> revisionOfPayload(String originalSql, Object payload) {
        Long revision = null;
        if (payload instanceof JdbcRevisional) {
            val revisionalEntity = (JdbcRevisional) payload;
            if (revisionalEntity.revisional()) {
                revision = revisionalEntity.getRevision();
                if (revision == null || revision < 1L) {
                    throw DevError.unexpected("[EDP-JDBC] Invalid revision number "
                        + revision + " for " + originalSql);
                }
            }
        }
        return Optional.ofNullable(revision);
    }

}
