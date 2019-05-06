package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TsoQuery extends PagingQuery {
    /**
     * 模板代码
     */
    private String docNo;
    /**
     * tsoId 模板ID
     */
    private String tsoId;
    /**
     * 公司CODE
     */
    private String ouCode;
    /**
     * 客户CODE
     */
    private String custCode;
}
