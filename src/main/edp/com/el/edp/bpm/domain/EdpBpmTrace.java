package com.el.edp.bpm.domain;

import com.el.core.util.TimeUtil;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 流程任务定义
 *
 * @author Simon.Hu
 * @since 17/8/11
 */
@Data
public class EdpBpmTrace {
    /**
     * ID
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务定义
     */
    private String taskKey;

    /**
     * 处理人
     */
    private String assignee;

    private String assigneeName;

    /**
     * 任务处理结果
     */
    private EdpBpmTaskResult result;

    /**
     * 流转意见
     */
    private String advice;

    /**
     * 流程实例编号
     */
    private String prcId;

    /**
     * 任务完成时间
     */
    @TimeUtil.TIME
    private LocalDateTime endTime;

    public static EdpBpmTrace of(EdpBpmTaskPayload payload, EdpBpmTask task) {
        EdpBpmTrace trace = new EdpBpmTrace();
        trace.setTaskId(payload.getTaskId());
        trace.setResult(payload.getResult());
        trace.setAdvice(payload.getAdvice());
        trace.setAssignee(payload.getAssignee());
        trace.setTaskName(task.getName());
        trace.setPrcId(task.getPrcId());
        return trace;
    }

    public static EdpBpmTrace of(EdpBpmTask task) {
        EdpBpmTrace trace = new EdpBpmTrace();
        trace.setPrcId(task.getPrcId());
        trace.setTaskId(task.getId());
        return trace;
    }
}
