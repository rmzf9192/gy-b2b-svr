package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class TB2bSoD {
    /**
     * 主键 - B2B_SO_D.ID
     */
    private Long id;

    /**
     * 租户ID - B2B_SO_D.TENANT_ID
     */
    private Long tenantId;

    /**
     * 订单ID - B2B_SO_D.SO_ID
     */
    private Long soId;

    /**
     * 行号 - B2B_SO_D.LINE_NO
     */
    private int lineNo;

    /**
     * 出库仓库ID - B2B_SO_D.WH_ID
     */
    private Long whId;

    /**
     * 品项ID - B2B_SO_D.ITEM_ID
     */
    private Long itemId;

    /**
     * 品项编号 - B2B_SO_D.ITEM_CODE
     */
    private String itemCode;

    /**
     * 品项名称 - B2B_SO_D.ITEM_NAME
     */
    private String itemName;

    /**
     * SKUID - B2B_SO_D.SKU_ID
     */
    private Long skuId;

    /**
     * 行类型 - B2B_SO_D.LINE_TYPE
     */
    private String lineType;

    /**
     * 行状态 - B2B_SO_D.LINE_STATUS
     */
    private String lineStatus;

    /**
     * 支付状态 - B2B_SO_D.PAY_STATUS
     */
    private String payStatus;

    /**
     * 需求数量 - B2B_SO_D.QTY
     */
    private int qty;

    /**
     * 单位 - B2B_SO_D.UOM
     */
    private String uom;

    /**
     * 重量 - B2B_SO_D.WEIGHT
     */
    private BigDecimal weight;

    /**
     * 重量单位 - B2B_SO_D.WEIGHT_UOM
     */
    private String weightUom;

    /**
     * 重量转换率 - B2B_SO_D.WEIGHT_RATIO
     */
    private BigDecimal weightRatio;

    /**
     * 面价 - B2B_SO_D.BASE_PRICE
     */
    private BigDecimal basePrice;

    /**
     * 价格类型 - B2B_SO_D.PRICE_TYPE
     */
    private String priceType;

    /**
     * 价格 - B2B_SO_D.PRICE
     */
    private BigDecimal price;

    /**
     * 折扣类型 - B2B_SO_D.DISC_TYPE
     */
    private String discType;

    /**
     * 折扣率 - B2B_SO_D.DISC_RATIO
     */
    private BigDecimal discRatio;

    /**
     * 折扣额 - B2B_SO_D.DISC_AMT
     */
    private BigDecimal discAmt;

    /**
     * 税率 - B2B_SO_D.TAX_RATE
     */
    private BigDecimal taxRate;

    /**
     * 税额 - B2B_SO_D.TAX_AMT
     */
    private BigDecimal taxAmt;

    /**
     * 不含税金额 - B2B_SO_D.NET_AMT
     */
    private BigDecimal netAmt;

    /**
     * 需求金额 - B2B_SO_D.AMT
     */
    private BigDecimal amt;

    /**
     * 备注 - B2B_SO_D.REMARK
     */
    private String remark;

    /**
     * 创建人ID - B2B_SO_D.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     * 创建时间 - B2B_SO_D.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     * 最后编辑人ID - B2B_SO_D.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     * 最后编辑时间 - B2B_SO_D.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     * 数据版本号 - B2B_SO_D.AUDIT_DATA_VERSION
     */
    private BigDecimal auditDataVersion;

    /**
     * 是否删除 - B2B_SO_D.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     * 已分配数量 - B2B_SO_D.ASSIGNED_QTY
     */
    private int assignedQty;

    /**
     * 未分配数量 - B2B_SO_D.UNASSIGNED_QTY
     */
    private int unassignedQty;

    /**
     * 已取消数量 - B2B_SO_D.CANCELLED_QTY
     */
    private int cancelledQty;

    /**
     * 已分配金额 - B2B_SO_D.ASSIGNED_AMT
     */
    private BigDecimal assignedAmt;

    /**
     * 单据编号 - B2B_SO_D.DOC_NO
     */
    private String docNo;

    /**
     * 单据编号 - B2B_SO_D.DOC_NO
     */
    private String docStatus;

    /**
     * 公司编码
     */
    private String ouCode;

    /**
     * ERP单据状态 - B2B_SO_D.ERP_STATUS
     */
    private String erpStatus;

}
