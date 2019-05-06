package com.el.edp.bpm.service;

import com.el.edp.bpm.api.EdpBpmConfPayload;
import com.el.edp.bpm.domain.model.EdpBpmViewSts;

import java.io.IOException;

/**
 * 流程表单信息服务
 *
 * @author neo.pan
 * @since 2018/04/23
 */
public interface EdpBpmFormService extends EdpBpmFormRuntimeService {

    /**
     * @param payload 更新信息
     */
    //@CachePointEvict("[0]-[1]-[2]")
    void updateViewConfOf(EdpBpmConfPayload payload);

    /**
     * @param procDefId 流程定义ID
     * @return 流程视图配置状况
     * @throws IOException IO异常发生
     */
    EdpBpmViewSts viewConfStatusOf(String procDefId) throws IOException;

}
