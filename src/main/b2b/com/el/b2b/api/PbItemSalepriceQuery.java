package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品价格表Query
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PbItemSalepriceQuery extends PagingQuery {
    /**
     * 公司编码
     */
    private String ouCode;
    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 品项名称
     */
    private String itemCode;

    /**
     * 品项名称
     */
    private String itemName;

    /**
     * 品项名称
     */
    private String spec;
    /**
     * 生效时间
     */
    @TimeUtil.DATE
    private LocalDate validFromDate;
    /**
     * 失效时间
     */
    @TimeUtil.DATE
    private LocalDate validToDate;

    /**
     * 厂家物料编码 - PB_ITEM.SPE8CJWLBM
     */
    private String spe8cjwlbm;

    /**
     * 生产厂家 - PB_ITEM.SPAN8DSC
     */
    private String span8dsc;

    /**
     * 生产厂家编码 - PB_ITEM.SPAN8DSC
     */
    private String span8;

    /**
     * 单位 - PB_ITEM.uom
     */
    private String uom;
//    /**
//     * 价格
//     */
//    private BigDecimal price;
//
//    /**
//     * 客户ID
//     */
//    private Long custId;

}
