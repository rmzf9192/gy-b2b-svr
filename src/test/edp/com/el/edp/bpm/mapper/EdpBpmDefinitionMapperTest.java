package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.api.runtime.EdpBpmDefinitionQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 18/4/8
 */
@Slf4j
public class EdpBpmDefinitionMapperTest extends EdpTest {

    @Autowired
    private EdpBpmDefinitionMapper flowDefinitionMapper;

    private EdpBpmDefinitionQuery query = new EdpBpmDefinitionQuery() {{
        setDepName("test");
        setDefName("test");
        setSrcName("test");
    }};

    @Test
    public void definitionsBy() throws Exception {
        log.info("[TEST] definitionsBy={}", flowDefinitionMapper.definitionsBy(query));
    }

    @Test
    public void definitionCountBy() throws Exception {
        log.info("[TEST] definitionCountBy={}", flowDefinitionMapper.definitionCountBy(query));
    }
}
