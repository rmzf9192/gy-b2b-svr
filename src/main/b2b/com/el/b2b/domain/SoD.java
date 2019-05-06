package com.el.b2b.domain;

import com.el.mbg.domain.TB2bSoD;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/26
 * @Description: 销售订单明细domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoD extends TB2bSoD {

    /**
     * 预计发货日期
     */
    private Date expectedDeliveryDate;
    /**
     * 风险等级
     */
    private String grade;
    /**
     * 商品编码
     */
    private String outerCode;
    /**
     * 申请人  PB_CUST
     */
    private String applyName;
    /**
     * 规格   PB_ITEM
     */
    private String spec;

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
    /**
     * 单价转换系数
     */
    private BigDecimal conv;

    private List<UomTrans> uomList;
    /**
     * 主计量单价
     */
    private BigDecimal basePrice;
    private String uomCode;

    private String sodStatus;

}
