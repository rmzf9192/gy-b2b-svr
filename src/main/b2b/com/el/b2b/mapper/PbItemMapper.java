package com.el.b2b.mapper;

import com.el.b2b.api.PbItemQuery;
import com.el.b2b.domain.PbItem;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbItemMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description: 商品主文件Mapper
 */
public interface PbItemMapper extends TPbItemMapper {

    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.TENANT_ID  tenantId");
            SELECT(" T.OU_ID  ouId");
            SELECT(" T.OU_CODE  ouCode");
            SELECT(" T.ORG_ID  orgId");
            SELECT(" T.ITEM_CODE  itemCode");
            SELECT(" T.ITEM_NAME  itemName");
            SELECT(" T.ITEM_ABBR  itemAbbr");
            SELECT(" T.ITEM_TITLE  itemTitle");
            SELECT(" T.ITEM_DESC  itemDesc");
            SELECT(" T.SUB_TITLE  subTitle");
            SELECT(" T.MEM_CODE  memCode");
            SELECT(" T.STORE_TYPE  storeType");
            SELECT(" T.PINYIN  pinyin");
            SELECT(" T.PINYIN_SH  pinyinSh");
            SELECT(" T.PROMOTION_INFO  promotionInfo");
            SELECT(" T.KEYWORDS  keywords");
            SELECT(" T.MARKS  marks");
            SELECT(" T.ITEM_STATUS  itemStatus");
            SELECT(" T.ITEM_STATUS2  itemStatus2");
            SELECT(" T.LISTING_STATUS  listingStatus");
            SELECT(" T.C1_CODE  c1Code");
            SELECT(" T.C2_CODE  c2Code");
            SELECT(" T.C3_CODE  c3Code");
            SELECT(" T.UOM2  uom2");
            SELECT(" T.UOM3  uom3");
            SELECT(" T.UOM4  uom4");
            SELECT(" T.UOM5  uom5");
            SELECT(" T.UOM6  uom6");
            SELECT(" T.UOM7  uom7");
            SELECT(" T.UOM8  uom8");
            SELECT(" T.UOM9  uom9");
            SELECT(" T.UOM10  uom10");
            SELECT(" T.SHIP_UOM  shipUom");
            SELECT(" T.GROSS_WEIGHT  grossWeight");
            SELECT(" T.NET_WEIGHT  netWeight");
            SELECT(" T.WEIGHT_UOM  weightUom");
            SELECT(" T.VOLUME  volume");
            SELECT(" T.MOQ  moq");
            SELECT(" T.SAFE_QTY  safeQty");
            SELECT(" T.MAX_QTY  maxQty");
            SELECT(" T.SALE_PRICE  salePrice");
            SELECT(" T.PRICE_GROUP  priceGroup");
            SELECT(" T.PRICE_PERIOD  pricePeriod");
            SELECT(" T.BRAND  brand");
            SELECT(" T.MATERIAL  material");

            SELECT(" T.STANDARD  standard");
            SELECT(" T.SURFACE  surface");

            SELECT(" T.SPEC  spec");

            SELECT(" T.P2  p2");
            SELECT(" T.P3  p3");
            SELECT(" T.P4  p4");
            SELECT(" T.P5  p5");
            SELECT(" T.P6  p6");
            SELECT(" T.P7  p7");
            SELECT(" T.P8  p8");
            SELECT(" T.REMARK  remark");
            SELECT(" T.OUTER_CODE  outerCode");
            SELECT(" T.CREATE_USER_ID  createUserId");
            SELECT(" T.CREATE_TIME  createTime");
            SELECT(" T.MODIFY_USER_ID  modifyUserId");
            SELECT(" T.MODIFY_TIME  modifyTime");
            SELECT(" T.AUDIT_DATA_VERSION  auditDataVersion");
            SELECT(" T.DELETE_FLAG  deleteFlag");
            SELECT(" T.ITEM_STAKE_TYPE  itemStakeType");
            SELECT(" T.ITEM_PROP  itemProp");
            SELECT(" T.REP_ITEM_ID  repItemId");
            SELECT(" T.OTHER_PROP1  otherProp1");
            SELECT(" T.OTHER_PROP2  otherProp2");
            SELECT(" T.OTHER_PROP3  otherProp3");
            SELECT(" T.OTHER_PROP4  otherProp4");
            SELECT(" T.OTHER_PROP5  otherProp5");
            SELECT(" T.OTHER_PROP6  otherProp6");
            SELECT(" T.OTHER_PROP7  otherProp7");
            SELECT(" T.OTHER_PROP8  otherProp8");
            SELECT(" T.OTHER_PROP9  otherProp9");
            SELECT(" T.OTHER_PROP10  otherProp10");
            SELECT(" T.OTHER_PROP11  otherProp11");
            SELECT(" T.OTHER_PROP12  otherProp12");
            SELECT(" T.OTHER_PROP13  otherProp13");
            SELECT(" T.OTHER_PROP14  otherProp14");
            SELECT(" T.OTHER_PROP15  otherProp15");
            SELECT(" T.OTHER_PROP16  otherProp16");
            SELECT(" T.OTHER_PROP17  otherProp17");
            SELECT(" T.OTHER_PROP18  otherProp18");

