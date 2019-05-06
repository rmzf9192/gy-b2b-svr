package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by jerry.feng
 * on 2018/5/10.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OuQuery extends PagingQuery {
    /**
     * 公司code
     */
    private String ouCode;
    /**
     * 公司name
     */
    private String ouName;
}
