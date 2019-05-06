package com.el.edp.bpm.service;

import com.el.core.DevError;
import com.el.edp.bpm.EdpBpmInstanceService;
import com.el.edp.bpm.EdpBpmTaskService;
import com.el.edp.bpm.api.EdpBpmOp;
import com.el.edp.bpm.config.BpmUserResolver;
import com.el.edp.bpm.domain.EdpBpmForm;
import com.el.edp.bpm.domain.EdpBpmTaskPayload;
import com.el.edp.bpm.domain.runtime.EdpBpmInst;
import com.el.edp.bpm.domain.runtime.EdpBpmTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static com.el.edp.bpm.api.EdpBpmOp.NG_BPM_ROLE_SETTLE_ERROR;
import static com.el.edp.bpm.api.EdpBpmOp.OK;
import static com.el.edp.bpm.config.BpmVariables.V_INITIATOR;
import static com.el.edp.bpm.domain.EdpBpmTaskResult.SUBMITTED;

/**
 * @author Simon.Hu
 * @since 17/8/3
 */
@Profile("bpm")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpBpmStartServiceImpl implements EdpBpmStartService {

    private final EdpBpmInstanceService bpmInstanceService;

    private final EdpBpmTaskService bpmTaskService;

    private final EdpBpmExamService bpmExamService;

    private final BpmUserResolver userResolver;

    private final EdpBpmRoleService bpmRoleService;

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @return 流程实例编号
     */
    public String startWorkflow(EdpBpmForm bpmForm) {
        return startWorkflow(bpmForm, null, null);
    }

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @param initiator 发起人
     * @return 流程实例编号
     */
    public String startWorkflow(EdpBpmForm bpmForm, String initiator) {
        return startWorkflow(bpmForm, null,  initiator);
    }

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @param variables 初始变量
     * @return 流程实例编号
     */
    @Override
    public String startWorkflow(EdpBpmForm bpmForm, Map<String, Object> variables) {
        return startWorkflow(bpmForm, variables,  null);
    }

    /**
     * 开启流程
     * @param bpmForm 流程表单
     * @param variables 初始变量
     * @param initiator 发起人
     * @return 流程实例编号
     */
    private String startWorkflow(EdpBpmForm bpmForm, Map<String, Object> variables, String initiator) {
        // 首次启动流程
        if (StringUtils.isEmpty(bpmForm.getPrcId())) {
            val initVariables = initVariables(bpmForm, variables, initiator);
            return Optional.of(startInstance(bpmForm, initVariables))
                .map(inst -> approveInitiatorTask(inst.getPrcId(), initiator).getPrcId())
                .orElseThrow(DevError::unexpected);
        }
        // 重新提交原流程
        else {
            return approveInitiatorTask(bpmForm.getPrcId(), initiator).getPrcId();
        }
    }

    /**
     * @param bpmForm 流程表单
     * @param variables 初始变量
     * @return 流程启动初始变量
     */
    private Map<String, Object> initVariables(EdpBpmForm bpmForm, Map<String, Object> variables, String initiator) {
        val trMapping = bpmRoleService.taskRoleMapping(bpmForm.getDefKey());
        Map<String, Object> initVariables = new ConcurrentHashMap<>(trMapping);
        if (!StringUtils.isEmpty(initiator)) {
            initVariables.put(V_INITIATOR.getName(), initiator);
        }
        Optional.ofNullable(variables).ifPresent(initVariables::putAll);
        return initVariables;
    }

    /**
     * 检查流程启动参数
     * @param bpmForm 流程表单
     * @param startup 启动函数
     * @return 检查结果
     */
    public EdpBpmOp checkInitParam(EdpBpmForm bpmForm, Consumer<EdpBpmForm> startup) {
        if (bpmRoleService.checkBpmRoleSettleCorrectly(bpmForm.getDefKey())) {
            startup.accept(bpmForm); return OK;
        }
        return NG_BPM_ROLE_SETTLE_ERROR;
    }

    /**
     * 创建流程实例
     * @param bpmForm 流程表单
     * @param variables 初始变量
     */
    private EdpBpmInst startInstance(EdpBpmForm bpmForm, Map<String, Object> variables) {
        return bpmInstanceService.startProcessInstance(bpmForm.getDefKey(), bpmForm.businessKey(), variables);
    }

    /**
     * 提交审批首个任务
     * @param prcId 流程实例编号
     * @param initiator 发起人
     */
    private EdpBpmTask approveInitiatorTask(String prcId, String initiator) {
        val task = bpmTaskService.initiatorTask(prcId);
        initiator = StringUtils.isEmpty(initiator) ? userResolver.user() : initiator;
        return bpmExamService.examine(EdpBpmTaskPayload.of(task.getId(), initiator, SUBMITTED));
    }
}
