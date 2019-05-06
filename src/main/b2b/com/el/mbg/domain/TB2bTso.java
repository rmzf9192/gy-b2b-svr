package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TB2bTso {
    /**
     *  主键 - B2B_TSO.ID
     */
    private Long id;

    /**
     *  租户ID - B2B_TSO.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司ID - B2B_TSO.OU_ID
     */
    private Long ouId;

    /**
     *  组织ID - B2B_TSO.ORG_ID
     */
    private Long orgId;

    /**
     *  模板编号 - B2B_TSO.DOC_NO
     */
    private String docNo;

    /**
     *  模板类型 - B2B_TSO.DOC_TYPE
     */
    private String docType;

    /**
     *  模板状态 - B2B_TSO.DOC_STATUS
     */
    private String docStatus;

    /**
     *  生成时间 - B2B_TSO.DOC_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime docTime;

    /**
     *  客户ID - B2B_TSO.CUST_ID
     */
    private Long custId;

    /**
     *  支付条款 - B2B_TSO.PAYMENT_TERM
     */
    private String paymentTerm;

    /**
     *  货币码 - B2B_TSO.CURR_CODE
     */
    private String currCode;

    /**
     *  税率码 - B2B_TSO.TAX_CODE
     */
    private String taxCode;

    /**
     *  送货方式 - B2B_TSO.DELIVER_WAY
     */
    private String deliverWay;

    /**
     *  配送联系人 - B2B_TSO.DELIVER_CONTACT
     */
    private String deliverContact;

    /**
     *  配送地址ID - B2B_TSO.DELIVER_ADDRESS_ID
     */
    private Long deliverAddressId;

    /**
     *  备注 - B2B_TSO.REMARK
     */
    private String remark;

    /**
     *  创建人ID - B2B_TSO.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - B2B_TSO.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - B2B_TSO.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - B2B_TSO.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - B2B_TSO.AUDIT_DATA_VERSION
     */
    private BigDecimal auditDataVersion;

    /**
     *  是否删除 - B2B_TSO.DELETE_FLAG
     */
    private Short deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getOuId() {
        return ouId;
    }

    public void setOuId(Long ouId) {
        this.ouId = ouId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public LocalDateTime getDocTime() {
        return docTime;
    }

    public void setDocTime(LocalDateTime docTime) {
        this.docTime = docTime;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public String getDeliverContact() {
        return deliverContact;
    }

    public void setDeliverContact(String deliverContact) {
        this.deliverContact = deliverContact;
    }

    public Long getDeliverAddressId() {
        return deliverAddressId;
    }

    public void setDeliverAddressId(Long deliverAddressId) {
        this.deliverAddressId = deliverAddressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public BigDecimal getAuditDataVersion() {
        return auditDataVersion;
    }

    public void setAuditDataVersion(BigDecimal auditDataVersion) {
        this.auditDataVersion = auditDataVersion;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", ouId=").append(ouId);
        sb.append(", orgId=").append(orgId);
        sb.append(", docNo=").append(docNo);
        sb.append(", docType=").append(docType);
        sb.append(", docStatus=").append(docStatus);
        sb.append(", docTime=").append(docTime);
        sb.append(", custId=").append(custId);
        sb.append(", paymentTerm=").append(paymentTerm);
        sb.append(", currCode=").append(currCode);
        sb.append(", taxCode=").append(taxCode);
        sb.append(", deliverWay=").append(deliverWay);
        sb.append(", deliverContact=").append(deliverContact);
        sb.append(", deliverAddressId=").append(deliverAddressId);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append("]");
        return sb.toString();
    }
}