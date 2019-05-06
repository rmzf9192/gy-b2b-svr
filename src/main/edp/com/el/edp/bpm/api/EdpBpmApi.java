package com.el.edp.bpm.api;

import com.el.edp.bpm.config.BpmUserResolver;
import com.el.edp.bpm.domain.EdpBpmTaskPayload;
import com.el.edp.bpm.domain.EdpBpmTrace;
import com.el.edp.bpm.service.EdpBpmConfigService;
import com.el.edp.bpm.service.EdpBpmExamService;
import com.el.edp.bpm.service.EdpBpmTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Simon.Hu
 * @since 17/8/8
 */
@Profile("bpm")
@Slf4j
@RestController
@RequestMapping("/edp/bpm/flow")
@RequiredArgsConstructor
public class EdpBpmApi {

    private final EdpBpmExamService bpmExamService;

    private final RepositoryService repositoryService;

    private final BpmUserResolver userResolver;

    private final EdpBpmTraceService bpmTraceService;

    private final EdpBpmConfigService bpmConfigService;

    /**
     * 流程批量审批
     *
     * @param payload 审批内容
     */
    @PostMapping("/examine")
    public void examine(@RequestBody EdpBpmTaskPayload payload) {
        log.info("[EDP-BPM] tasks({}) examine by user({})", payload.getTaskId(), userResolver.user());
        bpmExamService.examine(payload.assigneeOf(userResolver.user()));
    }

    /**
     * 获取流程图
     *
     * @param id 流程定义编号
     * @param response
     * @throws IOException
     */
    @GetMapping("/definitions/xml")
    public void definitionXmlOf(@RequestParam String id,
                                HttpServletResponse response) throws IOException {
        try (val is = repositoryService.getProcessModel(id);
             val os = response.getOutputStream()) {
            IOUtils.copy(is, os);
        }
    }

    /**
     * 获取流程历史任务列表
     *
     * @param id 流程实例编号
     * @return 历史任务列表
     */
    @GetMapping("/instances/{id}/trace")
    public List<EdpBpmTrace> trace(@PathVariable String id) {
        return bpmTraceService.tracesOf(id);
    }

    /**
     * @param id 流程实例编号
     * @return 当前用户是否活动任务的处理人？
     */
    @GetMapping("/instances/{id}/assignee/actived")
    public boolean activedAssignee(@PathVariable String id) {
        return bpmConfigService.activedAssignee(userResolver.user(), id);
    }
}
