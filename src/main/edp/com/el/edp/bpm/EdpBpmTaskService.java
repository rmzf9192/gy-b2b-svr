package com.el.edp.bpm;

import com.el.edp.bpm.config.BpmVariables;
import com.el.edp.bpm.domain.EdpBpmTaskPayload;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskAlreadyClaimedException;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.IdentityLink;

import java.util.List;
import java.util.stream.Collectors;

import static com.el.edp.bpm.config.BpmVariables.V_INITIATOR;

/**
 * 流程用户任务服务
 *
 * @author neo.pan
 * @since 17/6/8
 */
@Slf4j
@RequiredArgsConstructor
public final class EdpBpmTaskService {

    private final TaskService taskService;

    private final HistoryService historyService;

    /**
     * 将结果信息
     *
     * @param taskId 任务ID
     * @param key    变量名
     * @return 变量值
     */
    public Object getVariable(String taskId, String key) {
        return taskService.getVariable(taskId, key);
    }

    /**
     * 将结果信息
     *
     * @param taskId 任务ID
     * @param key    变量名
     * @param val    变量值
     */
    public void setVariable(String taskId, String key, Object val) {
        taskService.setVariable(taskId, key, val);
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     */
    public void complete(String taskId, String assignee) {
        val task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task.getDelegationState() == DelegationState.PENDING) {
            taskService.resolveTask(taskId);
            log.debug("[EDP-BPM] resolved task({}) by user({})", taskId, assignee);
            if (assignee.equals(task.getOwner())) {
                taskService.complete(taskId);
                log.debug("[EDP-BPM] and then completed it", taskId, assignee);
            }
        } else {
            taskService.complete(taskId);
            log.debug("[EDP-BPM] completed task({}) by user({})", taskId, assignee);
        }
    }

    /**
     * 判定是否被拒并保存至流程变量：rejected
     *
     * @param exam 审批对象
     */
    public void examineRejected(EdpBpmTaskPayload exam) {
        setVariable(exam.getTaskId(), exam.getTaskKey() + "Rejected", exam.getResult().rejected());
        complete(exam.getTaskId(), exam.getAssignee());
    }

    /**
     * 判定是否同意并保存至流程变量：approved
     *
     * @param exam 审批对象
     */
    public void examineApproved(EdpBpmTaskPayload exam) {
        setVariable(exam.getTaskId(), exam.getTaskKey() + "Approved", exam.getResult().approved());
        complete(exam.getTaskId(), exam.getAssignee());
    }

    /**
     * @param taskId 任务ID
     * @return 任务定义的入参变量
     */
    public String getTaskOutVarName(String taskId) {
        return (String) getVariable(taskId, BpmVariables.V_TASK_OUT_VNAME.getName());
    }

    /**
     * 获取流程发起人任务
     *
     * @param prcId 流程实例编号
     * @return 任务
     */
    public EdpBpmTask initiatorTask(String prcId) {
        return EdpBpmTask.of(taskService.createTaskQuery()
            .processInstanceId(prcId)
            .taskDefinitionKey(V_INITIATOR.getName())
            .active()
            .singleResult());
    }

    /**
     * 获取流程的活动任务列表
     *
     * @param prcId 流程实例编号
     * @return 任务
     */
    public List<EdpBpmTask> activeTasks(String prcId) {
        return taskService.createTaskQuery()
            .processInstanceId(prcId)
            .active().list()
            .stream().map(EdpBpmTask::of)
            .collect(Collectors.toList());
    }

    /**
     * @param id 任务编号
     * @return 活动任务
     */
    public EdpBpmTask taskBy(String id) {
        return EdpBpmTask.of(taskService.createTaskQuery()
            .taskId(id).singleResult());
    }

    /**
     * @param prcId 流程实例编号
     * @param assignee 流程用户
     * @return 任务
     */
    public EdpBpmTask taskBy(@NonNull String prcId, @NonNull String assignee) {
        EdpBpmTask task = taskByAssignee(prcId, assignee);
        if (task == null) {
            task = taskByCandidate(prcId, assignee);
        }
        if (task == null) {
            task = taskByOwner(prcId, assignee);
        }
        return task;
    }

