package com.el.edp.bpm.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author Simon.Hu
 * @since 2018/04/19
 */
public interface EdpBpmExecutionService extends JavaDelegate {

    @Override
    default void execute(DelegateExecution execution) throws Exception {
    }
}
