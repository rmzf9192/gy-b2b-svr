package com.el.edp.bpm.service;

import com.el.edp.bpm.EdpBpmInstanceService;
import com.el.edp.bpm.EdpBpmTaskService;
import com.el.edp.bpm.config.BpmUserResolver;
import com.el.edp.bpm.domain.EdpBpmConf;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
public class EdpBpmConfigService {

    private final EdpBpmInstanceService bpmInstanceService;

    private final EdpBpmTaskService bpmTaskService;

    private final BpmUserResolver userResolver;

    /**
     * @param assignee 处理人
     * @param prcId 流程实例编号
     * @return 用户是否活动任务的处理人？
     */
    public boolean activedAssignee(String assignee, String prcId) {
        val tasks = bpmTaskService.activeTasks(prcId);

        // 多节点处理人和候选人
        val userIds = new ArrayList<String>();
        tasks.forEach(task -> {
            userIds.add(task.getAssignee());
            Optional.ofNullable(bpmTaskService.candidatesOf(task.getId())).ifPresent(userIds::addAll);
        });

        return userIds.contains(assignee);
    }

    /**
     * @param bpmConf 流程任务配置
     */
    public EdpBpmConf bpmConfig(EdpBpmConf bpmConf) {
        // 设置流程定义编号
        Optional.ofNullable(bpmInstanceService.histProcessInstance(bpmConf.getPrcId()))
            .ifPresent(prc -> bpmConf.setDefId(prc.getDefId()));

        // 设置任务定义
        Optional.ofNullable(taskOf(bpmConf.getPrcId(), userResolver.user()))
            .ifPresent(task -> {
                bpmConf.setTaskKey(task.getKey());
                bpmConf.setTaskId(task.getId());
            });

        return bpmConf;
    }

    /**
     * @param prcId 流程实例编号
     * @param assignee 流程用户
     * @return 任务
     */
    private EdpBpmTask taskOf(String prcId, String assignee) {
        return Optional.ofNullable(bpmTaskService.taskBy(prcId, assignee))
            .orElseGet(() -> {
                // 同一处理人的多个历史任务中返回最后一个任务
                val tasks = Optional.ofNullable(bpmTaskService.histTaskBy(prcId, assignee));
                return tasks.isPresent() && !tasks.get().isEmpty() ? tasks.get().get(0) : null;
            });
    }
}
