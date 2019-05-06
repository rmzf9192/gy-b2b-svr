package com.el.edp.bpm.api;

import com.el.core.web.OpResult;

public enum EdpBpmOp implements OpResult {
    OK("成功"),
    NG_BPM_ROLE_SETTLE_ERROR("流程角色设置错误"),
    ;

    private final String desc;

    EdpBpmOp(String desc) {
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
