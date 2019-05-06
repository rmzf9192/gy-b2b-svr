package com.el.edp.bpm.config;

import lombok.Getter;

/**
 * @author neo.pan
 * @since 17/6/8
 */
@Getter
public enum BpmVariables {

    V_INITIATOR("initiator", "流程发起人"),
    V_TASK_OUT_VNAME("vnTaskOut", "存放任务执行结果的变量名"),;

    /**
     * 变量名
     */
    private final String name;

    /**
     * 变量说明
     */
    private final String desc;

    BpmVariables(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
