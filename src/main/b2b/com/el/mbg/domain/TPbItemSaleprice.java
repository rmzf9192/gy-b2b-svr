package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TPbItemSaleprice {
    /**
     *  主键 - PB_ITEM_SALEPRICE.ID
     */
    private Long id;

    /**
     *  租户ID - PB_ITEM_SALEPRICE.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司ID - PB_ITEM_SALEPRICE.OU_ID
     */
    private Long ouId;

    /**
     *  组织ID - PB_ITEM_SALEPRICE.ORG_ID
     */
    private Long orgId;

    /**
     *  价格类型 - PB_ITEM_SALEPRICE.PRICE_TYPE
     */
    private String priceType;

    /**
     *  价格类型2 - PB_ITEM_SALEPRICE.PRICE_TYPE2
     */
    private String priceType2;

    /**
     *  价格类型3 - PB_ITEM_SALEPRICE.PRICE_TYPE3
     */
    private String priceType3;

    /**
     *  品项ID - PB_ITEM_SALEPRICE.ITEM_ID
     */
    private Long itemId;

    /**
     *  客户组别 - PB_ITEM_SALEPRICE.CUST_GROUP
     */
    private String custGroup;

    /**
     *  客户ID - PB_ITEM_SALEPRICE.CUST_ID
     */
    private Long custId;

    /**
     *  销售区域 - PB_ITEM_SALEPRICE.SALE_REGION
     */
    private String saleRegion;

    /**
     *  价格组 - PB_ITEM_SALEPRICE.PRICE_GROUP
     */
    private String priceGroup;

    /**
     *  配送地址号 - PB_ITEM_SALEPRICE.SHIPTO_ADDR_NO
     */
    private Long shiptoAddrNo;

    /**
     *  价格 - PB_ITEM_SALEPRICE.PRICE
     */
    private BigDecimal price;

    /**
     *  单位 - PB_ITEM_SALEPRICE.UOM
     */
    private String uom;

    /**
     *  价格单位 - PB_ITEM_SALEPRICE.PRICE_UOM
     */
    private String priceUom;

    /**
     *  币种 - PB_ITEM_SALEPRICE.CURR_CODE
     */
    private String currCode;

    /**
     *  生效时间 - PB_ITEM_SALEPRICE.VALID_FROM
     */
    @TimeUtil.TIME
    private LocalDateTime validFrom;

    /**
     *  失效时间 - PB_ITEM_SALEPRICE.VALID_TO
     */
    @TimeUtil.TIME
    private LocalDateTime validTo;

    /**
     *  从数量 - PB_ITEM_SALEPRICE.FROM_QTY
     */
    private BigDecimal fromQty;

    /**
     *  到数量 - PB_ITEM_SALEPRICE.TO_QTY
     */
    private BigDecimal toQty;

    /**
     *  调价允差 - PB_ITEM_SALEPRICE.TOLERANCE
     */
    private BigDecimal tolerance;

    /**
     *  备注 - PB_ITEM_SALEPRICE.REMARK
     */
    private String remark;

    /**
     *  创建人ID - PB_ITEM_SALEPRICE.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_ITEM_SALEPRICE.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ITEM_SALEPRICE.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_ITEM_SALEPRICE.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_ITEM_SALEPRICE.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_ITEM_SALEPRICE.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  品项大类编号 - PB_ITEM_SALEPRICE.ITEM_C1
     */
    private String itemC1;

    /**
     *  品项中类编号 - PB_ITEM_SALEPRICE.ITEM_C2
     */
    private String itemC2;

    /**
     *  品项小类编号 - PB_ITEM_SALEPRICE.ITEM_C3
     */
    private String itemC3;

    /**
     *  商品价格状态 - PB_ITEM_SALEPRICE.PRICE_STATUS
     */
    private String priceStatus;

    /**
     *  商品编码 - PB_ITEM_SALEPRICE.ITEM_CODE
     */
    private String itemCode;

    /**
     *  客户编码 - PB_ITEM_SALEPRICE.CUST_CODE
     */
    private String custCode;

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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceType2() {
        return priceType2;
    }

    public void setPriceType2(String priceType2) {
        this.priceType2 = priceType2;
    }

    public String getPriceType3() {
        return priceType3;
    }

    public void setPriceType3(String priceType3) {
        this.priceType3 = priceType3;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getSaleRegion() {
        return saleRegion;
    }

    public void setSaleRegion(String saleRegion) {
        this.saleRegion = saleRegion;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Long getShiptoAddrNo() {
        return shiptoAddrNo;
    }

    public void setShiptoAddrNo(Long shiptoAddrNo) {
        this.shiptoAddrNo = shiptoAddrNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPriceUom() {
        return priceUom;
    }

    public void setPriceUom(String priceUom) {
        this.priceUom = priceUom;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public BigDecimal getFromQty() {
        return fromQty;
    }

    public void setFromQty(BigDecimal fromQty) {
        this.fromQty = fromQty;
    }

    public BigDecimal getToQty() {
        return toQty;
    }

    public void setToQty(BigDecimal toQty) {
        this.toQty = toQty;
    }

    public BigDecimal getTolerance() {
        return tolerance;
    }

    public void setTolerance(BigDecimal tolerance) {
        this.tolerance = tolerance;
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

    public String getItemC1() {
        return itemC1;
    }

    public void setItemC1(String itemC1) {
        this.itemC1 = itemC1;
    }

    public String getItemC2() {
        return itemC2;
    }

    public void setItemC2(String itemC2) {
        this.itemC2 = itemC2;
    }

    public String getItemC3() {
        return itemC3;
    }

    public void setItemC3(String itemC3) {
        this.itemC3 = itemC3;
    }

    public String getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(String priceStatus) {
        this.priceStatus = priceStatus;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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
        sb.append(", priceType=").append(priceType);
        sb.append(", priceType2=").append(priceType2);
        sb.append(", priceType3=").append(priceType3);
        sb.append(", itemId=").append(itemId);
        sb.append(", custGroup=").append(custGroup);
        sb.append(", custId=").append(custId);
        sb.append(", saleRegion=").append(saleRegion);
        sb.append(", priceGroup=").append(priceGroup);
        sb.append(", shiptoAddrNo=").append(shiptoAddrNo);
        sb.append(", price=").append(price);
        sb.append(", uom=").append(uom);
        sb.append(", priceUom=").append(priceUom);
        sb.append(", currCode=").append(currCode);
        sb.append(", validFrom=").append(validFrom);
        sb.append(", validTo=").append(validTo);
        sb.append(", fromQty=").append(fromQty);
        sb.append(", toQty=").append(toQty);
        sb.append(", tolerance=").append(tolerance);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", itemC1=").append(itemC1);
        sb.append(", itemC2=").append(itemC2);
        sb.append(", itemC3=").append(itemC3);
        sb.append(", priceStatus=").append(priceStatus);
        sb.append(", itemCode=").append(itemCode);
        sb.append(", custCode=").append(custCode);
        sb.append("]");
        return sb.toString();
    }
}