package com.el.cfg.jdbc;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.List;
import java.util.Properties;

/**
 * @author neo.pan
 * @since 2018/03/26
 */
@Slf4j
public abstract class JdbcCommandInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        val executor = (Executor) invocation.getTarget();
        val args = invocation.getArgs();
        val ms = (MappedStatement) args[0];
        val parameter = args[1];
        try {
            val msSqlInjected = resolveMappedStatement(ms, parameter);
            return executor.update(msSqlInjected, parameter);
        } catch (JdbcSelfServicedException e) {
            return invocation.proceed();
        }
    }

    private MappedStatement resolveMappedStatement(MappedStatement ms, Object parameter) {
        val configuration = ms.getConfiguration();
        // log.debug("[EDP-JDBC] sqlSource: {}", ms.getSqlSource().getClass()); // RawSqlSource
        val boundSql = ms.getSqlSource().getBoundSql(parameter);
        val mappings = boundSql.getParameterMappings();
        val sqlType = ms.getSqlCommandType();
        val sql = resolveSql(boundSql.getSql(), sqlType, mappings, parameter);
        val sqlSource = new StaticSqlSource(configuration, sql, mappings);
        return new MappedStatement.Builder(configuration, ms.getId(), sqlSource, sqlType)
            .keyGenerator(ms.getKeyGenerator())
            .keyProperty(ms.getKeyProperties() == null
                ? null : String.join(",", ms.getKeyProperties()))
            .build();
    }

    /**
     * 对 SQL 进行加工处理
     *
     * @param originalSql 原来的SQL
     * @param sqlType     SQL命令类型
     * @param mappings    SQL参数映射
     * @param parameter   SQL参数数据
     * @return 加工后的SQL
     */
    protected abstract String resolveSql(
        String originalSql, SqlCommandType sqlType, List<ParameterMapping> mappings, Object parameter);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
