package com.el.edp.bpm.api.runtime;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.OpsError;
import com.el.core.domain.PagingResult;
import com.el.core.storage.StorageException;
import com.el.core.storage.StorageService;
import com.el.edp.bpm.config.BpmStorageResolver;
import com.el.edp.bpm.domain.runtime.EdpBpmDepDefinition;
import com.el.edp.bpm.mapper.EdpBpmDefinitionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

/**
 * @author neo.pan
 * @since 17/6/5
 */
@Profile("bpm")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/edp/bpm")
public class EdpBpmDefinitionApi {

    private final EdpBpmDefinitionMapper bpmDefinitionMapper;

    private final EdpPrincipalService principalService;

    private final BpmStorageResolver storageResolver;

    private final RepositoryService repositoryService;

    private final StorageService storageService;

    /**
     * 查询 流程定义 列表
     *
     * @param query 搜索条件
     * @return 列表
     */
    @GetMapping("/definitions")
    public PagingResult<EdpBpmDepDefinition> definitions(EdpBpmDefinitionQuery query) {
        val rows = bpmDefinitionMapper.definitionsBy(query);
        val total = bpmDefinitionMapper.definitionCountBy(query);
        return PagingResult.of(rows, total);
    }

    /**
     * 删除 流程定义
     *
     * @param defId 编号列表
     */
    @PostMapping("/definitions/{id}")
    public void deleteDefinition(@PathVariable("id") String defId) {
        repositoryService.deleteProcessDefinition(defId, true, true);
    }

    /**
     * 获取部署仓库名称
     *
     * @param depName 部署名称
     * @return 流程仓库名
     */
    @GetMapping("/definitions/repo")
    public String repoNameOf(String depName) {
        val repoName = storageResolver.repoNameOf(depName);
        log.info("EDP-BPM] generate deploy repo:{}", repoName);
        return repoName;
    }

    /**
     * 流程部署
     *
     * @param depName 部署名称
     */
    @PostMapping("/definitions/deploy")
    public void deploy(@RequestParam String depName) throws StorageException {
        val deploymentBuilder = repositoryService.createDeployment()
            .source(principalService.getPrincipal().getUser().getName())
            .enableDuplicateFiltering(true)
            .name(depName);

        val repoName = storageResolver.repoNameOf(depName);
        log.info("EDP-BPM] storageResolver repo name: {}", repoName);

        storageService.filesOf(repoName).forEach(file -> {
            try (val bpmnInput = storageService.load(repoName, file.getFileName()).getInputStream()) {
                val bpmnModel = Bpmn.readModelFromStream(bpmnInput);
                deploymentBuilder.addModelInstance(file.getFileName(), bpmnModel);
            } catch (Throwable e) {
                log.error("EDP-BPM] error occurred while deploying file {}", file.getFileName());
                throw new OpsError(e.getLocalizedMessage());
            }
        });

        deploymentBuilder.deploy();
        storageService.eraseAll(repoName);
    }
}
