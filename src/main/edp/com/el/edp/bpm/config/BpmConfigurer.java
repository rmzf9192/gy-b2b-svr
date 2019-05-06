package com.el.edp.bpm.config;

import org.camunda.bpm.engine.delegate.VariableScope;

/**
 * @author neo.pan
 * @since 17/6/8
 */
public interface BpmConfigurer {

    /**
     * 流程参数配置
     *
     * @param defId 流程定义ID
     * @param scope 变量作用域
     */
    void config(String defId, VariableScope scope);

}
