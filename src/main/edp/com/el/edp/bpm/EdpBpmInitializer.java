package com.el.edp.bpm;

import com.el.edp.bpm.config.BpmInitializer;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;

/**
 * 流程启动时触发，设置流程启动参数
 *
 * @author neo.pan
 * @since 17/5/31
 */
@Slf4j
public class EdpBpmInitializer extends BpmInitializer {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
    }
}
