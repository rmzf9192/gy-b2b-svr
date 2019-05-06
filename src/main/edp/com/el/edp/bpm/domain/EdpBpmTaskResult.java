package com.el.edp.bpm.domain;

import lombok.Getter;

/**
 * @author neo.pan
 * @since 17/6/7
 */
@Getter
public enum EdpBpmTaskResult {

    SUBMITTED("提交"),
    REJECTED("拒绝"),
    APPROVED("通过"),;

    public String code() {
        return name().toLowerCase();
    }

    private final String desc;

    EdpBpmTaskResult(String desc) {
        this.desc = desc;
    }

    public Boolean approved() {
        return this == APPROVED;
    }

    public Boolean rejected() {
        return this == REJECTED;
    }
}
