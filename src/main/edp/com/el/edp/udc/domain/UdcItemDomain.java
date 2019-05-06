package com.el.edp.udc.domain;

import com.el.core.util.TimeUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UdcItemDomain {
    /**
     *   - PB_UDC.ID
     */
    private Long id;

    /**
     *  租户ID - PB_UDC.TENANT_ID
     */
    private Long tenantId;

    /**
     *  UDC类型ID - PB_UDC.UDC_TYPE_ID
     */
    private Long udcTypeId;

    /**
     *  领域代码 - PB_UDC.DOMAIN_CODE
     */
    private String domainCode;

    /**
     *  UDC编号 - PB_UDC.UDC_CODE
     */
    private String udcCode;

    /**
     *  值编号 - PB_UDC.VAL_CODE
     */
    private String valCode;

    /**
     *  语言 - PB_UDC.LANG
     */
    private String lang;

    /**
     *  含义 - PB_UDC.VAL_DESC
     */
    private String valDesc;

    /**
     *  含义2 - PB_UDC.VAL_DESC2
     */
    private String valDesc2;

    /**
     *  排序号 - PB_UDC.SORT_NO
     */
    private Double sortNo;

    /**
     *  是否有效 - PB_UDC.ENABLE_FLAG
     */
    private Boolean enableFlag;

    /**
     *  特殊处理码1 - PB_UDC.VAL_SPHD1
     */
    private String valSphd1;

    /**
     *  特殊处理码2 - PB_UDC.VAL_SPHD2
     */
    private String valSphd2;

    /**
     *  特殊处理码3 - PB_UDC.VAL_SPHD3
     */
    private String valSphd3;

    /**
     *  特殊处理码4 - PB_UDC.VAL_SPHD4
     */
    private String valSphd4;

    /**
     *  特殊处理码5 - PB_UDC.VAL_SPHD5
     */
    private String valSphd5;

    /**
     *  特殊处理码6 - PB_UDC.VAL_SPHD6
     */
    private String valSphd6;

    /**
     *  特殊处理码7 - PB_UDC.VAL_SPHD7
     */
    private String valSphd7;

    /**
     *  特殊处理码8 - PB_UDC.VAL_SPHD8
     */
    private String valSphd8;

    /**
     *  特殊处理码9 - PB_UDC.VAL_SPHD9
     */
    private String valSphd9;

    /**
     *  特殊处理码10 - PB_UDC.VAL_SPHD10
     */
    private String valSphd10;

    /**
     *  上级ID - PB_UDC.PID
     */
    private Long pid;

    /**
     *  最后编辑人ID - PB_UDC.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_UDC.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_UDC.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  创建人ID - PB_UDC.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_UDC.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  是否删除 - PB_UDC.DELETE_FLAG
     */
    private Boolean deleteFlag;

}
