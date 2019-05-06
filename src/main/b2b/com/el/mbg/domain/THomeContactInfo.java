package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class THomeContactInfo {
    /**
     *  主键 - HOME_CONTACT_INFO.ID
     */
    private Integer id;

    /**
     *  租户ID - HOME_CONTACT_INFO.TENANT_ID
     */
    private String tenantId;

    /**
     *  公司编码 - HOME_CONTACT_INFO.OU_CODE
     */
    private String ouCode;

    /**
     *  客户编码 - HOME_CONTACT_INFO.CUST_CODE
     */
    private String custCode;

    /**
     *  标题 - HOME_CONTACT_INFO.TITLE
     */
    private String title;

    /**
     *  联系人 - HOME_CONTACT_INFO.CONTACT
     */
    private String contact;

    /**
     *  电话 - HOME_CONTACT_INFO.TEL
     */
    private String tel;

    /**
     *  手机 - HOME_CONTACT_INFO.PHONE
     */
    private String phone;

    /**
     *  邮箱 - HOME_CONTACT_INFO.EMAIL
     */
    private String email;

    /**
     *  备注 - HOME_CONTACT_INFO.REMARK
     */
    private String remark;

    /**
     *  是否删除 - HOME_CONTACT_INFO.DELETE_FLAG
     */
    private Integer deleteFlag;

    /**
     *  创建人ID - HOME_CONTACT_INFO.CREATE_USER_ID
     */
    private Integer createUserId;

    /**
     *  最后编辑人ID - HOME_CONTACT_INFO.MODIFY_USER_ID
     */
    private Integer modifyUserId;

    /**
     *  创建时间 - HOME_CONTACT_INFO.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑时间 - HOME_CONTACT_INFO.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - HOME_CONTACT_INFO.AUDIT_DATA_VERSION
     */
    private Integer auditDataVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        sb.append(", custCode=").append(custCode);
        sb.append(", title=").append(title);
        sb.append(", contact=").append(contact);
        sb.append(", tel=").append(tel);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
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