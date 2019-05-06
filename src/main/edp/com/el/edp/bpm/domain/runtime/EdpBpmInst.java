package com.el.edp.bpm.domain.runtime;

import com.el.edp.bpm.config.BpmUtil;
import lombok.Data;
import lombok.ToString;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * @author neo.pan
 * @since 17/5/26
 */
@Data
@ToString(callSuper = true)
public class EdpBpmInst {

    /**
     * The key of the process definition of the process instance.
     */
    private String defKey;

    /**
     * The id of the process definition of the process instance.
     */
    private String defId;

    /**
     * The id of the process instance.
     */
    private String prcId;

    /**
     * The business key of this process instance.
     */
    private String businessKey;

    public static EdpBpmInst of(ProcessInstance processInstance) {
        if (processInstance == null) return null;
        EdpBpmInst prc = new EdpBpmInst();
        prc.defId = processInstance.getProcessDefinitionId();
        prc.defKey = BpmUtil.toProcessDefinitionKey(prc.defId);
        prc.businessKey = processInstance.getBusinessKey();
        prc.prcId = processInstance.getProcessInstanceId();
        return prc;
    }

    public static EdpBpmInst of(HistoricProcessInstance processInstance) {
        if (processInstance == null) return null;
        EdpBpmInst prc = new EdpBpmInst();
        prc.defId = processInstance.getProcessDefinitionId();
        prc.defKey = BpmUtil.toProcessDefinitionKey(prc.defId);
        prc.businessKey = processInstance.getBusinessKey();
        prc.prcId = processInstance.getId();
        return prc;
    }
}
