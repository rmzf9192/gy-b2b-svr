package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.domain.EdpBpmTaskDef;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Slf4j
public class EdpBpmTaskMapperTest extends EdpTest {

    @Autowired
    private EdpBpmTaskMapper taskMapper;

    @Test
    public void taskRoleMapping() {
        val tss = taskMapper.taskRoleMapping("SuppliperPerformance");
        val map = tss.stream().collect(Collectors.toMap(EdpBpmTaskDef::getKey, EdpBpmTaskDef::getCandidates));
        log.info("[TEST] taskRoleMapping={}", map);
    }

    @Test
    public void checkBpmRoleSettleCorrectly() {
        log.info("[TEST] checkBpmRoleSettleCorrectly={}", taskMapper.checkBpmRoleSettleCorrectly("SuppliperPerformance"));
    }
}
