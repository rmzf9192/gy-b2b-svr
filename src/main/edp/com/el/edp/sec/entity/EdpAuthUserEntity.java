package com.el.edp.sec.entity;

import com.el.cfg.jdbc.EdpEntity;
import com.el.core.udc.UdcName;
import com.el.edi.EdpPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author neo.pan
 * @since 17/8/9
 */
@Data
@ToString(exclude = {"password", "salt"})
@EqualsAndHashCode(callSuper = true)
public class EdpAuthUserEntity extends EdpEntity implements EdpPayload {

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
     * 用户层级
     */
    private String layer;

    @UdcName(udcName = "EDP.AUTH_LAYER", codePropName = "layer")
    private String layerName;

    /**
     * 用户领域
     */
    private String field;

    @UdcName(udcName = "EDP.AUTH_FIELD", codePropName = "field")
    private String fieldName;

    /**
     * 企业类型
     */
    private String entType;

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
    @Size(min = 1, max = 40)
    private String name;

    /**
     * 手机
     */
//    @Size(min = 1, max = 20)
    private String phone;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 授权角色集合
     */
    private List<Long> authRoles;

    /**
     * 授权角色集合
     */
    private String authRolesName;

    /**
     * 流程角色集合
     */
    private List<Long> bpmRoles;

    /**
     * 是否禁用
     */
    private boolean deleteFlag;

    /**
     * 登录名
     */
    private String loginName;
}
