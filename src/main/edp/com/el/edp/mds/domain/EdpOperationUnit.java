package com.el.edp.mds.domain;

import com.el.core.udc.UdcName;
import lombok.Data;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Data
public class EdpOperationUnit {
    /**
     *  ID - PB_OU.ID
     */
    private Long id;

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

    @UdcName(udcName = "EDP.OU_TYPE", codePropName = "ouType")
    private String ouTypeName;

    /**
     *  公司状态 - PB_OU.OU_STATUS
     */
    private String ouStatus;

    @UdcName(udcName = "EDP.ON_OFF_FLAG", codePropName = "ouStatus")
    private String ouStatusName;

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
     *  是否删除 - PB_OU.DELETE_FLAG
     */
    private String deleteFlag;
}
