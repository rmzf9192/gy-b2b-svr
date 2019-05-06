package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class TPbAnnc {
    /**
     *  主键 - PB_ANNC.ID
     */
    private Integer id;

    /**
     *  租户ID - PB_ANNC.TENANT_ID
     */
    private Integer tenantId;

    /**
     *  主题 - PB_ANNC.ANNC_SUBJECT
     */
    private String anncSubject;

    /**
     *  内容 - PB_ANNC.ANNC_CONTENT
     */
    private String anncContent;

    /**
     *  接收者 - PB_ANNC.ANNC_TARGET
     */
    private String anncTarget;

    /**
     *  发布时间 - PB_ANNC.PUBLISH_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime publishTime;

    /**
     *  创建人ID - PB_ANNC.CREATE_USER_ID
     */
    private Integer createUserId;

    /**
     *  创建时间 - PB_ANNC.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ANNC.MODIFY_USER_ID
     */
    private Integer modifyUserId;

    /**
     *  最后编辑时间 - PB_ANNC.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_ANNC.AUDIT_DATA_VERSION
     */
    private Integer auditDataVersion;

    /**
     *  是否删除 - PB_ANNC.DELETE_FLAG
     */
    private Long deleteFlag;

    /**
     *  公司编码 - PB_ANNC.OU_CODE
     */
    private String ouCode;

    /**
     *  公告状态 - PB_ANNC.ANNC_STATUS
     */
    private Integer anncStatus;

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

    public String getAnncSubject() {
        return anncSubject;
    }

    public void setAnncSubject(String anncSubject) {
        this.anncSubject = anncSubject;
    }

    public String getAnncContent() {
        return anncContent;
    }

    public void setAnncContent(String anncContent) {
        this.anncContent = anncContent;
    }

    public String getAnncTarget() {
        return anncTarget;
    }

    public void setAnncTarget(String anncTarget) {
        this.anncTarget = anncTarget;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
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

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public Integer getAnncStatus() {
        return anncStatus;
    }

    public void setAnncStatus(Integer anncStatus) {
        this.anncStatus = anncStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", anncSubject=").append(anncSubject);
        sb.append(", anncContent=").append(anncContent);
        sb.append(", anncTarget=").append(anncTarget);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", ouCode=").append(ouCode);
        sb.append(", anncStatus=").append(anncStatus);
        sb.append("]");
        return sb.toString();
    }
}