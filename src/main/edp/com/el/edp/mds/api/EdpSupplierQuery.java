package com.el.edp.mds.api;

import com.el.core.domain.PagingQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Getter
@Setter
public class EdpSupplierQuery extends PagingQuery {
    /**
     * 组件搜索关键字
     */
    private String key;
}
