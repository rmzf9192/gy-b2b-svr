package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class THomeOu {
    /**
     *  主键 - HOME_OU.ID
     */
    private Integer id;

    /**
     *  租户ID - HOME_OU.TENANT_ID
     */
    private Integer tenantId;

    /**
     *  公司编码 - HOME_OU.OU_CODE
     */
    private String ouCode;

    /**
     *  公司名称 - HOME_OU.OU_NAME
     */
    private String ouName;

    /**
     *  开户银行 - HOME_OU.BANK
     */
    private String bank;

    /**
     *  账号 - HOME_OU.BANK_ACC
     */
    private String bankAcc;

    /**
     *  税号 - HOME_OU.TAX_NO
     */
    private String taxNo;

    /**
     *  公司地址 - HOME_OU.OU_ADDR
     */
    private String ouAddr;

    /**
     *  公司地址2 - HOME_OU.OU_ADDR2
     */
    private String ouAddr2;

    /**
     *  公司地址3 - HOME_OU.OU_ADDR3
     */
    private String ouAddr3;

    /**
     *  仓库地址 - HOME_OU.WAREHOUSE_ADDR
     */
    private String warehouseAddr;

    /**
     *  仓库地址2 - HOME_OU.WAREHOUSE_ADDR2
     */
    private String warehouseAddr2;

    /**
     *  仓库地址3 - HOME_OU.WAREHOUSE_ADDR3
     */
    private String warehouseAddr3;

    /**
     *  图片url - HOME_OU.image_url
     */
    private String imageUrl;

    /**
     *  图片url2 - HOME_OU.image_url2
     */
    private String imageUrl2;

    /**
     *  图片url3 - HOME_OU.image_url3
     */
    private String imageUrl3;

    /**
     *  图片url4 - HOME_OU.image_url4
     */
    private String imageUrl4;

    /**
     *  图片url5 - HOME_OU.image_url5
     */
    private String imageUrl5;

    /**
     *  备注 - HOME_OU.REMARK
     */
    private String remark;

    /**
     *  是否删除 - HOME_OU.DELETE_FLAG
     */
    private Integer deleteFlag;

    /**
     *  创建人ID - HOME_OU.CREATE_USER_ID
     */
    private Integer createUserId;

    /**
     *  最后编辑人ID - HOME_OU.MODIFY_USER_ID
     */
    private Integer modifyUserId;

    /**
     *  创建时间 - HOME_OU.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑时间 - HOME_OU.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - HOME_OU.AUDIT_DATA_VERSION
     */
    private Integer auditDataVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getOuAddr() {
        return ouAddr;
    }

    public void setOuAddr(String ouAddr) {
        this.ouAddr = ouAddr;
    }

    public String getOuAddr2() {
        return ouAddr2;
    }

    public void setOuAddr2(String ouAddr2) {
        this.ouAddr2 = ouAddr2;
    }

    public String getOuAddr3() {
        return ouAddr3;
    }

    public void setOuAddr3(String ouAddr3) {
        this.ouAddr3 = ouAddr3;
    }

    public String getWarehouseAddr() {
        return warehouseAddr;
    }

    public void setWarehouseAddr(String warehouseAddr) {
        this.warehouseAddr = warehouseAddr;
    }

    public String getWarehouseAddr2() {
        return warehouseAddr2;
    }

    public void setWarehouseAddr2(String warehouseAddr2) {
        this.warehouseAddr2 = warehouseAddr2;
    }

    public String getWarehouseAddr3() {
        return warehouseAddr3;
    }

    public void setWarehouseAddr3(String warehouseAddr3) {
        this.warehouseAddr3 = warehouseAddr3;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getAuditDataVersion() {
        return auditDataVersion;
    }

    public void setAuditDataVersion(Integer auditDataVersion) {
        this.auditDataVersion = auditDataVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", ouCode=").append(ouCode);
        sb.append(", ouName=").append(ouName);
        sb.append(", bank=").append(bank);
        sb.append(", bankAcc=").append(bankAcc);
        sb.append(", taxNo=").append(taxNo);
        sb.append(", ouAddr=").append(ouAddr);
        sb.append(", ouAddr2=").append(ouAddr2);
        sb.append(", ouAddr3=").append(ouAddr3);
        sb.append(", warehouseAddr=").append(warehouseAddr);
        sb.append(", warehouseAddr2=").append(warehouseAddr2);
        sb.append(", warehouseAddr3=").append(warehouseAddr3);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", imageUrl2=").append(imageUrl2);
        sb.append(", imageUrl3=").append(imageUrl3);
        sb.append(", imageUrl4=").append(imageUrl4);
        sb.append(", imageUrl5=").append(imageUrl5);
        sb.append(", remark=").append(remark);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append("]");
        return sb.toString();
    }
}