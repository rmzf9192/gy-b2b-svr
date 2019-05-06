package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TPbCust {
    /**
     *  主键 - PB_CUST.ID
     */
    private Long id;

    /**
     *  租户ID - PB_CUST.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司ID - PB_CUST.OU_ID
     */
    private Long ouId;

    /**
     *  组织ID - PB_CUST.ORG_ID
     */
    private Long orgId;

    /**
     *  客户编号 - PB_CUST.CUST_CODE
     */
    private String custCode;

    /**
     *  客户名称 - PB_CUST.CUST_NAME
     */
    private String custName;

    /**
     *  客户简称 - PB_CUST.CUST_ABBR
     */
    private String custAbbr;

    /**
     *  客户类型 - PB_CUST.CUST_TYPE
     */
    private String custType;

    /**
     *  客户状态 - PB_CUST.CUST_STATUS
     */
    private String custStatus;

    /**
     *  上级ID - PB_CUST.PID
     */
    private Long pid;

    /**
     *  地址号 - PB_CUST.ADDR_NO
     */
    private Long addrNo;

    /**
     *  拼音 - PB_CUST.PINYIN
     */
    private String pinyin;

    /**
     *  拼音简称 - PB_CUST.PINYIN_SH
     */
    private String pinyinSh;

    /**
     *  资金能力 - PB_CUST.COMP_CAPITAL
     */
    private String compCapital;

    /**
     *  业务员员工ID - PB_CUST.AGENT_EMP_ID
     */
    private Long agentEmpId;

    /**
     *  结算币种 - PB_CUST.CUST_CURR
     */
    private String custCurr;

    /**
     *  信用额度 - PB_CUST.CREDIT_LIMIT
     */
    private Long creditLimit;

    /**
     *  信用余额 - PB_CUST.CREDIT_BAL
     */
    private Long creditBal;

    /**
     *  支付条款 - PB_CUST.PAYMENT_TERM
     */
    private String paymentTerm;

    /**
     *  结算方式 - PB_CUST.SETTLE_TYPE
     */
    private String settleType;

    /**
     *  对账周期 - PB_CUST.RECON_PERIOD
     */
    private String reconPeriod;

    /**
     *  月结日期 - PB_CUST.SETTLE_MONTHLY_DAY
     */
    private Long settleMonthlyDay;

    /**
     *  默认组织ID - PB_CUST.DEF_ORG_ID
     */
    private Long defOrgId;

    /**
     *  默认仓库ID - PB_CUST.DEF_WH_ID
     */
    private Long defWhId;

    /**
     *  行业大类 - PB_CUST.C1
     */
    private String c1;

    /**
     *  行业中类 - PB_CUST.C2
     */
    private String c2;

    /**
     *  行业小类 - PB_CUST.C3
     */
    private String c3;

    /**
     *  国家 - PB_CUST.COUNTRY
     */
    private String country;

    /**
     *  邮编 - PB_CUST.POSTCODE
     */
    private String postcode;

    /**
     *  区域 - PB_CUST.REGION
     */
    private String region;

    /**
     *  客户等级 - PB_CUST.CUST_LEVEL
     */
    private String custLevel;

    /**
     *  客户组别 - PB_CUST.CUST_GROUP
     */
    private String custGroup;

    /**
     *  会员号码 - PB_CUST.VIP_NO
     */
    private String vipNo;

    /**
     *  会员级别 - PB_CUST.VIP_LEVEL
     */
    private String vipLevel;

    /**
     *  会员组别 - PB_CUST.VIP_GROUP
     */
    private String vipGroup;

    /**
     *  是否积分 - PB_CUST.CUST_POINT_FLAG
     */
    private Short custPointFlag;

    /**
     *  积分余额 - PB_CUST.POINT
     */
    private BigDecimal point;

    /**
     *  客户来源 - PB_CUST.CUST_SOURCE
     */
    private String custSource;

    /**
     *  生效日期 - PB_CUST.VALID_FROM
     */
    @TimeUtil.TIME
    private LocalDateTime validFrom;

    /**
     *  失效日期 - PB_CUST.VALID_TO
     */
    @TimeUtil.TIME
    private LocalDateTime validTo;

    /**
     *  发票类型 - PB_CUST.INV_TYPE
     */
    private String invType;

    /**
     *  开票抬头 - PB_CUST.INV_TITLE
     */
    private String invTitle;

    /**
     *  发票税号 - PB_CUST.INV_TAXNO
     */
    private String invTaxno;

    /**
     *  税码 - PB_CUST.TAX_CODE
     */
    private String taxCode;

    /**
     *  工商登记号 - PB_CUST.IC_REGISTER_NO
     */
    private String icRegisterNo;

    /**
     *  注册日期 - PB_CUST.REGISTER_DATE
     */
    @TimeUtil.TIME
    private LocalDate registerDate;

    /**
     *  注册地址 - PB_CUST.REGISTER_ADDRESS
     */
    private String registerAddress;

    /**
     *  注册商标编号 - PB_CUST.REGISTER_TRADEMARK_NO
     */
    private String registerTrademarkNo;

    /**
     *  注册资金 - PB_CUST.REGISTER_FUND
     */
    private String registerFund;

    /**
     *  注册资金货币 - PB_CUST.REGISTER_FUND_CURRY
     */
    private String registerFundCurry;

    /**
     *  法人代表 - PB_CUST.REPR
     */
    private String repr;

    /**
     *  公司名称 - PB_CUST.COMP_NAME
     */
    private String compName;

    /**
     *  公司性质 - PB_CUST.COMP_PROP
     */
    private String compProp;

    /**
     *  公司规模 - PB_CUST.COMP_SCALE
     */
    private String compScale;

    /**
     *  经营地点 - PB_CUST.COMP_BUSSADDR
     */
    private String compBussaddr;

    /**
     *  主营业务 - PB_CUST.COMP_MAINBUSS
     */
    private String compMainbuss;

    /**
     *  经营范围 - PB_CUST.COMP_BUSSRANGE
     */
    private String compBussrange;

    /**
     *  备注 - PB_CUST.REMARK
     */
    private String remark;

    /**
     *  null - PB_CUST.OUTER_CODE
     */
    private String outerCode;

    /**
     *  创建人ID - PB_CUST.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_CUST.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_CUST.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_CUST.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_CUST.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_CUST.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  付款方式 - PB_CUST.PAY_METHOD
     */
    private String payMethod;

    /**
     *  纳税人类型 - PB_CUST.TAXPAYER_TYPE
     */
    private String taxpayerType;

    /**
     *  公司编码 - PB_CUST.OU_CODE
     */
    private String ouCode;

    private String unitFlag;

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

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAbbr() {
        return custAbbr;
    }

    public void setCustAbbr(String custAbbr) {
        this.custAbbr = custAbbr;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(Long addrNo) {
        this.addrNo = addrNo;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyinSh() {
        return pinyinSh;
    }

    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh;
    }

    public String getCompCapital() {
        return compCapital;
    }

    public void setCompCapital(String compCapital) {
        this.compCapital = compCapital;
    }

    public Long getAgentEmpId() {
        return agentEmpId;
    }

    public void setAgentEmpId(Long agentEmpId) {
        this.agentEmpId = agentEmpId;
    }

    public String getCustCurr() {
        return custCurr;
    }

    public void setCustCurr(String custCurr) {
        this.custCurr = custCurr;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Long getCreditBal() {
        return creditBal;
    }

    public void setCreditBal(Long creditBal) {
        this.creditBal = creditBal;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getReconPeriod() {
        return reconPeriod;
    }

    public void setReconPeriod(String reconPeriod) {
        this.reconPeriod = reconPeriod;
    }

    public Long getSettleMonthlyDay() {
        return settleMonthlyDay;
    }

    public void setSettleMonthlyDay(Long settleMonthlyDay) {
        this.settleMonthlyDay = settleMonthlyDay;
    }

    public Long getDefOrgId() {
        return defOrgId;
    }

    public void setDefOrgId(Long defOrgId) {
        this.defOrgId = defOrgId;
    }

    public Long getDefWhId() {
        return defWhId;
    }

    public void setDefWhId(Long defWhId) {
        this.defWhId = defWhId;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getVipGroup() {
        return vipGroup;
    }

    public void setVipGroup(String vipGroup) {
        this.vipGroup = vipGroup;
    }

    public Short getCustPointFlag() {
        return custPointFlag;
    }

    public void setCustPointFlag(Short custPointFlag) {
        this.custPointFlag = custPointFlag;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
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

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvTitle() {
        return invTitle;
    }

    public void setInvTitle(String invTitle) {
        this.invTitle = invTitle;
    }

    public String getInvTaxno() {
        return invTaxno;
    }

    public void setInvTaxno(String invTaxno) {
        this.invTaxno = invTaxno;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getIcRegisterNo() {
        return icRegisterNo;
    }

    public void setIcRegisterNo(String icRegisterNo) {
        this.icRegisterNo = icRegisterNo;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getRegisterTrademarkNo() {
        return registerTrademarkNo;
    }

    public void setRegisterTrademarkNo(String registerTrademarkNo) {
        this.registerTrademarkNo = registerTrademarkNo;
    }

    public String getRegisterFund() {
        return registerFund;
    }

    public void setRegisterFund(String registerFund) {
        this.registerFund = registerFund;
    }

    public String getRegisterFundCurry() {
        return registerFundCurry;
    }

    public void setRegisterFundCurry(String registerFundCurry) {
        this.registerFundCurry = registerFundCurry;
    }

    public String getRepr() {
        return repr;
    }

    public void setRepr(String repr) {
        this.repr = repr;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompProp() {
        return compProp;
    }

    public void setCompProp(String compProp) {
        this.compProp = compProp;
    }

    public String getCompScale() {
        return compScale;
    }

    public void setCompScale(String compScale) {
        this.compScale = compScale;
    }

    public String getCompBussaddr() {
        return compBussaddr;
    }

    public void setCompBussaddr(String compBussaddr) {
        this.compBussaddr = compBussaddr;
    }

    public String getCompMainbuss() {
        return compMainbuss;
    }

    public void setCompMainbuss(String compMainbuss) {
        this.compMainbuss = compMainbuss;
    }

    public String getCompBussrange() {
        return compBussrange;
    }

    public void setCompBussrange(String compBussrange) {
        this.compBussrange = compBussrange;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(String taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public String getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(String unitFlag) {
        this.unitFlag = unitFlag;
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
        sb.append(", custCode=").append(custCode);
        sb.append(", custName=").append(custName);
        sb.append(", custAbbr=").append(custAbbr);
        sb.append(", custType=").append(custType);
        sb.append(", custStatus=").append(custStatus);
        sb.append(", pid=").append(pid);
        sb.append(", addrNo=").append(addrNo);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", pinyinSh=").append(pinyinSh);
        sb.append(", compCapital=").append(compCapital);
        sb.append(", agentEmpId=").append(agentEmpId);
        sb.append(", custCurr=").append(custCurr);
        sb.append(", creditLimit=").append(creditLimit);
        sb.append(", creditBal=").append(creditBal);
        sb.append(", paymentTerm=").append(paymentTerm);
        sb.append(", settleType=").append(settleType);
        sb.append(", reconPeriod=").append(reconPeriod);
        sb.append(", settleMonthlyDay=").append(settleMonthlyDay);
        sb.append(", defOrgId=").append(defOrgId);
        sb.append(", defWhId=").append(defWhId);
        sb.append(", c1=").append(c1);
        sb.append(", c2=").append(c2);
        sb.append(", c3=").append(c3);
        sb.append(", country=").append(country);
        sb.append(", postcode=").append(postcode);
        sb.append(", region=").append(region);
        sb.append(", custLevel=").append(custLevel);
        sb.append(", custGroup=").append(custGroup);
        sb.append(", vipNo=").append(vipNo);
        sb.append(", vipLevel=").append(vipLevel);
        sb.append(", vipGroup=").append(vipGroup);
        sb.append(", custPointFlag=").append(custPointFlag);
        sb.append(", point=").append(point);
        sb.append(", custSource=").append(custSource);
        sb.append(", validFrom=").append(validFrom);
        sb.append(", validTo=").append(validTo);
        sb.append(", invType=").append(invType);
        sb.append(", invTitle=").append(invTitle);
        sb.append(", invTaxno=").append(invTaxno);
        sb.append(", taxCode=").append(taxCode);
        sb.append(", icRegisterNo=").append(icRegisterNo);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", registerAddress=").append(registerAddress);
        sb.append(", registerTrademarkNo=").append(registerTrademarkNo);
        sb.append(", registerFund=").append(registerFund);
        sb.append(", registerFundCurry=").append(registerFundCurry);
        sb.append(", repr=").append(repr);
        sb.append(", compName=").append(compName);
        sb.append(", compProp=").append(compProp);
        sb.append(", compScale=").append(compScale);
        sb.append(", compBussaddr=").append(compBussaddr);
        sb.append(", compMainbuss=").append(compMainbuss);
        sb.append(", compBussrange=").append(compBussrange);
        sb.append(", remark=").append(remark);
        sb.append(", outerCode=").append(outerCode);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", auditDataVersion=").append(auditDataVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", taxpayerType=").append(taxpayerType);
        sb.append(", ouCode=").append(ouCode);
        sb.append(", unitFlag=").append(unitFlag);
        sb.append("]");
        return sb.toString();
    }
}
