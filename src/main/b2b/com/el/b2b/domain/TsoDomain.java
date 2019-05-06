package com.el.b2b.domain;

import com.el.mbg.domain.TB2bTso;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TsoDomain extends TB2bTso {
    /**
     * 模板详情id
     */
    private String tsodId;
    /**
     * 模板id
     */
    private String tsoId;
    /**
     * 品项ID
     */
    private String itemId;
    /**
     * 单位
     */
    private String uom;
    private String uomCode;//
    /**
     * 品项 code
     */
    private String itemCode;
    /**
     * 品项 name
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;
    /**
     *
     */
    private List<TsodDomain> tsod;
    /**
     * 公司CODE
     */
    private String ouCode;
    /**
     * 客户CODE
     */
    private String custCode;
    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 厂家物料编码 - PB_ITEM.SPE8CJWLBM
     */
    private String spe8cjwlbm;

    /**
     * 生产厂家 - PB_ITEM.SPAN8DSC
     */
    private String span8dsc;

    /**
     * 生产厂家编码 - PB_ITEM.SPAN8DSC
     */
    private String span8;

    private List<UomTrans> uomList;
    private BigDecimal conv;
    /**
     * 主计量单价
     */
    private BigDecimal basePrice;
}
