package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.domain.EdpBpmTaskResult;
import com.el.edp.bpm.domain.EdpBpmTrace;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/24
 */
@Slf4j
public class EdpBpmTraceMapperTest extends EdpTest {

    @Autowired
    private EdpBpmTraceMapper flowTraceMapper;

    private EdpBpmTrace trace = new EdpBpmTrace() {{
        setTaskId("1231");
        setPrcId("11111");
        setResult(EdpBpmTaskResult.APPROVED);
        setAdvice("remark");
        setAssignee("1");
    }};

    @Test
    public void insertBpmTrace() {
        log.info("[TEST] insertBpmTrace={}", flowTraceMapper.insertBpmTrace(trace));
    }

    @Test
    public void updateBpmTrace() {
        log.info("[TEST] updateBpmTrace={}", flowTraceMapper.updateBpmTrace(trace));
    }

    @Test
    public void taskTraces() {
        log.info("[TEST] insertTaskTrace={}", flowTraceMapper.tracesOf("125701"));
    }
}
