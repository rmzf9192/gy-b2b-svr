package com.el.edp.bpm.domain;

import lombok.Data;

/**
 * 流程任务定义
 *
 * @author Simon.Hu
 * @since 17/8/11
 */
@Data
public class EdpBpmTaskDef {
    /**
     * 任务定义
     */
    private String key;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 候选人集合 eg.(1,2,3)
     */
    private String candidates;
}
