package com.el.mbg.domain;

import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class TPbItem {
    /**
     *  主键 - PB_ITEM.ID
     */
    private Long id;

    /**
     *  租户ID - PB_ITEM.TENANT_ID
     */
    private Long tenantId;

    /**
     *  公司ID - PB_ITEM.OU_ID
     */
    private Long ouId;

    /**
     *  组织ID - PB_ITEM.ORG_ID
     */
    private Long orgId;

    /**
     *  品项编号 - PB_ITEM.ITEM_CODE
     */
    private String itemCode;

    /**
     *  品项名称 - PB_ITEM.ITEM_NAME
     */
    private String itemName;

    /**
     *  品项简称 - PB_ITEM.ITEM_ABBR
     */
    private String itemAbbr;

    /**
     *  品项标题 - PB_ITEM.ITEM_TITLE
     */
    private String itemTitle;

    /**
     *  品项描述 - PB_ITEM.ITEM_DESC
     */
    private String itemDesc;

    /**
     *  副标题 - PB_ITEM.SUB_TITLE
     */
    private String subTitle;

    /**
     *  助记码 - PB_ITEM.MEM_CODE
     */
    private String memCode;

    /**
     *  存储类型 - PB_ITEM.STORE_TYPE
     */
    private String storeType;

    /**
     *  拼音 - PB_ITEM.PINYIN
     */
    private String pinyin;

    /**
     *  拼音简称 - PB_ITEM.PINYIN_SH
     */
    private String pinyinSh;

    /**
     *  促销信息 - PB_ITEM.PROMOTION_INFO
     */
    private String promotionInfo;

    /**
     *  关键词 - PB_ITEM.KEYWORDS
     */
    private String keywords;

    /**
     *  标记 - PB_ITEM.MARKS
     */
    private String marks;

    /**
     *  品项状态 - PB_ITEM.ITEM_STATUS
     */
    private String itemStatus;

    /**
     *  品项状态2 - PB_ITEM.ITEM_STATUS2
     */
    private String itemStatus2;

    /**
     *  上架状态 - PB_ITEM.LISTING_STATUS
     */
    private String listingStatus;

    /**
     *  大类编号 - PB_ITEM.C1_CODE
     */
    private String c1Code;

    /**
     *  中类编号 - PB_ITEM.C2_CODE
     */
    private String c2Code;

    /**
     *  小类编号 - PB_ITEM.C3_CODE
     */
    private String c3Code;

    /**
     *  主计量单位 - PB_ITEM.UOM
     */
    private String uom;

    /**
     *  计量单位2 - PB_ITEM.UOM2
     */
    private String uom2;

    /**
     *  发运计量单位 - PB_ITEM.SHIP_UOM
     */
    private String shipUom;

    /**
     *  毛重 - PB_ITEM.GROSS_WEIGHT
     */
    private BigDecimal grossWeight;

    /**
     *  净重 - PB_ITEM.NET_WEIGHT
     */
    private BigDecimal netWeight;

    /**
     *  重量单位 - PB_ITEM.WEIGHT_UOM
     */
    private String weightUom;

    /**
     *  体积 - PB_ITEM.VOLUME
     */
    private BigDecimal volume;

    /**
     *  最小起订量 - PB_ITEM.MOQ
     */
    private BigDecimal moq;

    /**
     *  安全库存 - PB_ITEM.SAFE_QTY
     */
    private BigDecimal safeQty;

    /**
     *  最大库存量 - PB_ITEM.MAX_QTY
     */
    private BigDecimal maxQty;

    /**
     *  默认售价 - PB_ITEM.SALE_PRICE
     */
    private BigDecimal salePrice;

    /**
     *  价格组 - PB_ITEM.PRICE_GROUP
     */
    private String priceGroup;

    /**
     *  调价周期 - PB_ITEM.PRICE_PERIOD
     */
    private String pricePeriod;

    /**
     *  品牌 - PB_ITEM.BRAND
     */
    private String brand;

    /**
     *  材质 - PB_ITEM.MATERIAL
     */
    private String material;

    /**
     *  等级 - PB_ITEM.GRADE
     */
    private String grade;

    /**
     *  标准 - PB_ITEM.STANDARD
     */
    private String standard;

    /**
     *  表面处理 - PB_ITEM.SURFACE
     */
    private String surface;

    /**
     *  产地 - PB_ITEM.ORIGIN
     */
    private String origin;

    /**
     *  规格 - PB_ITEM.SPEC
     */
    private String spec;

    /**
     *  属性1 - PB_ITEM.P1
     */
    private String p1;

    /**
     *  属性2 - PB_ITEM.P2
     */
    private String p2;

    /**
     *  属性3 - PB_ITEM.P3
     */
    private String p3;

    /**
     *  属性4 - PB_ITEM.P4
     */
    private String p4;

    /**
     *  属性5 - PB_ITEM.P5
     */
    private String p5;

    /**
     *  属性6 - PB_ITEM.P6
     */
    private String p6;

    /**
     *  属性7 - PB_ITEM.P7
     */
    private String p7;

    /**
     *  属性8 - PB_ITEM.P8
     */
    private String p8;

    /**
     *  备注 - PB_ITEM.REMARK
     */
    private String remark;

    /**
     *  外部编码 - PB_ITEM.OUTER_CODE
     */
    private String outerCode;

    /**
     *  创建人ID - PB_ITEM.CREATE_USER_ID
     */
    private Long createUserId;

    /**
     *  创建时间 - PB_ITEM.CREATE_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime createTime;

    /**
     *  最后编辑人ID - PB_ITEM.MODIFY_USER_ID
     */
    private Long modifyUserId;

    /**
     *  最后编辑时间 - PB_ITEM.MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     *  数据版本号 - PB_ITEM.AUDIT_DATA_VERSION
     */
    private Long auditDataVersion;

    /**
     *  是否删除 - PB_ITEM.DELETE_FLAG
     */
    private Short deleteFlag;

    /**
     *  计量单位3 - PB_ITEM.UOM3
     */
    private String uom3;

    /**
     *  计量单位4 - PB_ITEM.UOM4
     */
    private String uom4;

    /**
     *  计量单位5 - PB_ITEM.UOM5
     */
    private String uom5;

    /**
     *  计量单位6 - PB_ITEM.UOM6
     */
    private String uom6;

    /**
     *  计量单位7 - PB_ITEM.UOM7
     */
    private String uom7;

    /**
     *  计量单位8 - PB_ITEM.UOM8
     */
    private String uom8;

    /**
     *  计量单位9 - PB_ITEM.UOM9
     */
    private String uom9;

    /**
     *  计量单位10 - PB_ITEM.UOM10
     */
    private String uom10;

    /**
     *  接口状态 - PB_ITEM.INTF_STATUS
     */
    private String intfStatus;

    /**
     *  接口处理时间 - PB_ITEM.INTF_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime intfTime;

    /**
     *  物料属性 - PB_ITEM.ITEM_PROP
     */
    private String itemProp;

    /**
     *  替代原料ID - PB_ITEM.REP_ITEM_ID
     */
    private Long repItemId;

    /**
     *  预留字段1 - PB_ITEM.OTHER_PROP1
     */
    private String otherProp1;

    /**
     *  预留字段2 - PB_ITEM.OTHER_PROP2
     */
    private String otherProp2;

    /**
     *  预留字段3 - PB_ITEM.OTHER_PROP3
     */
    private String otherProp3;

    /**
     *  预留字段4 - PB_ITEM.OTHER_PROP4
     */
    private String otherProp4;

    /**
     *  预留字段5 - PB_ITEM.OTHER_PROP5
     */
    private String otherProp5;

    /**
     *  预留字段6 - PB_ITEM.OTHER_PROP6
     */
    private String otherProp6;

    /**
     *  预留字段7 - PB_ITEM.OTHER_PROP7
     */
    private String otherProp7;

    /**
     *  预留字段8 - PB_ITEM.OTHER_PROP8
     */
    private String otherProp8;

    /**
     *  预留字段9 - PB_ITEM.OTHER_PROP9
     */
    private String otherProp9;

    /**
     *  预留字段10 - PB_ITEM.OTHER_PROP10
     */
    private String otherProp10;

    /**
     *  预留字段11 - PB_ITEM.OTHER_PROP11
     */
    private String otherProp11;

    /**
     *  预留字段12 - PB_ITEM.OTHER_PROP12
     */
    private String otherProp12;

    /**
     *  预留字段13 - PB_ITEM.OTHER_PROP13
     */
    private String otherProp13;

    /**
     *  预留字段14 - PB_ITEM.OTHER_PROP14
     */
    private String otherProp14;

    /**
     *  预留字段15 - PB_ITEM.OTHER_PROP15
     */
    private String otherProp15;

    /**
     *  预留字段16 - PB_ITEM.OTHER_PROP16
     */
    private String otherProp16;

    /**
     *  预留字段17 - PB_ITEM.OTHER_PROP17
     */
    private String otherProp17;

    /**
     *  预留字段18 - PB_ITEM.OTHER_PROP18
     */
    private String otherProp18;

    /**
     *  盘点类型 - PB_ITEM.ITEM_STAKE_TYPE
     */
    private String itemStakeType;

    /**
     *  公司编码 - PB_ITEM.OU_CODE
     */
    private String ouCode;

    /**
     * 商品性质 - PB_ITEM.SPE8SPXZ
     */
    private String spe8spxz;

    /**
     * 商品性质 - PB_ITEM.SPE8SPXZ
     */
    private String spe8spxzDsc;

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
}
