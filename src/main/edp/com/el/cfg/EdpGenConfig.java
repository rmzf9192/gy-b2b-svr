package com.el.cfg;

import com.el.cfg.jdbc.EdpSqlTenantInfoResolver;
import com.el.core.AppProperties;
import com.el.edp.gen.mapper.EdpGenMapper;
import com.el.edp.gen.service.EdpGenManager;
import com.el.edp.gen.service.EdpGenManagerImpl;
import com.el.edp.gen.service.EdpGenService;
import com.el.edp.gen.service.EdpGenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
@Profile("edp")
@Slf4j
@Configuration
public class EdpGenConfig {

    @Bean
    public EdpGenService genService(EdpGenMapper genMapper) {
        log.info("[EDP-GEN] genService");
        return new EdpGenServiceImpl(genMapper);
    }

    @Bean
    public EdpGenManager genManager(
        EdpSqlTenantInfoResolver tenantInfoResolver, AppProperties appProperties, EdpGenService genService) {
        log.info("[EDP-GEN] genManager: t-{}, a-{}", tenantInfoResolver.tenantId(), appProperties.getProgId());
        return new EdpGenManagerImpl(tenantInfoResolver.tenantId(), appProperties.getProgId(), genService);
    }

}
