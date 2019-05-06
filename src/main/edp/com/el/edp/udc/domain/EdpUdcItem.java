package com.el.edp.udc.domain;

import com.el.cfg.jdbc.EdpEntity;
import com.el.core.udc.UdcItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author neo.pan
 * @since 2018/04/10
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpUdcItem extends EdpEntity implements UdcItem {

    /**
     * 编号
     */
    private String valCode;

    /**
     * 含义
     */
    private String valDesc;

    /**
     * 特殊处理码1
     */
    private String valSphd1;

    /**
     * 特殊处理码2
     */
    private String valSphd2;

    /**
     * 排序编号
     */
    private BigDecimal sortNo;

    @Override
    public String getCode() {
        return valCode;
    }

    @Override
    public String getName() {
        return valDesc;
    }

    public static EdpUdcItem of(String valCode) {
        EdpUdcItem item = new EdpUdcItem();
        item.valCode = valCode;
        return item;
    }

}
