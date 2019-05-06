package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.api.runtime.EdpBpmInstanceQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 18/4/8
 */
@Slf4j
public class EdpBpmInstanceMapperTest extends EdpTest {

    @Autowired
    private EdpBpmInstanceMapper flowInstanceMapper;

    private EdpBpmInstanceQuery query = new EdpBpmInstanceQuery() {{
        setDefId("test");
        setDefName("test");
        setState("ACTIVE");
    }};

    @Test
    public void instancesBy() throws Exception {
        log.info("[TEST] instancesBy={}", flowInstanceMapper.instancesBy(query));
    }

    @Test
    public void instanceCountBy() throws Exception {
        log.info("[TEST] instanceCountBy={}", flowInstanceMapper.instanceCountBy(query));
    }
}
