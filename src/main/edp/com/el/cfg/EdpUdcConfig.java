package com.el.cfg;

import com.el.core.udc.*;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import com.el.edp.udc.mapper.EdpUdcMapper;
import com.el.edp.udc.service.EdpUdcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/5/10
 */
@Profile("edp")
@Slf4j
@Configuration
public class EdpUdcConfig {

    @Bean
    public UdcManager<EdpUdc> udcManager(final EdpUdcMapper udcMapper) {
        log.info("[EDP-UDC] udcManager");
        return SimpleUdcManager.of(udcMapper.udcs());
    }

    @Bean
    public UdcOpService<EdpUdc, EdpUdcItem> udcService(final EdpUdcMapper udcMapper) {
        log.info("[EDP-UDC] udcService");
        return new EdpUdcService(udcMapper);
    }

    @Bean
    public UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver(
        UdcService<EdpUdc, EdpUdcItem> udcService, UdcResolver<EdpUdc> udcResolver) {
        log.info("[CORE-UDC] udcNameResolver");
        return new UdcNameResolver<>(udcService, udcResolver);
    }

}
