package com.el.edp.udc.api;

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
public class EdpUdcMgmtQuery extends PagingQuery {

    /**
     * 模糊匹配：领域代码、UDC编号、UDC名称
     */
    private String udcLike;
}
