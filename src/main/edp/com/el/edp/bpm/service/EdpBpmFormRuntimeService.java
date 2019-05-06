package com.el.edp.bpm.service;

import com.el.edp.bpm.domain.model.EdpBpmViewMode;

/**
 * 流程表单信息服务
 *
 * @author neo.pan
 * @since 2018/04/23
 */
public interface EdpBpmFormRuntimeService {

    /**
     * @param procDefId 流程定义ID
     * @param taskDefId 任务定义ID
     * @param viewMode  视图模式
     * @return 视图配置(JSON)
     */
    //@CachePoint("[0]-[1]-[2]")
    String viewConfOf(String procDefId, String taskDefId, EdpBpmViewMode viewMode);

}
