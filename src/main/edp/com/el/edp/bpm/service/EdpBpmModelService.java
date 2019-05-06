package com.el.edp.bpm.service;

import com.el.edp.bpm.api.EdpBpmModelPayload;
import com.el.edp.bpm.domain.model.EdpBpmElemType;
import com.el.edp.bpm.domain.model.EdpBpmServiceTaskDef;
import com.el.edp.bpm.domain.model.EdpBpmUserTaskDef;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
public interface EdpBpmModelService {

    /**
     * @param procDefId 流程定义ID
     * @return 编辑中的流程定义(XML)输出流
     */
    Optional<InputStream> editingModelStreamOf(String procDefId);

    /**
     * @param procDefId    流程定义ID
     * @param outputStream 输出流
     * @throws IOException IO异常发生
     */
    void loadModelXml(String procDefId, OutputStream outputStream) throws IOException;

    /**
     * @param payload 流程定义信息
     */
    void saveModelXml(EdpBpmModelPayload payload);

    /**
     * @param procDefId 流程定义ID
     */
    void commitModelXml(String procDefId) throws IOException;

    /**
     * @param procDefId 流程定义ID
     * @return 指定流程处理定义中的元素类型列表
     * @throws IOException IO异常发生
     */
    List<EdpBpmElemType> modelElemTypes(String procDefId) throws IOException;

    /**
     * @param procDefId 流程定义ID
     * @return 指定流程处理定义中的用户任务定义一览
     * @throws IOException IO异常发生
     */
    List<EdpBpmUserTaskDef> userTaskDefsOf(String procDefId) throws IOException;

    /**
     * @param procDefId 流程定义ID
     * @return 指定流程处理定义中的计算任务定义一览
     * @throws IOException IO异常发生
     */
    List<EdpBpmServiceTaskDef> serviceTaskDefsOf(String procDefId) throws IOException;

}
