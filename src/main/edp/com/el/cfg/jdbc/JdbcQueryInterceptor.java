package com.el.cfg.jdbc;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * @author neo.pan
 * @since 2018/03/26
 */
@Slf4j
public abstract class JdbcQueryInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        val executor = (Executor) invocation.getTarget();
        val args = invocation.getArgs();
        val ms = (MappedStatement) args[0];
        val parameter = args[1];
        val rowBounds = (RowBounds) args[2];
        val resultHandler = (ResultHandler) args[3];

        CacheKey cacheKey;
        BoundSql boundSql;
        SqlSource sqlSource = ms.getSqlSource();
        //log.debug("[EDP-JDBC] sqlSource: {}", sqlSource.getClass()); // RawSqlSource
        //由于逻辑关系，只会进入一次
        if (args.length == 4) { //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else { //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        val configuration = ms.getConfiguration();
        val mappings = boundSql.getParameterMappings();
        try {
            val sql = resolveSql(boundSql.getSql(), mappings, parameter);
            return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey,
                new BoundSql(configuration, sql, mappings, parameter));
        } catch (JdbcSelfServicedException e) {
            return invocation.proceed();
        }
    }

    /**
     * 对 SQL 进行加工处理
     *
     * @param originalSql 原来的SQL
     * @param mappings    SQL参数映射
     * @param parameter   SQL参数数据
     * @return 加工后的SQL
     */
    protected abstract String resolveSql(String originalSql, List<ParameterMapping> mappings, Object parameter);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
