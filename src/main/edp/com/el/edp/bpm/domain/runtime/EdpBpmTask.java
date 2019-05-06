package com.el.edp.bpm.domain.runtime;

import com.el.edp.bpm.config.BpmUtil;
import lombok.Data;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.task.Task;

/**
 * @author Simon.Hu
 * @since 17/8/8
 */
@Data
public class EdpBpmTask {
    /**
     * 处理人
     */
    private String assignee;

    /**
     * 任务编号
     */
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务Key
     */
    private String key;

    /**
     * 流程定义编号
     */
    private String defId;

    /**
     * 流程定义Key
     */
    private String defKey;

    /**
     * 流程实例编号
     */
    private String prcId;

    public static EdpBpmTask of(Task task) {
        if (task == null) return null;

        EdpBpmTask tsk = new EdpBpmTask();
        tsk.setDefId(task.getProcessDefinitionId());
        tsk.setDefKey(BpmUtil.toProcessDefinitionKey(tsk.getDefId()));
        tsk.setPrcId(task.getProcessInstanceId());
        tsk.setId(task.getId());
        tsk.setKey(task.getTaskDefinitionKey());
        tsk.setName(task.getName());
        tsk.setAssignee(task.getAssignee());
        return tsk;
    }

    public static EdpBpmTask of(HistoricTaskInstance taskInstance) {
        if (taskInstance == null) return null;

        EdpBpmTask tsk = new EdpBpmTask();
        tsk.setDefId(taskInstance.getProcessDefinitionId());
        tsk.setDefKey(taskInstance.getProcessDefinitionKey());
        tsk.setPrcId(taskInstance.getProcessInstanceId());
        tsk.setId(taskInstance.getId());
        tsk.setKey(taskInstance.getTaskDefinitionKey());
        tsk.setName(taskInstance.getName());
        tsk.setAssignee(taskInstance.getAssignee());
        return tsk;
    }
}
