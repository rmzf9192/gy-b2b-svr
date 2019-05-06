package com.el.edp.bpm.config;

import org.camunda.bpm.engine.delegate.VariableScope;

/**
 * @author neo.pan
 * @since 17/6/8
 */
public class BpmParamsConfigurer implements BpmConfigurer {

    private final BpmParamsProvider bpmParamsProvider;

    public BpmParamsConfigurer(BpmParamsProvider bpmParamsProvider) {
        this.bpmParamsProvider = bpmParamsProvider;
    }

    @Override
    public void config(String defId, VariableScope scope) {
        bpmParamsProvider.params(defId).forEach(param ->
            scope.setVariable(param.getName(), param.getValue()));
    }

}
