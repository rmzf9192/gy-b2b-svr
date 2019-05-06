package com.el.b2b.domain;

import com.el.mbg.domain.TB2bSo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/15
 * @Description: 销售订单domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class So extends TB2bSo {

    /**
     * 公司编码
     */
    private String ouCode;
    /**
     * 公司名称
     */
    private String ouName;
    /**
     * 手机
     */
    private String mobile;

    /**
     * 订单明细ID --sodId
     */
    private Long sodId;
    /**
     * 数量
     */
    private BigDecimal qty;
    /**
     * 单位
     */
    private String uom;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 金额
     */
    private BigDecimal amt;
    /**
     * 状态
     */
    private String status;
    /**
     * 收货地址
     */
    private String deliverAddress;
    /**
     * 联系人
     */
    private String deliverContact;
//    /**
//     * 电话
//     */
//    private String mobile;
    /**
     * 收货单位
     */
    private String custName;
    /**
     * 产品编号
     */
    private String itemCode;
    /**
     * 产品名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;

    /**
     * 订单明细List
     */
    private List<SoD> sodList;
}
