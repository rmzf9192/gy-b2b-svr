package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PbItemUomQuery extends PagingQuery {
    private String ouCode;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 管理计量单位编码
     */
    private String umCode;

    /**
     * 管理计量单位
     */
    private String umName;

    /**
     * 主计量单位编码
     */
    private String rumCode;
    /**
     * 主计量单位
     */
    private String rumName;

}
