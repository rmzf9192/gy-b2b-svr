package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TPbAddrQualfy {
    /**
     *  主键 - PB_ADDR_QUALIFY.ID
     */
    private Long id;

    /**
     *  租户ID - PB_ADDR_QUALIFY.TENANT_ID
     */
    private Long tenantId;

    /**
     *  地址号 - PB_ADDR_QUALIFY.ADDR_NO
     */
    private String addrNo;

    /**
     *  行号 - PB_ADDR_QUALIFY.LINE_NO
     */
    private Long lineNo;

    /**
     *  资质类型 - PB_ADDR_QUALIFY.QUALIFY_TYPE
     */
    private String qualifyType;

    /**
     *  资质名称 - PB_ADDR_QUALIFY.QUALIFY_NAME
     */
    private String qualifyName;

    /**
     *  资质文件编号 - PB_ADDR_QUALIFY.QUALIFY_NO
     */
    private String qualifyNo;

    /**
     *  资质文件地址 - PB_ADDR_QUALIFY.QUALIFY_ADDRESS
     */
    private String qualifyAddress;

    /**
     *  生效时间 - PB_ADDR_QUALIFY.VALID_FROM
     */
    @TimeUtil.TIME
    private LocalDateTime validFrom;

    /**
     *  失效时间 - PB_ADDR_QUALIFY.VALID_TO
     */
    @TimeUtil.TIME
    private LocalDateTime validTo;

    /**
     *  下次检验日期 - PB_ADDR_QUALIFY.NEXT_CHECK_DATE
     */
    @TimeUtil.TIME
    private LocalDate nextCheckDate;

    /**
     *  备注 - PB_ADDR_QUALIFY.REMARK
     */
    private String remark;

    /**
     *  创建人ID - PB_ADDR_QUALIFY.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_ADDR_QUALIFY.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ADDR_QUALIFY.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_ADDR_QUALIFY.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_ADDR_QUALIFY.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_ADDR_QUALIFY.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  附件仓库名称 - PB_ADDR_QUALIFY.ATTACH_REPO
     */
    private String attachRepo;

    /**
     *  I类经营范围 - PB_ADDR_QUALIFY.VALID_LV1
     */
    private String validLv1;

    /**
     *  II类经营范围 - PB_ADDR_QUALIFY.VALID_LV2
     */
    private String validLv2;

    /**
     *  III类经营范围 - PB_ADDR_QUALIFY.VALID_LV3
     */
    private String validLv3;

    /**
     *  证照状态 - PB_ADDR_QUALIFY.CERT_STATUS
     */
    private String certStatus;

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

    public String getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(String addrNo) {
        this.addrNo = addrNo;
    }

    public Long getLineNo() {
        return lineNo;
    }

    public void setLineNo(Long lineNo) {
        this.lineNo = lineNo;
    }

    public String getQualifyType() {
        return qualifyType;
    }

    public void setQualifyType(String qualifyType) {
        this.qualifyType = qualifyType;
    }

    public String getQualifyName() {
        return qualifyName;
    }

    public void setQualifyName(String qualifyName) {
        this.qualifyName = qualifyName;
    }

    public String getQualifyNo() {
        return qualifyNo;
    }

    public void setQualifyNo(String qualifyNo) {
        this.qualifyNo = qualifyNo;
    }

    public String getQualifyAddress() {
        return qualifyAddress;
    }

    public void setQualifyAddress(String qualifyAddress) {
        this.qualifyAddress = qualifyAddress;
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

    public LocalDate getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(LocalDate nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
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

    public String getAttachRepo() {
        return attachRepo;
    }

    public void setAttachRepo(String attachRepo) {
        this.attachRepo = attachRepo;
    }

    public String getValidLv1() {
        return validLv1;
    }

    public void setValidLv1(String validLv1) {
        this.validLv1 = validLv1;
    }

    public String getValidLv2() {
        return validLv2;
    }

    public void setValidLv2(String validLv2) {
        this.validLv2 = validLv2;
    }

    public String getValidLv3() {
        return validLv3;
    }

    public void setValidLv3(String validLv3) {
        this.validLv3 = validLv3;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", addrNo=").append(addrNo);
        sb.append(", lineNo=").append(lineNo);
        sb.append(", qualifyType=").append(qualifyType);
        sb.append(", qualifyName=").append(qualifyName);
        sb.append(", qualifyNo=").append(qualifyNo);
        sb.append(", qualifyAddress=").append(qualifyAddress);
        sb.append(", validFrom=").append(validFrom);
        sb.append(", validTo=").append(validTo);
        sb.append(", nextCheckDate=").append(nextCheckDate);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", attachRepo=").append(attachRepo);
        sb.append(", validLv1=").append(validLv1);
        sb.append(", validLv2=").append(validLv2);
        sb.append(", validLv3=").append(validLv3);
        sb.append(", certStatus=").append(certStatus);
        sb.append("]");
        return sb.toString();
    }
}