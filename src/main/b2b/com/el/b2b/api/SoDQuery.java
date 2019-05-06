package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:订单明细Query
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoDQuery extends PagingQuery {

    /**
     * 订单ID
     */
    private Long id;
    /**
     * 订单表头ID
     */
    private Long soId;

}