            SELECT(" T.GRADE  grade");
            SELECT(" T.P1  p1");
            SELECT(" O.OU_NAME  ouName");
            SELECT(" U.VAL_DESC  uom");
            SELECT(" U1.VAL_DESC  spe8spxzDsc");
            SELECT(" U2.VAL_DESC  gradeName");
            SELECT(" U3.VAL_DESC  p1Name");
            SELECT(" U4.VAL_DESC  origin");
            SELECT(" T.SPE8SPXZ  spe8spxz");
            SELECT(" T.SPE8CJWLBM  spe8cjwlbm");
            SELECT(" T.SPAN8DSC  span8dsc");
            SELECT(" T.SPAN8  span8");
        }

        private void FROM_SQL() {
            FROM("PB_ITEM T");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = T.UOM");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'E8' AND trim(UDC_CODE) = '20') U1 ON U1.VAL_CODE = T.SPE8SPXZ");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'E8' AND trim(UDC_CODE) = '18') U2 ON U2.VAL_CODE = T.GRADE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'E8' AND trim(UDC_CODE) = '16') U3 ON U3.VAL_CODE = T.P1");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'CN') U4 ON U4.VAL_CODE = T.ORIGIN");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(PbItemQuery query) {
            //过滤产品名称
            if (!StringUtils.isEmpty(query.getItemName())) {
                WHERE(" T.ITEM_NAME LIKE " + SqlUtil.toSqlLikeString(query.getItemName()));
            }
            //过滤规格
            if (!StringUtils.isEmpty(query.getSpec())) {
                WHERE(" T.SPEC LIKE " + SqlUtil.toSqlLikeString(query.getSpec()));
            }
            //过滤外部编码
            if (!StringUtils.isEmpty(query.getOuterCode())) {
                WHERE(" T.OUTER_CODE = #{outerCode}");
            }
            //过滤产品编码
            if (!StringUtils.isEmpty(query.getItemCode())) {
                WHERE(" T.ITEM_CODE like " + SqlUtil.toSqlLikeString(query.getItemCode()));
            }
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE = #{ouCode}");
            }
            //过滤厂家物料编码
            if (!StringUtils.isEmpty(query.getSpe8cjwlbm())) {
                WHERE(" T.SPE8CJWLBM like " + SqlUtil.toSqlLikeString(query.getSpe8cjwlbm()));
            }
            //过滤生产厂家编码
            if (!StringUtils.isEmpty(query.getSpan8dsc())) {
                WHERE(" T.SPAN8DSC like " + SqlUtil.toSqlLikeString(query.getSpan8dsc()));
            }
            //过滤生产厂家
            if (!StringUtils.isEmpty(query.getSpan8())) {
                WHERE(" T.SPAN8 like " + SqlUtil.toSqlLikeString(query.getSpan8()));
            }
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(PbItemQuery query) {
            SELECT("COUNT(1) n ");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(PbItemQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY(" T.ID DESC ");
            return SqlUtil.paging(toString(), query);
        }


        private static final String FIND_BY_ID = "findById";

        public String findById() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ID = #{id}");
            return toString();
        }

        private static final String FIND_BY_OUTER_CODE = "findByOuterCode";

        public String findByOuterCode() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.OUTER_CODE = #{outerCode}");
            return toString();
        }

        private static final String FIND_BY_ITEM_CODE_AND_OU_CODE = "findByItemCodeAndOuCode";

        public String findByItemCodeAndOuCode() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.OU_CODE = #{ouCode}");
            WHERE(" T.ITEM_CODE = #{itemCode}");
            return toString();
        }
    }

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(PbItemQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<PbItem> findPage(PbItemQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    PbItem findById(@Param("id") long id);

    /**
     * 按商品编号（OUTER_CODE）查询
     *
     * @param outerCode
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_OUTER_CODE)
    PbItem findByOuterCode(@Param("outerCode") String outerCode);

    /**
     * 按商品编号（itemCode）查询
     *
     * @param itemCode
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ITEM_CODE_AND_OU_CODE)
    PbItem findByItemCodeAndOuCode(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode);


}
