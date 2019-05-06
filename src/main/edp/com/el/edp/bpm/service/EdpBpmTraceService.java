package com.el.edp.bpm.service;

import com.el.edp.bpm.EdpBpmTaskService;
import com.el.edp.bpm.domain.EdpBpmTrace;
import com.el.edp.bpm.mapper.EdpBpmTraceMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Simon.Hu
 * @since 2018/04/24
 */
@RequiredArgsConstructor
public class EdpBpmTraceService {

    private final EdpBpmTraceMapper bpmTraceMapper;

    private final EdpBpmTaskService bpmTaskService;

    /**
     * 新增任务履历
     * @param trace 任务履历
     */
    public void addBpmTrace(EdpBpmTrace trace) {
        val total = bpmTraceMapper.updateBpmTrace(trace);
        if (total == 0) {
            trace.setEndTime(LocalDateTime.now());
            bpmTraceMapper.insertBpmTrace(trace);
        }

        bpmTaskService.activeTasks(trace.getPrcId()).stream()
            .filter(task -> bpmTraceMapper.newBpmTrace(task.getId()))
            .forEach(task -> bpmTraceMapper.insertBpmTrace(EdpBpmTrace.of(task)));
    }

    /**
     * @param prcId 流程实例编号
     * @return 流程实例履历
     */
    public List<EdpBpmTrace> tracesOf(String prcId) {
        val traces = bpmTraceMapper.tracesOf(prcId);
        return traces.stream().peek(trace ->
            Optional.ofNullable(bpmTaskService.histTaskBy(trace.getTaskId())).ifPresent(tsk -> {
                trace.setTaskName(tsk.getName());
                trace.setTaskKey(tsk.getKey());
            })
        ).collect(Collectors.toList());
    }
}
