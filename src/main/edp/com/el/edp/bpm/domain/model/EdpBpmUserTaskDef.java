package com.el.edp.bpm.domain.model;

import com.el.core.domain.CodeName;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * 用户任务定义
 * <p>
 * - 流程中、即为流程的`UserTask`活动节点定义
 * - 非流程、即为上下文业务处理定义
 *
 * @author neo.pan
 * @since 2018/04/23
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(of = "id")
public class EdpBpmUserTaskDef implements CodeName {

    public static final String BPMN_MODEL_ELEM_TYPE = "userTask";

    private final String id;
    private final String name;
    private final String assignee;
    private final String candidateUsers;

    @Override
    public String getCode() {
        return id;
    }

}
