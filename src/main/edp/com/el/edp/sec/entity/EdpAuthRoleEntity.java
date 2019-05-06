package com.el.edp.sec.entity;

import com.el.cfg.jdbc.EdpEntity;
import com.el.core.udc.UdcName;
import com.el.edi.EdpPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author neo.pan
 * @since 17/8/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EdpAuthRoleEntity extends EdpEntity implements EdpPayload {

    /**
     * 用户层级
     */
    private String layer;

    @UdcName(udcName = "EDP.AUTH_LAYER", codePropName = "layer")
    private String layerName;

    /**
     * 用户领域
     */
    @NotNull
    private String field;

    @UdcName(udcName = "EDP.AUTH_FIELD", codePropName = "field")
    private String fieldName;

    /**
     * 角色名称
     */
    @NotNull
    @Size(min = 1, max = 40)
    private String name;

    /**
     * 角色权限
     */
    @NotNull
    private List<String> perms;

    /**
     * 是否禁用
     */
    private boolean deleteFlag;
}
