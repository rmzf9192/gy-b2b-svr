package com.el.edp.sec.mapper;

import com.el.edp.EdpTest;
import com.el.edp.sec.domain.EdpAuthLayer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Slf4j
public class EdpAuthMapperTest extends EdpTest {

    @Autowired
    private EdpAuthMapper authMapper;

    @Test
    public void userBy() {
        log.info("[TEST] userBy={}", authMapper.userBy("0"));
    }

    @Test
    public void menusOf() {
        log.info("[TEST] menusOf={}", authMapper.menusOf(EdpAuthLayer.E, Collections.emptyList()));
    }

    //TODO more tests
}
