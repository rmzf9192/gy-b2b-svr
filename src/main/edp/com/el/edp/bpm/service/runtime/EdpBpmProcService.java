package com.el.edp.bpm.service.runtime;

import com.el.edp.bpm.domain.runtime.EdpBpmTask;

import java.util.Optional;

/**
 * 流程运行时服务
 *
 * @author neo.pan
 * @since 2018/04/27
 */
public interface EdpBpmProcService {

    /**
     * 获取当前流程的活动任务(如果有两个以上则取其中一个)
     *
     * @param procId   流程实例ID
     * @param assignee 处理人
     * @return 任务信息
     */
    Optional<EdpBpmTask> activeTaskOf(String procId, String assignee);

}
