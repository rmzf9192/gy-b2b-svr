package com.el.edp.bpm.domain;

public interface EdpBpmForm {
    /**
     * @return 流程定义
     */
    String getDefKey();

    /**
     * @return 流程实例编号
     */
    String getPrcId();

    /**
     * @return 业务主键
     */
    String businessKey();
}
