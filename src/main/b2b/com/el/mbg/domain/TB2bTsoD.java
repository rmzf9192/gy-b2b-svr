package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TB2bTsoD {
    /**
     *  主键 - B2B_TSO_D.ID
     */
    private Long id;

    /**
     *  租户ID - B2B_TSO_D.TENANT_ID
     */
    private Long tenantId;

    /**
     *  模板ID - B2B_TSO_D.TSO_ID
     */
    private Long tsoId;

    /**
     *  行号 - B2B_TSO_D.LINE_NO
     */
    private BigDecimal lineNo;

    /**
     *  出库仓库ID - B2B_TSO_D.WH_ID
     */
    private Long whId;

    /**
     *  品项ID - B2B_TSO_D.ITEM_ID
     */
    private Long itemId;

    /**
     *  SKUID - B2B_TSO_D.SKU_ID
     */
    private Long skuId;

    /**
     *  行类型 - B2B_TSO_D.LINE_TYPE
     */
    private String lineType;

    /**
     *  数量 - B2B_TSO_D.QTY
     */
    private BigDecimal qty;

    /**
     *  单位 - B2B_TSO_D.UOM
     */
    private String uom;

    /**
     *  备注 - B2B_TSO_D.REMARK
     */
    private String remark;

    /**
     *  创建人ID - B2B_TSO_D.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - B2B_TSO_D.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - B2B_TSO_D.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - B2B_TSO_D.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - B2B_TSO_D.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - B2B_TSO_D.DELETE_FLAG
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

    public Long getTsoId() {
        return tsoId;
    }

    public void setTsoId(Long tsoId) {
        this.tsoId = tsoId;
    }

    public BigDecimal getLineNo() {
        return lineNo;
    }

    public void setLineNo(BigDecimal lineNo) {
        this.lineNo = lineNo;
    }

    public Long getWhId() {
        return whId;
    }

    public void setWhId(Long whId) {
        this.whId = whId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
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

    public Long getAuditDataVersion() {
        return auditDataVersion;
    }

    public void setAuditDataVersion(Long auditDataVersion) {
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
        sb.append(", tsoId=").append(tsoId);
        sb.append(", lineNo=").append(lineNo);
        sb.append(", whId=").append(whId);
        sb.append(", itemId=").append(itemId);
        sb.append(", skuId=").append(skuId);
        sb.append(", lineType=").append(lineType);
        sb.append(", qty=").append(qty);
        sb.append(", uom=").append(uom);
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