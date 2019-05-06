package com.el.edp.bpm.entity;

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
public class EdpBpmRoleEntity extends EdpEntity implements EdpPayload {

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
     * 任务编号
     */
    @NotNull
    private List<Long> tasks;

    /**
     * 预警类型
     */
    private String reminderType;

    /**
     * 预警编号
     */
    private Long reminderId;

    /**
     * 是否禁用
     */
    private boolean deleteFlag;
}
