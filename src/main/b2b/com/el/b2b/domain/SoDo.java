package com.el.b2b.domain;

import com.el.mbg.domain.VE84211W;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 订单收货对象
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoDo extends VE84211W {

    /**
     * 虚拟主键
     */
    private String id;
    /**
     * 生产日期
     */
    private String fcd01Str;
    /**
     * 失效日期
     */
    private String iommejStr;

    /**
     * 订单公司
     */
    private String ouName;
    /**
     * 收货单位
     */
    private String soOuName;
    /**
     * 收货地址
     */
    private String deliverAddress;
    /**
     * 联系人
     */
    private String deliverContact;
    /**
     * 电话
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 配送单状态
     */
    private String lttrStatus;

    /**
     * 厂家物料编码 - PB_ITEM.SPE8CJWLBM
     */
    private String spe8cjwlbm;

    /**
     * 生产厂家 - PB_ITEM.SPAN8DSC
     */
    private String span8dsc;

    /**
     * 订单明细
     */
    private List<SoDo> sodoDetailList;


}

