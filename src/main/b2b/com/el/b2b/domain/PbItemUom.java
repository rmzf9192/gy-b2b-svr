package com.el.b2b.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PbItemUom {
    private String ouCode;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 管理计量单位编码
     */
    private String umCode;

    /**
     * 管理计量单位
     */
    private String umName;

    /**
     * 主计量单位编码
     */
    private String rumCode;
    /**
     * 主计量单位
     */
    private String rumName;

    private BigDecimal conv;

}
