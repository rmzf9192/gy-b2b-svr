package com.el.cfg;

import com.el.core.jdbc.dialect.SqlDialectMySQL;
import com.el.core.jdbc.handler.PurifyStringTypeHandler;
import com.el.core.jdbc.handler.YesNoFlagTypeHandler;
import com.el.core.util.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/8/7
 */
@Profile("b2b")
@Slf4j
@Configuration
@MapperScan({
    "com.el.b2b.mapper",
    "com.el.b2b.*.mapper",
    "com.el.b2b.*.*.mapper",
})
public class B2bJdbcConfig {

    static {
//        SqlUtil.SQL_DIALECT = new SqlDialectOracle();
        SqlUtil.SQL_DIALECT = new SqlDialectMySQL();
    }

    @Bean
    public PurifyStringTypeHandler purifyStringTypeHandler() {
        log.info("[B2B-JDBC] purifyStringTypeHandler");
        return new PurifyStringTypeHandler();
    }

    @Bean
    public YesNoFlagTypeHandler yesNoFlagTypeHandler() {
        log.info("[B2B-JDBC] yesNoFlagTypeHandler");
        return new YesNoFlagTypeHandler();
    }

}
