package com.el.edp.bpm.domain.model;

import com.el.core.domain.CodeName;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * 计算任务定义
 *
 * @author neo.pan
 * @since 2018/04/23
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(of = "id")
public class EdpBpmServiceTaskDef implements CodeName {

    public static final String BPMN_MODEL_ELEM_TYPE = "serviceTask";

    private final String id;
    private final String name;
    private final String delegateExpression;
    private final String expression;
    private final String resultVariable;

    @Override
    public String getCode() {
        return id;
    }

}
