package com.el.edp.bpm.config;

import com.el.core.DevError;

/**
 * @author neo.pan
 * @since 17/6/8
 */
public abstract class BpmUtil {

    /**
     * @param processDefinitionId 流程定义ID
     * @return 流程定义Key
     */
    public static String toProcessDefinitionKey(String processDefinitionId) {
        int i = processDefinitionId.indexOf(':');
        if (i == -1) throw DevError.unexpected();
        return processDefinitionId.substring(0, i);
    }

}
