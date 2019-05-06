package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class TPbOu {
    /**
     *  主键 - PB_OU.ID
     */
    private Long id;

    /**
     *  租户ID - PB_OU.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司编号 - PB_OU.OU_CODE
     */
    private String ouCode;

    /**
     *  公司名称 - PB_OU.OU_NAME
     */
    private String ouName;

    /**
     *  公司简称 - PB_OU.OU_ABBR
     */
    private String ouAbbr;

    /**
     *  公司类型 - PB_OU.OU_TYPE
     */
    private String ouType;

    /**
     *  公司状态 - PB_OU.OU_STATUS
     */
    private String ouStatus;

    /**
     *  上级ID - PB_OU.PID
     */
    private Long pid;

    /**
     *  本位币 - PB_OU.OU_CURR
     */
    private String ouCurr;

    /**
     *  地址号 - PB_OU.ADDR_NO
     */
    private Long addrNo;

    /**
     *  区域 - PB_OU.REGION
     */
    private String region;

    /**
     *  备注 - PB_OU.REMARK
     */
    private String remark;

    /**
     *  外部编码 - PB_OU.OUTER_CODE
     */
    private String outerCode;

    /**
     *  创建人ID - PB_OU.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_OU.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_OU.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_OU.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_OU.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_OU.DELETE_FLAG
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

    public String getOuAbbr() {
        return ouAbbr;
    }

    public void setOuAbbr(String ouAbbr) {
        this.ouAbbr = ouAbbr;
    }

    public String getOuType() {
        return ouType;
    }

    public void setOuType(String ouType) {
        this.ouType = ouType;
    }

    public String getOuStatus() {
        return ouStatus;
    }

    public void setOuStatus(String ouStatus) {
        this.ouStatus = ouStatus;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOuCurr() {
        return ouCurr;
    }

    public void setOuCurr(String ouCurr) {
        this.ouCurr = ouCurr;
    }

    public Long getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(Long addrNo) {
        this.addrNo = addrNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOuterCode() {
        return outerCode;
    }

    public void setOuterCode(String outerCode) {
        this.outerCode = outerCode;
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
        sb.append(", ouCode=").append(ouCode);
        sb.append(", ouName=").append(ouName);
        sb.append(", ouAbbr=").append(ouAbbr);
        sb.append(", ouType=").append(ouType);
        sb.append(", ouStatus=").append(ouStatus);
        sb.append(", pid=").append(pid);
        sb.append(", ouCurr=").append(ouCurr);
        sb.append(", addrNo=").append(addrNo);
        sb.append(", region=").append(region);
        sb.append(", remark=").append(remark);
        sb.append(", outerCode=").append(outerCode);
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