package com.el.edp.bpm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Simon.Hu
 * @since 17/8/3
 */
@Data
public class EdpBpmTaskPayload {
    /**
     * 任务编号
     */
    private String taskId;

    /**
     * 任务定义
     */
    @JsonIgnore
    private String taskKey;

    /**
     * 任务名称
     */
    @JsonIgnore
    private String taskName;

    /**
     * 处理人
     */
    private String assignee;

    /**
     * 任务处理结果
     */
    private EdpBpmTaskResult result;

    /**
     * 流转意见
     */
    private String advice;

    public static EdpBpmTaskPayload of(String taskId, String assignee, EdpBpmTaskResult result) {
        EdpBpmTaskPayload exam = new EdpBpmTaskPayload();
        exam.setTaskId(taskId);
        exam.setAssignee(assignee);
        exam.setResult(result);
        return exam;
    }

    public EdpBpmTaskPayload assigneeOf(String assignee) {
        setAssignee(assignee);
        return this;
    }
}
