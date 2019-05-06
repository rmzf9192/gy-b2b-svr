package com.el.b2b.domain;

import com.el.mbg.domain.TPbItemSaleprice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description: 商品价格表domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PbItemSaleprice extends TPbItemSaleprice {
    /**
     * 品项编号 - PB_ITEM.ITEM_CODE
     */
    private String itemCode;
    /**
     * 品项名称 - PB_ITEM.ITEM_NAME
     */
    private String itemName;
    /**
     * 规格 - PB_ITEM.SPEC
     */
    private String spec;
    /**
     * 主计量单位 - 对应的udc名称
     */
    private String uom;
    /**
     * 主计量单位 - PB_ITEM.UOM
     */
    private String uomCode;
    /**
     * 等级 - PB_ITEM.GRADE
     */
    private String grade;
    /**
     * 公司
     */
    private String ouName;
    /**
     * 外部编码
     */
    private String outerCode;

    /**
     * 厂家物料编码 - PB_ITEM.SPE8CJWLBM
     */
    private String spe8cjwlbm;

    /**
     * 生产厂家 - PB_ITEM.SPAN8DSC
     */
    private String span8dsc;

    /**
     * 生产厂家编码 - PB_ITEM.SPAN8
     */
    private String span8;

    private List<UomTrans> uomList;
    private BigDecimal conv;

    private BigDecimal basePrice;

}
