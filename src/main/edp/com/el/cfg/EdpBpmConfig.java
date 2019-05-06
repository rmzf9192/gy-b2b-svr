package com.el.cfg;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.storage.FileSystemStorageService;
import com.el.core.storage.StorageProperties;
import com.el.core.storage.StorageService;
import com.el.edp.bpm.*;
import com.el.edp.bpm.config.*;
import com.el.edp.bpm.mapper.EdpBpmTaskMapper;
import com.el.edp.bpm.mapper.EdpBpmTraceMapper;
import com.el.edp.bpm.service.*;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/10/8
 */
@Profile("bpm")
@Slf4j
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class EdpBpmConfig {

    @Bean
    public StorageService storageService(StorageProperties storageProperties) {
        log.info("EDP-BPM] storageService");
        return new FileSystemStorageService(storageProperties);
    }

    @Bean
    public BpmStorageResolver bpmStorageResolver() {
        log.info("EDP-BPM] bpmStorageResolver");
        return new EdpBpmStorageResolver();
    }

    @Bean
    public BpmUserResolver bpmUserResolver(EdpPrincipalService principalService) {
        log.info("EDP-BPM] bpmUserResolver");
        return new EdpBpmUserResolver(principalService);
    }

    @Bean
    public BpmParamsProvider bpmParamsProvider() {
        log.info("EDP-BPM] bpmParamsProvider");
        return new EdpBpmParamsProvider();
    }

    @Bean
    public BpmConfigurer bpmConfigurer() {
        log.info("EDP-BPM] bpmConfigurer");
        return new BpmParamsConfigurer(bpmParamsProvider());
    }

    @Bean
    public BpmInitializer flowInitializer() {
        log.info("EDP-BPM] flowInitializer");
        return new EdpBpmInitializer();
    }

    @Bean
    public EdpBpmTaskService bpmTaskService(TaskService taskService,
                                            HistoryService historyService) {
        log.info("EDP-BPM] bpmTaskService");
        return new EdpBpmTaskService(taskService, historyService);
    }

    @Bean
    public EdpBpmInstanceService bpmInstanceService(RuntimeService runtimeService,
                                                    HistoryService historyService) {
        log.info("EDP-BPM] bpmInstanceService");
        return new EdpBpmInstanceService(runtimeService, historyService);
    }

    @Bean
    public EdpBpmTraceService bpmTraceService(EdpBpmTraceMapper bpmTraceMapper,
                                              EdpBpmTaskService bpmTaskService) {
        log.info("EDP-BPM] bpmTraceService");
        return new EdpBpmTraceService(bpmTraceMapper, bpmTaskService);
    }

    @Bean
    public EdpBpmExamService bpmExamService(EdpBpmTaskService bpmTaskService,
                                            EdpBpmTraceService bpmTraceService) {
        log.info("EDP-BPM] bpmExamService");
        return new EdpBpmSimpleExamService(bpmTaskService, bpmTraceService);
    }

    @Bean
    public EdpBpmRoleService bpmRoleService(EdpBpmTaskMapper bpmTaskMapper) {
        log.info("EDP-BPM] bpmRoleService");
        return new EdpBpmRoleServiceImpl(bpmTaskMapper);
    }

    @Bean
    public EdpBpmConfigService bpmConfigService(EdpBpmInstanceService bpmInstanceService,
                                                EdpBpmTaskService bpmTaskService,
                                                EdpBpmUserResolver userResolver) {
        log.info("EDP-BPM] bpmConfigService");
        return new EdpBpmConfigService(bpmInstanceService, bpmTaskService, userResolver);
    }
}
