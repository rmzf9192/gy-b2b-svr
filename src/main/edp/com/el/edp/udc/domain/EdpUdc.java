package com.el.edp.udc.domain;

import com.el.cfg.jdbc.EdpEntity;
import com.el.core.udc.DomainUdcFace;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author neo.pan
 * @since 17/5/10
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpUdc extends EdpEntity implements DomainUdcFace {

    /**
     * 领域代码
     */
    private String domainCode;

    /**
     * UDC编号
     */
    private String udcCode;

    /**
     * UDC名称
     */
    private String udcName;

    @Override
    public String getName() {
        return udcName;
    }

    /**
     * UDC描述
     */
    private String udcDesc;

    /**
     * 系统级控制
     */
    private boolean sysFlag;

    public static EdpUdc of(String domainCode, String udcCode) {
        EdpUdc udc = new EdpUdc();
        udc.domainCode = domainCode;
        udc.udcCode = udcCode;
        return udc;
    }

}
