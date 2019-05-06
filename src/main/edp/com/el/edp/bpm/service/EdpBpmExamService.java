package com.el.edp.bpm.service;

import com.el.edp.bpm.domain.EdpBpmTaskPayload;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import org.springframework.transaction.annotation.Transactional;

public interface EdpBpmExamService {

    /**
     * 执行审批
     * @param payload 审批对象
     */
    @Transactional
    EdpBpmTask examine(EdpBpmTaskPayload payload);

    /**
     * 审批前置处理
     * @param payload 审批对象
     * @param task 任务
     */
    default void beforeTaskCompleted(EdpBpmTaskPayload payload, EdpBpmTask task){

    }
}
