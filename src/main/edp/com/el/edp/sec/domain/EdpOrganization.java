package com.el.edp.sec.domain;

import com.el.core.udc.UdcName;
import lombok.Data;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Data
public class EdpOrganization {
    /**
     *  ID - PB_ORG.ID
     */
    private Long id;

    /**
     *  公司ID - PB_ORG.OU_ID
     */
    private Long ouId;

    /**
     *  公司名称 - PB_OU.OU_NAME
     */
    private String ouName;

    /**
     *  组织编号 - PB_ORG.ORG_CODE
     */
    private String orgCode;

    /**
     *  组织名称 - PB_ORG.ORG_NAME
     */
    private String orgName;

    /**
     *  组织简称 - PB_ORG.ORG_ABBR
     */
    private String orgAbbr;

    /**
     *  组织类型 - PB_ORG.ORG_TYPE
     */
    private String orgType;

    @UdcName(udcName = "EDP.ORG_TYPE", codePropName = "orgType")
    private String orgTypeName;

    /**
     *  组织状态 - PB_ORG.ORG_STATUS
     */
    private String orgStatus;

    @UdcName(udcName = "EDP.ON_OFF_FLAG", codePropName = "orgStatus")
    private String orgStatusName;

    /**
     *  上级ID - PB_ORG.PID
     */
    private Long pid;

    /**
     *  地址号 - PB_ORG.ADDR_NO
     */
    private Long addrNo;

    /**
     *  组织层级 - PB_ORG.ORG_LEVEL
     */
    private String orgLevel;

    /**
     *  负责人员工ID - PB_ORG.PIC_EMP_ID
     */
    private Long picEmpId;

    /**
     *  备注 - PB_ORG.REMARK
     */
    private String remark;

    /**
     *  外部编码 - PB_ORG.OUTER_CODE
     */
    private String outerCode;

    /**
     *  是否删除 - PB_ORG.DELETE_FLAG
     */
    private String deleteFlag;
}
