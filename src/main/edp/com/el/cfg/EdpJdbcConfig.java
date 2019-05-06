package com.el.cfg;

import com.el.cfg.jdbc.*;
import com.el.cfg.security.EdpPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/8/7
 */
@Profile("edp")
@Slf4j
@Configuration
@MapperScan({
    "com.el.edp.*.mapper",
    "com.el.service.*.mapper",
})
public class EdpJdbcConfig {

    @ConditionalOnProperty(value = "jdbc.tenancy", matchIfMissing = true)
    @Bean
    public EdpSqlTenantInfoResolver tenantResolver() {
        log.info("[EDP-JDBC] tenantResolver");
        return new EdpSqlTenantInfoResolver();
    }

    @ConditionalOnProperty(value = "jdbc.tenancy", matchIfMissing = true)
    @Bean
    public EdpJdbcQueryInterceptor queryInterceptor(SqlTenantInfoResolver tenantInfoResolver) {
        log.info("[EDP-JDBC] queryInterceptor");
        return new EdpJdbcQueryInterceptor(tenantInfoResolver);
    }

    @ConditionalOnProperty(value = "jdbc.auditor", matchIfMissing = true)
    @ConditionalOnMissingBean(SqlAuditInfoResolver.class)
    @Bean
    public SqlAuditInfoResolver auditInfoResolver(EdpPrincipalService principalService) {
        log.info("[EDP-JDBC] auditInfoResolver");
        return EdpSqlAuditInfoResolver.of(principalService::userId);
    }

    @ConditionalOnProperty(value = "jdbc.auditor", matchIfMissing = true)
    @Bean
    public EdpJdbcCommandInterceptor commandInterceptor(
        SqlTenantInfoResolver tenantInfoResolver, SqlAuditInfoResolver auditInfoResolver) {
        log.info("[EDP-JDBC] commandInterceptor");
        return new EdpJdbcCommandInterceptor(auditInfoResolver, tenantInfoResolver);
    }

}
