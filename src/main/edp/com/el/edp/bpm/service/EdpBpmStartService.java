package com.el.edp.bpm.service;

import com.el.edp.bpm.api.EdpBpmOp;
import com.el.edp.bpm.domain.EdpBpmForm;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Simon.Hu
 * @since 17/8/3
 */
public interface EdpBpmStartService {

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @return 流程实例编号
     */
    String startWorkflow(EdpBpmForm bpmForm);

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @param initiator 发起人
     * @return 流程实例编号
     */
    String startWorkflow(EdpBpmForm bpmForm, String initiator);

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @param variables 初始变量
     * @return 流程实例编号
     */
    String startWorkflow(EdpBpmForm bpmForm, Map<String, Object> variables);

    /**
     * 检查流程启动参数
     * @param bpmForm 流程表单
     * @param startup 启动函数
     * @return 检查结果
     */
    EdpBpmOp checkInitParam(EdpBpmForm bpmForm, Consumer<EdpBpmForm> startup);
}
