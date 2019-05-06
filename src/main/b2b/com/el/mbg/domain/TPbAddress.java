package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.time.LocalDateTime;

public class TPbAddress {
    /**
     *  主键 - PB_ADDRESS.ID
     */
    private Long id;

    /**
     *  租户ID - PB_ADDRESS.TENANT_ID
     */
    private Long tenantId;

    /**
     *  地址号 - PB_ADDRESS.ADDR_NO
     */
    private Long addrNo;

    /**
     *  行号 - PB_ADDRESS.LINE_NO
     */
    private Long lineNo;

    /**
     *  地址类型 - PB_ADDRESS.ADDRESS_TYPE
     */
    private String addressType;

    /**
     *  是否默认 - PB_ADDRESS.DEFAULT_FLAG
     */
    private String defaultFlag;

    /**
     *  地址状态 - PB_ADDRESS.ADDRESS_STATUS
     */
    private String addressStatus;

    /**
     *  联系人姓名 - PB_ADDRESS.CONT_PERSON
     */
    private String contPerson;

    /**
     *  手机 - PB_ADDRESS.MOBILE
     */
    private String mobile;

    /**
     *  手机2 - PB_ADDRESS.MOBILE2
     */
    private String mobile2;

    /**
     *  电话 - PB_ADDRESS.TEL
     */
    private String tel;

    /**
     *  电话2 - PB_ADDRESS.TEL2
     */
    private String tel2;

    /**
     *  传真 - PB_ADDRESS.FAX
     */
    private String fax;

    /**
     *  电邮 - PB_ADDRESS.EMAIL
     */
    private String email;

    /**
     *  电邮2 - PB_ADDRESS.EMAIL2
     */
    private String email2;

    /**
     *  国家 - PB_ADDRESS.COUNTRY
     */
    private String country;

    /**
     *  省 - PB_ADDRESS.PROVINCE
     */
    private String province;

    /**
     *  市 - PB_ADDRESS.CITY
     */
    private String city;

    /**
     *  县／区／镇 - PB_ADDRESS.PREFECTURE
     */
    private String prefecture;

    /**
     *  区／街道／村 - PB_ADDRESS.DISTRICT
     */
    private String district;

    /**
     *  详细地址 - PB_ADDRESS.DETAILADDR
     */
    private String detailaddr;

    /**
     *  邮编 - PB_ADDRESS.ZIPCODE
     */
    private String zipcode;

    /**
     *  网址 - PB_ADDRESS.WEBADDRESS
     */
    private String webaddress;

    /**
     *  微博 - PB_ADDRESS.WEIBO
     */
    private String weibo;

    /**
     *  微信公众号 - PB_ADDRESS.WECHAT_MP
     */
    private String wechatMp;

    /**
     *  社交 - PB_ADDRESS.SNS
     */
    private String sns;

    /**
     *  社交2 - PB_ADDRESS.SNS2
     */
    private String sns2;

    /**
     *  社交3 - PB_ADDRESS.SNS3
     */
    private String sns3;

    /**
     *  备注 - PB_ADDRESS.REMARK
     */
    private String remark;

    /**
     *  数据版本号 - PB_ADDRESS.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_ADDRESS.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  创建人ID - PB_ADDRESS.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_ADDRESS.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ADDRESS.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_ADDRESS.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  职位 - PB_ADDRESS.POSITION
     */
    private String position;

    /**
     *  上级联系人ID - PB_ADDRESS.PID
     */
    private Long pid;

    /**
     *  地址 - PB_ADDRESS.ADDR_NAME
     */
    private String addrName;

    /**
     *  客户号 - PB_ADDRESS.CUST_CODE
     */
    private String custCode;

    /**
     *  公司号 - PB_ADDRESS.OU_CODE
     */
    private String ouCode;

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

    public Long getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(Long addrNo) {
        this.addrNo = addrNo;
    }

    public Long getLineNo() {
        return lineNo;
    }

    public void setLineNo(Long lineNo) {
        this.lineNo = lineNo;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public String getContPerson() {
        return contPerson;
    }

    public void setContPerson(String contPerson) {
        this.contPerson = contPerson;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailaddr() {
        return detailaddr;
    }

    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getWebaddress() {
        return webaddress;
    }

    public void setWebaddress(String webaddress) {
        this.webaddress = webaddress;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWechatMp() {
        return wechatMp;
    }

    public void setWechatMp(String wechatMp) {
        this.wechatMp = wechatMp;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public String getSns2() {
        return sns2;
    }

    public void setSns2(String sns2) {
        this.sns2 = sns2;
    }

    public String getSns3() {
        return sns3;
    }

    public void setSns3(String sns3) {
        this.sns3 = sns3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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
        sb.append(", addrNo=").append(addrNo);
        sb.append(", lineNo=").append(lineNo);
        sb.append(", addressType=").append(addressType);
        sb.append(", defaultFlag=").append(defaultFlag);
        sb.append(", addressStatus=").append(addressStatus);
        sb.append(", contPerson=").append(contPerson);
        sb.append(", mobile=").append(mobile);
        sb.append(", mobile2=").append(mobile2);
        sb.append(", tel=").append(tel);
        sb.append(", tel2=").append(tel2);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", email2=").append(email2);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", prefecture=").append(prefecture);
        sb.append(", district=").append(district);
        sb.append(", detailaddr=").append(detailaddr);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", webaddress=").append(webaddress);
        sb.append(", weibo=").append(weibo);
        sb.append(", wechatMp=").append(wechatMp);
        sb.append(", sns=").append(sns);
        sb.append(", sns2=").append(sns2);
        sb.append(", sns3=").append(sns3);
        sb.append(", remark=").append(remark);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", position=").append(position);
        sb.append(", pid=").append(pid);
        sb.append(", addrName=").append(addrName);
        sb.append(", custCode=").append(custCode);
        sb.append(", ouCode=").append(ouCode);
        sb.append("]");
        return sb.toString();
    }
}
