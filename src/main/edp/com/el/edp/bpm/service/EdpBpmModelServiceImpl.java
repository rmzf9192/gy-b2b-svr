package com.el.edp.bpm.service;

import com.el.edp.bpm.api.EdpBpmModelPayload;
import com.el.edp.bpm.domain.model.EdpBpmElemType;
import com.el.edp.bpm.domain.model.EdpBpmServiceTaskDef;
import com.el.edp.bpm.domain.model.EdpBpmUserTaskDef;
import com.el.edp.bpm.mapper.EdpBpmModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.el.edp.util.EdpIOUtil.EDP_CHARSET;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Profile("bpm")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpBpmModelServiceImpl implements EdpBpmModelService {

    private static final String BPMN_OMG_MODEL_NS = "http://www.omg.org/spec/BPMN/20100524/MODEL";
    private static final String BPMN_CAMUNDA_NS = "http://camunda.org/schema/1.0/bpmn";

    private static final String BPMN_MODEL_SRC = EdpBpmModelServiceImpl.class.getSimpleName();

    private final RepositoryService repositoryService;

    private final EdpBpmModelMapper modelMapper;

    @Override
    public Optional<InputStream> editingModelStreamOf(String procDefId) {
        val procDefKey = EdpBpmUtil.toProcDefKey(procDefId);
        return Optional.ofNullable(modelMapper.modelReaderOf(procDefKey))
            .map(r -> new ReaderInputStream(r, EDP_CHARSET));
    }

    @Override
    public void loadModelXml(String procDefId, OutputStream outputStream) throws IOException {
        try (val inputStream = editingModelStreamOf(procDefId)
            .orElse(repositoryService.getProcessModel(procDefId))) {
            IOUtils.copy(inputStream, outputStream);
        }
    }

    @Override
    public void saveModelXml(EdpBpmModelPayload payload) {
        val procDefKey = EdpBpmUtil.toProcDefKey(payload.getDefId());
        if (modelMapper.hasModel(procDefKey) == null) {
            modelMapper.addNewModel(procDefKey, payload.getDefXml());
        } else {
            modelMapper.updateModel(procDefKey, payload.getDefXml());
        }
    }

    @Override
    public void commitModelXml(String procDefId) throws IOException {
        val procDefKey = EdpBpmUtil.toProcDefKey(procDefId);
        val modelStream = editingModelStreamOf(procDefKey);
        if (modelStream.isPresent()) {

            BpmnModelInstance modelInstance;
            try (val inputStream = modelStream.get()) {
                modelInstance = Bpmn.readModelFromStream(inputStream);
            }

            val depOld = deploymentOf(deploymentIdOf(procDefId));
            val depNewId = repositoryService.createDeployment()
                .source(BPMN_MODEL_SRC)
                .enableDuplicateFiltering(true)
                .name(depOld.getName())
                .addModelInstance(procDefKey, modelInstance)
                .deploy().getId();
            log.info("[EDP-BPM] new model of {} deployed - {}:{}", depOld.getName(), depNewId);

            modelMapper.deleteModel(procDefKey);

            //TODO 从`depOld`复制表单视图配置信息到`depNewId`

        } else {
            log.info("[EDP-BPM] no model of {} in editing.");
        }
    }

    private Deployment deploymentOf(String deploymentId) {
        return repositoryService.createDeploymentQuery()
            .deploymentId(deploymentId).singleResult();
    }

    private String deploymentIdOf(String procDefId) {
        return repositoryService.getProcessDefinition(procDefId).getDeploymentId();
    }

    @Override
    public List<EdpBpmElemType> modelElemTypes(String procDefId) throws IOException {
        try (val inputStream = repositoryService.getProcessModel(procDefId)) {
            val modelInstance = Bpmn.readModelFromStream(inputStream);
            val model = modelInstance.getModel();
            return model.getTypes().stream()
                .map(t -> EdpBpmElemType.of(t.getTypeNamespace(), t.getTypeName()))
                .collect(Collectors.toList());
        }
    }

    @Override
    public List<EdpBpmUserTaskDef> userTaskDefsOf(String procDefId) throws IOException {
        try (val inputStream = repositoryService.getProcessModel(procDefId)) {
            val modelInstance = Bpmn.readModelFromStream(inputStream);
            val model = modelInstance.getModel();
            val elemType = model.getTypeForName(BPMN_OMG_MODEL_NS, EdpBpmUserTaskDef.BPMN_MODEL_ELEM_TYPE);
            val elmes = modelInstance.getModelElementsByType(elemType);
            return elmes.stream().map(x -> EdpBpmUserTaskDef.of(
                x.getAttributeValue("id"),
                x.getAttributeValue("name"),
                x.getAttributeValueNs(BPMN_CAMUNDA_NS, "assignee"),
                x.getAttributeValueNs(BPMN_CAMUNDA_NS, "candidateUsers")))
                .collect(Collectors.toList());
        }
    }

    @Override
    public List<EdpBpmServiceTaskDef> serviceTaskDefsOf(String procDefId) throws IOException {
        try (val inputStream = repositoryService.getProcessModel(procDefId)) {
            val modelInstance = Bpmn.readModelFromStream(inputStream);
            val model = modelInstance.getModel();
            val elemType = model.getTypeForName(BPMN_OMG_MODEL_NS, EdpBpmServiceTaskDef.BPMN_MODEL_ELEM_TYPE);
            val elems = modelInstance.getModelElementsByType(elemType);
            return elems.stream().map(x -> EdpBpmServiceTaskDef.of(
                x.getAttributeValue("id"),
                x.getAttributeValue("name"),
                x.getAttributeValueNs(BPMN_CAMUNDA_NS, "delegateExpression"),
                x.getAttributeValueNs(BPMN_CAMUNDA_NS, "expression"),
                x.getAttributeValueNs(BPMN_CAMUNDA_NS, "resultVariable")))
                .collect(Collectors.toList());
        }
    }

}
