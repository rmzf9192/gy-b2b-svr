package com.el.edp.sec.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author neo.pan
 * @since 17/8/17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpAuthRoleQuery extends PagingQuery {

    /**
     * 模糊匹配：角色编码、名称
     */
    private String roleLike;

}
