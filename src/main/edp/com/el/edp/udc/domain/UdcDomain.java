package com.el.edp.udc.domain;

import com.el.core.util.TimeUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UdcDomain {
    /**
     *   - PB_UDC_TYPE.ID
     */
    private Long id;

    /**
     *  租户ID - PB_UDC_TYPE.TENANT_ID
     */
    private Long tenantId;

    /**
     *  领域代码 - PB_UDC_TYPE.DOMAIN_CODE
     */
    private String domainCode;

    /**
     *  编号 - PB_UDC_TYPE.UDC_CODE
     */
    private String udcCode;

    /**
     *  名称 - PB_UDC_TYPE.UDC_NAME
     */
    private String udcName;

    /**
     *  说明 - PB_UDC_TYPE.UDC_DESC
     */
    private String udcDesc;

    /**
     *  是否系统级 系统自带的，可能不允许用户修改 - PB_UDC_TYPE.SYS_FLAG
     */
    private Boolean sysFlag;

    /**
     *  创建人ID - PB_UDC_TYPE.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_UDC_TYPE.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_UDC_TYPE.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_UDC_TYPE.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_UDC_TYPE.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_UDC_TYPE.DELETE_FLAG
     */
    private Boolean deleteFlag;

}
