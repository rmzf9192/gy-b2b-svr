package com.el.edp.bpm.domain;

import lombok.Data;

import java.util.List;

@Data
public class EdpBpmConf {
    /**
     * 流程实例编号
     */
    private String prcId;

    /**
     * 流程定义编号
     */
    private String defId;

    /**
     * 流程任务编号
     */
    private String taskId;

    /**
     * 流程任务定义
     */
    private String taskKey;

    /**
     * 流程任务名称
     */
    private String taskName;

    /**
     * 任务处理人集合
     */
    private List<String> userIds;
}
