package com.el.edp.bpm.config;

import java.util.List;

/**
 * 流程配置提供者
 *
 * @author neo.pan
 * @since 17/6/8
 */
public interface BpmParamsProvider {

    /**
     * @return 流程参数列表
     * @param defId 流程定义ID
     */
    List<BpmParam> params(String defId);
}
