package com.el.edp.udc.service;

import com.el.core.cache.CacheName;
import com.el.core.udc.UdcOpService;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import com.el.edp.udc.mapper.EdpUdcMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/11
 */
@Profile("edp")
@Slf4j
@CacheName("UDC")
@RequiredArgsConstructor
public class EdpUdcService implements UdcOpService<EdpUdc, EdpUdcItem> {

    private final EdpUdcMapper udcMapper;

    @Override
    public void storeUdc(EdpUdc udc, List<EdpUdcItem> udcItems) {
        throw new UnsupportedOperationException("[EDP-UDC] UDC ops is unsupported.");
    }

    @Override
    public void evictAll() {
        log.info("[EDP-UDC] evict all");
    }

    @Override
    public List<EdpUdcItem> udcItems(EdpUdc udc) {
        log.trace("[DEMO-UDC] fetch udc: {}", udc.getCode());
        return udcMapper.udcItemsOf(udc);
    }

}
