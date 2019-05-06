package com.el.cfg.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/03/26
 */
@Slf4j
@Intercepts(value = {
    @Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
    }),
    @Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class
    }),
})
@RequiredArgsConstructor
public class EdpJdbcQueryInterceptor extends JdbcQueryInterceptor {

    /**
     * 租客信息获取
     */
    private final SqlTenantInfoResolver tenantInfoResolver;

    private String buildSqlFilter() {
        return tenantInfoResolver.tenantIdColumnName() + " = " + tenantInfoResolver.tenantId();
    }

    @Override
    protected String resolveSql(String originalSql, List<ParameterMapping> mappings, Object parameter) {
        String sqlFilter = buildSqlFilter();
        val sql = SqlInjector.resolveSelectSql(originalSql, sqlFilter, tenantInfoResolver::isTenantTable);
        log.trace("[EDP-JDBC] Resolved sql statement: {} - {}", sql, parameter);
        return sql;
    }

}
