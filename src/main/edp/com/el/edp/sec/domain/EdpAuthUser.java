package com.el.edp.sec.domain;

import com.el.cfg.security.EdpAuthCredentials;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

/**
 * 身份确认: 企业号(ENT_CODE) + 员工号(EMP_CODE)
 * 运营侧: ENT = BP_OU(账户主体所属OU、所以不一定是根OU)
 * 供应商: ENT = BP_SUPP
 *
 * @author neo.pan
 * @since 17/8/9
 */
@Data
@ToString(exclude = {"password", "salt"})
@EqualsAndHashCode(of = "id")
public class EdpAuthUser implements EdpUser {

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * ID
     */
    private Long id;

    /**
     * 登录密码(SHA256+盐)
     */
    @JsonIgnore
    private transient String password;
    /**
     * 密码的盐
     */
    @JsonIgnore
    private transient String salt;

    /**
     * @return 认证用凭证
     */
    public EdpAuthCredentials credentials() {
        val credentials = EdpAuthCredentials.of(password, salt);
        password = null;
        salt = null;
        return credentials;
    }

    /**
     * 权限层级
     */
    private EdpAuthLayer authLayer;
    /**
     * 权限领域
     */
    private EdpAuthField authField;

    /**
     * 企业类型
     */
    private EdpEntType entType;

    /**
     * 企业ID
     */
    private Long entId;
    /**
     * 企业号
     */
    private String entCode;
    /**
     * 企业地址号
     */
    private Long entAddr;

    /**
     * 员工ID
     */
    private Long empId;
    /**
     * 员工号(即所属企业内用户名)
     */
    private String empCode;
    /**
     * 员工地址号
     */
    private Long empAddr;

    /**
     * 用户名称(显示用)
     */
    private String name;

    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    public static EdpAuthUser of(
        long tenantId, EdpAuthUserEntity entity, EdpAuthField authField, EdpEntType entType) {
        EdpAuthUser user = new EdpAuthUser();
        user.tenantId = tenantId;
        user.id = entity.getId();
        user.password = entity.getPassword();
        user.salt = entity.getSalt();
        user.authLayer = EdpAuthLayer.valueOf(entity.getLayer());
        user.authField = authField;
        user.entType = entType;
        user.entId = entity.getEntId();
        user.entCode = entity.getEntCode();
        user.entAddr = entity.getEntAddr();
        user.empId = entity.getEmpId();
        user.empCode = entity.getEmpCode();
        user.empAddr = entity.getEmpAddr();
        user.name = entity.getName();
        user.phone = entity.getPhone();
        user.email = entity.getEmail();
        return user;
    }

}
