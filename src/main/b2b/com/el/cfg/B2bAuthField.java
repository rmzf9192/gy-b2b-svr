package com.el.cfg;

import com.el.edp.sec.domain.EdpAuthField;
import lombok.Getter;

/**
 * @author neo.pan
 * @since 2018/03/23
 */
public enum B2bAuthField implements EdpAuthField {

    EA("管理者"), EO("运营者"), EP("供应商"),;

    public String getCode() {
        return name();
    }

    @Getter
    private String name;

    B2bAuthField(String name) {
        this.name = name;
    }
}
