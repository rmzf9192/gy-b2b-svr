package com.el.edp.bpm.domain.model;

import lombok.Data;

/**
 * 流程表单视图配置索引
 *
 * @author neo.pan
 * @since 2018/04/26
 */
@Data
public class EdpBpmViewKey {

    /**
     * 流程定义ID
     */
    private String defId;
    /**
     * 任务定义ID
     */
    private String nodeId;
    /**
     * 视图模式
     */
    private EdpBpmViewMode mode;

}
