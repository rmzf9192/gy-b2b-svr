package com.el.edp.bpm.service;

import com.el.core.DevError;
import com.el.edp.bpm.EdpBpmTaskService;
import com.el.edp.bpm.domain.EdpBpmTaskPayload;
import com.el.edp.bpm.domain.EdpBpmTrace;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class EdpBpmSimpleExamService implements EdpBpmExamService {

    private final EdpBpmTaskService bpmTaskService;

    private final EdpBpmTraceService bpmTraceService;

    @Override
    public EdpBpmTask examine(EdpBpmTaskPayload payload) {
        return Optional.ofNullable(bpmTaskService.taskBy(payload.getTaskId())).map(task -> {
            // 处理人策略
            if (payload.getAssignee().equals(task.getAssignee())) {
                examineCompleted(payload, task);
            }
            // 候选人策略
            else if (candidateContains(task.getId(), payload.getAssignee())) {
                bpmTaskService.claim(task.getId(), payload.getAssignee());
                examineCompleted(payload, task);
            }
            else {
                throw DevError.unexpected("EDP-BPM] no correct assignee.");
            }
            return task;
        }).orElseThrow(() -> DevError.unexpected("EDP-BPM] flow examine failed."));
    }

    /**
     * @param payload 审批对象
     * @param task 任务
     */
    private void examineCompleted(EdpBpmTaskPayload payload, EdpBpmTask task) {
        // 审批前置处理
        beforeTaskCompleted(payload, task);
        // 执行审批
        payload.setTaskKey(task.getKey());
        bpmTaskService.examineApproved(payload);
        // 新增审批任务履历
        bpmTraceService.addBpmTrace(EdpBpmTrace.of(payload, task));
    }

    /**
     * @param taskId 任务编号
     * @param candidate 候选人
     * @return 任务是否包含指定候选人？
     */
    private boolean candidateContains(String taskId, String candidate) {
        return bpmTaskService.candidatesOf(taskId).contains(candidate);
    }
}
