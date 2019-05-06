package com.el.edp;

import com.el.cfg.EdpJdbcConfig;
import com.el.cfg.jdbc.EdpSqlAuditInfoResolver;
import com.el.cfg.jdbc.SqlAuditInfoResolver;
import com.el.core.jdbc.JdbcConfig;
import com.el.core.jdbc.dialect.SqlDialectOracle;
import com.el.core.util.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neo.pan
 * @since 2018/3/22
 */
@ContextConfiguration(classes = {EdpTest.Config.class, EdpJdbcConfig.class, JdbcConfig.class})
@Transactional
@Rollback
public abstract class EdpTest extends EdpSpringTest {

    static {
        SqlUtil.SQL_DIALECT = new SqlDialectOracle();
    }

    @Slf4j
    @Configuration
    public static class Config {
        @Bean
        public SqlAuditInfoResolver auditInfoResolver() {
            log.info("[EDP-TEST] auditInfoResolver");
            return EdpSqlAuditInfoResolver.of(() -> 0L);
        }
    }

}
