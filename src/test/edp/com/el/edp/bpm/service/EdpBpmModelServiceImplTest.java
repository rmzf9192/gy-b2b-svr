package com.el.edp.bpm.service;

import com.el.edp.EdpTest;
import com.el.edp.bpm.api.EdpBpmModelPayload;
import com.el.edp.bpm.mapper.EdpBpmModelMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;

import static com.el.edp.util.EdpIOUtil.EDP_CHARSET;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@ActiveProfiles({"edp", "test"})
@Slf4j
public class EdpBpmModelServiceImplTest extends EdpTest {

    private static final String PROC_DEF_ID = "SuppliperItem:1:87323";

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Autowired
    private EdpBpmModelMapper modelMapper;

    private EdpBpmModelService buildModelService() {
        val repositoryService = processEngineRule.getRepositoryService();
        return new EdpBpmModelServiceImpl(repositoryService, modelMapper);
    }

    @Test
    public void list_elemTypes() throws IOException {
        val modelService = buildModelService();
        modelService.modelElemTypes(PROC_DEF_ID)
            .forEach(type -> log.info(type.toString()));
    }

    @Test
    public void list_taskDefs() throws IOException {
        val modelService = buildModelService();

        val userTaskDefs = modelService.userTaskDefsOf(PROC_DEF_ID);
        log.info("=== USER TASK ({}) ===", userTaskDefs.size());
        userTaskDefs.forEach(def -> log.info(def.toString()));

        val serviceTaskDefs = modelService.serviceTaskDefsOf(PROC_DEF_ID);
        log.info("=== SERVICE TASK ({}) ===", serviceTaskDefs.size());
        serviceTaskDefs.forEach(def -> log.info(def.toString()));
    }

    @Test
    public void saveModelXml() throws IOException {
        val modelService = buildModelService();
        modelService.saveModelXml(EdpBpmModelPayload.of(PROC_DEF_ID, "<xml><foo></foo></xml>"));
    }

    @Test
    public void loadModelXml() throws IOException {
        val modelService = buildModelService();
        val outPath = toTestPath("Hello.xml");
        try (val outputStream = Files.newOutputStream(outPath)) {
            modelService.loadModelXml(PROC_DEF_ID, outputStream);
        }
        try (val reader = Files.newBufferedReader(outPath, EDP_CHARSET)) {
            log.info("loadModelXml.firstLine={}", reader.readLine());
        }
    }

}
