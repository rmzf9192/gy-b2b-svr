package com.el.edp.bpm.config;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author neo.pan
 * @since 17/5/31
 */
@Slf4j
public abstract class BpmInitializer implements JavaDelegate {

    @Autowired
    private BpmConfigurer bpmConfigurer;

    @Autowired
    protected BpmUserResolver bpmUserResolver;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.debug("[EDP-BPM] flow started: {}", execution.getId());
        configFlow(execution);
        configInitiator(execution);
    }

    protected final void configFlow(DelegateExecution execution) {
        bpmConfigurer.config(execution.getProcessDefinitionId(), execution);
    }

    protected final void configInitiator(DelegateExecution execution) {
        log.debug("[EDP-BPM] flow initiator: {}", bpmUserResolver.user());
        execution.setVariable(BpmVariables.V_INITIATOR.getName(), bpmUserResolver.user());
    }

}