    /**
     * @param id 任务编号
     * @return 历史任务
     */
    public EdpBpmTask histTaskBy(String id) {
        return EdpBpmTask.of(historyService.createHistoricTaskInstanceQuery()
            .taskId(id).singleResult());
    }

    /**
     * @param prcId 流程实例编号
     * @param assignee 流程用户
     * @return 历史任务
     */
    public List<EdpBpmTask> histTaskBy(@NonNull String prcId, @NonNull String assignee) {
        return historyService.createHistoricTaskInstanceQuery()
            .processInstanceId(prcId).taskAssignee(assignee)
            .orderByHistoricActivityInstanceStartTime().desc().list()
            .stream().map(EdpBpmTask::of)
            .collect(Collectors.toList());
    }

    /**
     * 查询待办任务
     *
     * @param prcId 流程实例编号
     * @param assignee 流程用户
     * @return 任务列表
     */
    public EdpBpmTask taskByAssignee(String prcId, String assignee) {
        return EdpBpmTask.of(taskService.createTaskQuery()
            .taskAssignee(assignee)
            .processInstanceId(prcId)
            .active().singleResult());
    }

    /**
     * 查询授权任务
     *
     * @param prcId 流程实例编号
     * @param assignee 流程用户
     * @return 任务列表
     */
    public EdpBpmTask taskByOwner(String prcId, String assignee) {
        return EdpBpmTask.of(taskService.createTaskQuery()
            .taskOwner(assignee)
            .processInstanceId(prcId)
            .active().singleResult());
    }

    /**
     * 查询候选人任务
     *
     * @param prcId 流程实例编号
     * @param candidate 流程用户
     * @return 任务列表
     */
    public EdpBpmTask taskByCandidate(String prcId, String candidate) {
        return EdpBpmTask.of(taskService.createTaskQuery()
            .taskCandidateUser(candidate)
            .processInstanceId(prcId)
            .active().singleResult());
    }

    /**
     * 设置任务代理人
     *
     * @param taskId 任务编号
     * @param assignee 流程用户
     */
    public void delegate(String taskId, String assignee) {
        taskService.delegateTask(taskId, assignee);
    }

    /**
     * 设置任务处理人
     *
     * @param taskId 任务编号
     * @param assignee 流程用户
     */
    public void setAssignee(String taskId, String assignee) {
        taskService.setAssignee(taskId, assignee);
    }

    /**
     * 设置任务授权人
     *
     * @param taskId 任务编号
     * @param assignee 流程用户
     */
    public void setOwner(String taskId, String assignee) {
        taskService.setOwner(taskId, assignee);
    }

    /**
     * 设置任务候选人
     *
     * @param taskId 任务编号
     * @param candidate 流程用户
     */
    public void addCandidate(String taskId, String candidate) {
        taskService.addCandidateUser(taskId, candidate);
    }

    /**
     * 删除任务候选人
     *
     * @param taskId 任务编号
     * @param candidate 流程用户
     */
    public void deleteCandidate(String taskId, String candidate) {
        taskService.deleteCandidateUser(taskId, candidate);
    }

    /**
     * 声明任务处理人
     *
     * @param taskId 任务编号
     * @param assignee 处理人
     */
    public void claim(String taskId, String assignee) {
        try {
            taskService.claim(taskId, assignee);
            log.info("[EDP-BPM] task {} has been claimed by user {}", taskId, assignee);
        } catch (TaskAlreadyClaimedException e) {
            log.warn("[EDP-BPM] task {} has been claimed by user {}", taskId, e.getTaskAssignee());
        }
    }

    /**
     * @param taskId 任务编号
     * @return 候选人列表
     */
    public List<String> candidatesOf(String taskId) {
        val idLinks = taskService.getIdentityLinksForTask(taskId);
        return idLinks.stream().map(IdentityLink::getUserId).collect(Collectors.toList());
    }
}
