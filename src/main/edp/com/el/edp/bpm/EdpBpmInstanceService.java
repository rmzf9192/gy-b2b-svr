package com.el.edp.bpm;

import com.el.edp.bpm.domain.runtime.EdpBpmInst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;

import java.util.Map;
import java.util.Optional;

/**
 * @author Simon.Hu
 * @since 17/8/8
 */
@Slf4j
@RequiredArgsConstructor
public class EdpBpmInstanceService {

    private final RuntimeService runtimeService;

    private final HistoryService historyService;

    /**
     * 使用最新版本的流程定义启动流程实例
     *
     * @param defKey 流程定义Key
     * @param businessKey 业务主键
     * @return 流程实例
     */
    public EdpBpmInst startProcessInstance(String defKey, String businessKey, Map<String, Object> variables) {
        return EdpBpmInst.of(runtimeService.startProcessInstanceByKey(defKey, businessKey, variables));
    }

    /**
     * 查询流程实例
     *
     * @param id 流程实例编号
     * @return 流程实例
     */
    public EdpBpmInst processInstance(String id) {
        return EdpBpmInst.of(runtimeService.createProcessInstanceQuery()
            .processInstanceId(id).singleResult());
    }

    /**
     * 查询流程实例
     *
     * @param id 流程实例编号
     * @return 流程实例
     */
    public EdpBpmInst histProcessInstance(String id) {
        return EdpBpmInst.of(historyService.createHistoricProcessInstanceQuery()
            .processInstanceId(id).singleResult());
    }

    /**
     * 删除流程实例
     *
     * @param id 流程实例ID
     */
    public void deleteProcessInstance(String id, String reason) {
        runtimeService.deleteProcessInstance(id, reason);
    }

    /**
     * 查询业务主键
     *
     * @param prcId 流程实例编号
     * @return 业务主键
     */
    public Optional<String> getBusinessKey(String prcId) {
        return Optional.ofNullable(processInstance(prcId)).map(EdpBpmInst::getBusinessKey);
    }
}
