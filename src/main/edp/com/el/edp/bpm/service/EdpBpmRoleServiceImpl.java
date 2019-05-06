package com.el.edp.bpm.service;

import com.el.edp.bpm.domain.EdpBpmTaskDef;
import com.el.edp.bpm.mapper.EdpBpmTaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
@Slf4j
@RequiredArgsConstructor
public class EdpBpmRoleServiceImpl implements EdpBpmRoleService {

    private final EdpBpmTaskMapper bpmTaskMapper;

    @Override
    public boolean checkBpmRoleSettleCorrectly(String defKey) {
        return bpmTaskMapper.checkBpmRoleSettleCorrectly(defKey);
    }

    @Override
    public Map<String, Object> taskRoleMapping(String defKey) {
        val mappings = bpmTaskMapper.taskRoleMapping(defKey);
        return mappings.stream().collect(Collectors.toMap(EdpBpmTaskDef::getKey, EdpBpmTaskDef::getCandidates));
    }

}
