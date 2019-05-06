package com.el.cfg;

import com.el.edp.sec.domain.EdpEntType;
import lombok.Getter;

import static com.el.cfg.B2bAuthField.EO;
import static com.el.cfg.B2bAuthField.EP;

/**
 * @author neo.pan
 * @since 2018/03/23
 */
@Getter
public enum B2bEntType implements EdpEntType {

    oper("运营者", EO), supp("供应商", EP),;

    public String getCode() {
        return name();
    }

    private String name;
    private B2bAuthField authField;

    B2bEntType(String name, B2bAuthField authField) {
        this.name = name;
        this.authField = authField;
    }

}
