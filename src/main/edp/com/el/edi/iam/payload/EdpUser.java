package com.el.edi.iam.payload;

import com.el.edi.EdpPayload;
import com.el.edp.sec.domain.EdpAuthField;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.domain.EdpEntType;

/**
 * @author neo.pan
 * @since 17/8/9
 */
public interface EdpUser extends EdpPayload {

    /**
     * ID
     */
    Long getId();

    /*------------------+
     |   以下为权限属性   |
     +=================*/

    /**
     * 租户ID
     */
    Long getTenantId();

    /**
     * 权限层级
     */
    EdpAuthLayer getAuthLayer();

    /**
     * 权限领域
     */
    EdpAuthField getAuthField();

    /*------------------+
     |   以下为业务属性   |
     +=================*/

    /**
     * 企业类型
     */
    EdpEntType getEntType();

    /**
     * 企业ID
     */
    Long getEntId();

    /**
     * 企业号
     */
    String getEntCode();

    /**
     * 企业地址号
     */
    Long getEntAddr();

    /**
     * 员工ID
     */
    Long getEmpId();

    /**
     * 员工号(即所属企业内用户名)
     */
    String getEmpCode();

    /**
     * 员工地址号
     */
    Long getEmpAddr();

    /**
     * 用户名称(显示用)
     */
    String getName();

    /**
     * 手机
     */
    String getPhone();

    /**
     * 邮箱
     */
    String getEmail();
}
