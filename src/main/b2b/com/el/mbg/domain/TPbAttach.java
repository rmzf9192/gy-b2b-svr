package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class TPbAttach {
    /**
     *  主键 - PB_ATTACH.ID
     */
    private Integer id;

    /**
     *  租户ID - PB_ATTACH.TENANT_ID
     */
    private Integer tenantId;

    /**
     *  实体类别 - PB_ATTACH.ENTRY_CLS
     */
    private String entryCls;

    /**
     *  实体ID - PB_ATTACH.ENTRY_ID
     */
    private Integer entryId;

    /**
     *  文件名 - PB_ATTACH.ATTACH_NAME
     */
    private String attachName;

    /**
     *  类型1 - PB_ATTACH.ATTACH_TYPE1
     */
    private String attachType1;

    /**
     *  类型2 - PB_ATTACH.ATTACH_TYPE2
     */
    private String attachType2;

    /**
     *  类型3 - PB_ATTACH.ATTACH_TYPE3
     */
    private String attachType3;

    /**
     *  文档数据类型 - PB_ATTACH.ATTACH_DATA_TYPE
     */
    private String attachDataType;

    /**
     *  文件路径 - PB_ATTACH.ATTACH_PATH
     */
    private String attachPath;

    /**
     *  上传时间 - PB_ATTACH.UPLOAD_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime uploadTime;

    /**
     *  上传用户ID - PB_ATTACH.UPLOAD_USER_ID
     */
    private Integer uploadUserId;

    /**
     *  上传IP地址 - PB_ATTACH.UPLOAD_USER_IP
     */
    private String uploadUserIp;

    /**
     *  备注 - PB_ATTACH.REMARK
     */
    private String remark;

    /**
     *  创建人ID - PB_ATTACH.CREATE_USER_ID
     */
    private Integer createUserId;

    /**
     *  创建时间 - PB_ATTACH.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ATTACH.MODIFY_USER_ID
     */
    private Integer modifyUserId;

    /**
     *  最后编辑时间 - PB_ATTACH.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_ATTACH.AUDIT_DATA_VERSION
     */
    private Integer auditDataVersion;

    /**
     *  是否删除 - PB_ATTACH.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  公司编码 - PB_ATTACH.OU_CODE
     */
    private String ouCode;

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

    public String getEntryCls() {
        return entryCls;
    }

    public void setEntryCls(String entryCls) {
        this.entryCls = entryCls;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachType1() {
        return attachType1;
    }

    public void setAttachType1(String attachType1) {
        this.attachType1 = attachType1;
    }

    public String getAttachType2() {
        return attachType2;
    }

    public void setAttachType2(String attachType2) {
        this.attachType2 = attachType2;
    }

    public String getAttachType3() {
        return attachType3;
    }

    public void setAttachType3(String attachType3) {
        this.attachType3 = attachType3;
    }

    public String getAttachDataType() {
        return attachDataType;
    }

    public void setAttachDataType(String attachDataType) {
        this.attachDataType = attachDataType;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserIp() {
        return uploadUserIp;
    }

    public void setUploadUserIp(String uploadUserIp) {
        this.uploadUserIp = uploadUserIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", entryCls=").append(entryCls);
        sb.append(", entryId=").append(entryId);
        sb.append(", attachName=").append(attachName);
        sb.append(", attachType1=").append(attachType1);
        sb.append(", attachType2=").append(attachType2);
        sb.append(", attachType3=").append(attachType3);
        sb.append(", attachDataType=").append(attachDataType);
        sb.append(", attachPath=").append(attachPath);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", uploadUserId=").append(uploadUserId);
        sb.append(", uploadUserIp=").append(uploadUserIp);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", ouCode=").append(ouCode);
        sb.append("]");
        return sb.toString();
    }
}