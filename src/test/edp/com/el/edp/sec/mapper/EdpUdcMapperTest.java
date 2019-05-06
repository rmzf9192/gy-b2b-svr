package com.el.edp.sec.mapper;

import com.el.edp.EdpTest;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import com.el.edp.udc.mapper.EdpUdcMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author neo.pan
 * @since 2018/04/03
 */
@Slf4j
public class EdpUdcMapperTest extends EdpTest {

    @Autowired
    private EdpUdcMapper udcMapper;

    private static EdpUdc udc = EdpUdc.of("SUPP", "SUPP_STATUS");

    private static EdpUdcItem item = EdpUdcItem.of("TEST");

    @Test
    public void udcs() {
        log.info("[TEST] udcs={}", udcMapper.udcs());
    }

    @Test
    public void udcOf() {
        log.info("[TEST] udcOf(1)={}", udcMapper.udcOf(1L));
    }

    @Test
    public void udcItemsOf() {
        log.info("[TEST] udcItemsOf={}", udcMapper.udcItemsOf(udc));
    }

}
