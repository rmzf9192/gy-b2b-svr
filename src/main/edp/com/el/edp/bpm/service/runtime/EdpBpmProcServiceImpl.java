package com.el.edp.bpm.service.runtime;

import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2018/04/27
 */
@Profile("bpm")
@Slf4j
@RequiredArgsConstructor
public class EdpBpmProcServiceImpl implements EdpBpmProcService {

    private final TaskService taskService;

    @Override
    public Optional<EdpBpmTask> activeTaskOf(String procId, String assignee) {
        return taskService.createTaskQuery()
            .processInstanceId(procId)
            .taskAssignee(assignee)
            .active()
            .list()
            .stream()
            .findAny()
            .map(EdpBpmTask::of);
    }

}
