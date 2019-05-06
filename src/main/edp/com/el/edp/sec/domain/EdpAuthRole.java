package com.el.edp.sec.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

/**
 * @author neo.pan
 * @since 17/8/9
 */
@Data
@EqualsAndHashCode(of = "id")
public class EdpAuthRole {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户层级
     */
    private EdpAuthLayer layer;

    /**
     * 用户领域
     */
    private String field;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 角色名称
     */
    @Size(min = 1, max = 40)
    private String name;

    /**
     * 是否禁用
     */
    private boolean deleteFlag;
}
