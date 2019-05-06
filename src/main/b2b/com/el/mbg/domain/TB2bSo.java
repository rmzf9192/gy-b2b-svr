package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TB2bSo {
    /**
     *  主键 - B2B_SO.ID
     */
    private Long id;

    /**
     *  租户ID - B2B_SO.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司ID - B2B_SO.OU_ID
     */
    private Long ouId;

    /**
     *  组织ID - B2B_SO.ORG_ID
     */
    private Long orgId;

    /**
     *  单据编号 - B2B_SO.DOC_NO
     */
    private String docNo;

    /**
     *  单据类型 - B2B_SO.DOC_TYPE
     */
    private String docType;

    /**
     *  单据状态 - B2B_SO.DOC_STATUS
     */
    private String docStatus;

    /**
     *  下单时间 - B2B_SO.DOC_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime docTime;

    /**
     *  订单批ID - B2B_SO.SO_BATCH_ID
     */
    private Long soBatchId;

    /**
     *  单据编号2 - B2B_SO.DOC_NO2
     */
    private String docNo2;

    /**
     *  单据类型2 - B2B_SO.DOC_TYPE2
     */
    private String docType2;

    /**
     *  下单渠道 - B2B_SO.SO_SOURCE
     */
    private String soSource;

    /**
     *  销售区域 - B2B_SO.SALE_REGION
     */
    private String saleRegion;

    /**
     *  配送区域 - B2B_SO.DELIVER_REGION
     */
    private String deliverRegion;

    /**
     *  客户ID - B2B_SO.CUST_ID
     */
    private Long custId;

    /**
     *  客户编号 - B2B_SO.CUST_CODE
     */
    private String custCode;

    /**
     *  下单员工ID - B2B_SO.SO_EMP_ID
     */
    private Long soEmpId;

    /**
     *  业务员员工ID - B2B_SO.AGENT_EMP_ID
     */
    private Long agentEmpId;

    /**
     *  货币码 - B2B_SO.CURR_CODE
     */
    private String currCode;

    /**
     *  税率码 - B2B_SO.TAX_CODE
     */
    private String taxCode;

    /**
     *  审核状态 - B2B_SO.APPR_STATUS
     */
    private String apprStatus;

    /**
     *  审核时间 - B2B_SO.APPR_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime apprTime;

    /**
     *  审核人ID - B2B_SO.APPR_USER_ID
     */
    private Long apprUserId;

    /**
     *  支付状态 - B2B_SO.PAY_STATUS
     */
    private String payStatus;

    /**
     *  支付时间 - B2B_SO.PAY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime payTime;

    /**
     *  支付交易ID - B2B_SO.PAY_TRANS_ID
     */
    private Long payTransId;

    /**
     *  付款方式 - B2B_SO.PAY_METHOD
     */
    private String payMethod;

    /**
     *  支付条款 - B2B_SO.PAYMENT_TERM
     */
    private String paymentTerm;

    /**
     *  拣货时间 - B2B_SO.PL_DATE
     */
    @TimeUtil.TIME
    private LocalDate plDate;

    /**
     *  配送状态 - B2B_SO.DELIVER_STATUS
     */
    private String deliverStatus;

    /**
     *  送货方式 - B2B_SO.DELIVER_WAY
     */
    private String deliverWay;

    /**
     *  配送联系人 - B2B_SO.DELIVER_CONTACT
     */
    private String deliverContact;

    /**
     *  配送地址ID - B2B_SO.DELIVER_ADDRESS_ID
     */
    private Long deliverAddressId;

    /**
     *  配送地址 - B2B_SO.DELIVER_ADDRESS
     */
    private String deliverAddress;

    /**
     *  出货时间 - B2B_SO.SHIP_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime shipTime;

    /**
     *  配送送达时间 - B2B_SO.DELIVER_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime deliverTime;

    /**
     *  确认收货时间 - B2B_SO.RECEIVE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime receiveTime;

    /**
     *  退货状态 - B2B_SO.RETURN_STATUS
     */
    private String returnStatus;

    /**
     *  退货申请时间 - B2B_SO.RETURN_APPLY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime returnApplyTime;

    /**
     *  退货批准时间 - B2B_SO.RETURN_APPR_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime returnApprTime;

    /**
     *  评价状态 - B2B_SO.COMMENT_STATUS
     */
    private String commentStatus;

    /**
     *  评价类型 - B2B_SO.COMMENT_TYPE
     */
    private String commentType;

    /**
     *  评价时间 - B2B_SO.COMMENT_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime commentTime;

    /**
     *  评价内容 - B2B_SO.COMMENT_CONTENT
     */
    private String commentContent;

    /**
     *  取消时间 - B2B_SO.CANCEL_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime cancelTime;

    /**
     *  取消原因 - B2B_SO.CANCEL_REASON
     */
    private String cancelReason;

    /**
     *  退款状态 - B2B_SO.REFUND_STATUS
     */
    private String refundStatus;

    /**
     *  退款时间 - B2B_SO.REFUND_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime refundTime;

    /**
     *  退款金额 - B2B_SO.REFUND_AMT
     */
    private BigDecimal refundAmt;

    /**
     *  折扣金额 - B2B_SO.DISC_AMT
     */
    private BigDecimal discAmt;

    /**
     *  税率 - B2B_SO.TAX_RATE
     */
    private BigDecimal taxRate;

    /**
     *  税额 - B2B_SO.TAX_AMT
     */
    private BigDecimal taxAmt;

    /**
     *  不含税金额 - B2B_SO.NET_AMT
     */
    private BigDecimal netAmt;

    /**
     *  运费金额 - B2B_SO.FREIGHT_FEE
     */
    private BigDecimal freightFee;

    /**
     *  订单金额 - B2B_SO.AMT
     */
    private BigDecimal amt;

    /**
     *  模板ID - B2B_SO.TSO_ID
     */
    private Long tsoId;

    /**
     *  备注 - B2B_SO.REMARK
     */
    private String remark;

    /**
     *  外部编码 - B2B_SO.OUTER_CODE
     */
    private String outerCode;

    /**
     *  创建人ID - B2B_SO.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - B2B_SO.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - B2B_SO.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - B2B_SO.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - B2B_SO.AUDIT_DATA_VERSION
     */
    private BigDecimal auditDataVersion;

    /**
     *  是否删除 - B2B_SO.DELETE_FLAG
     */
    private Short deleteFlag;

    private String ouCode;

    private String mobile;

    private String deliverAddressNo;

    /**
     *  订单传输状态 - B2B_SO.TRANSFER_FLAG
     */
    private String transferFlag;
}
