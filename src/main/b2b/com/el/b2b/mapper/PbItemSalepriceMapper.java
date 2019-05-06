package com.el.b2b.mapper;

import com.el.b2b.api.PbItemSalepriceQuery;
import com.el.b2b.domain.PbItemSaleprice;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbItemSalepriceMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description: 商品价格表Mapper
 */
public interface PbItemSalepriceMapper extends TPbItemSalepriceMapper {

    class SqlBuilder extends Sql {

        private void SELECT_ALL() {
            SELECT(" T.ID id");
            SELECT(" T.TENANT_ID tenantId");
            SELECT(" T.OU_ID ouId");
            SELECT(" T.ORG_ID orgId");
            SELECT(" T.PRICE_TYPE priceType");
            SELECT(" T.PRICE_TYPE2 priceType2");
            SELECT(" T.PRICE_TYPE3 priceType3");
            SELECT(" T.ITEM_ID itemId");
            SELECT(" T.ITEM_C1 itemC1");
            SELECT(" T.ITEM_C2 itemC2");
            SELECT(" T.ITEM_C3 itemC3");
            SELECT(" T.CUST_GROUP custGroup");
            SELECT(" T.CUST_ID custId");
            SELECT(" T.SALE_REGION saleRegion");
            SELECT(" T.PRICE_GROUP priceGroup");
            SELECT(" T.SHIPTO_ADDR_NO shiptoAddrNo");
            SELECT(" T.PRICE price");
            SELECT(" T.PRICE_UOM priceUom");
            SELECT(" T.CURR_CODE currCode");
            SELECT(" T.VALID_FROM validFrom");
            SELECT(" T.VALID_TO validTo");
            SELECT(" T.FROM_QTY fromQty");
            SELECT(" T.TO_QTY toQty");
            SELECT(" T.TOLERANCE tolerance");
            SELECT(" T.REMARK remark");
            SELECT(" T.CREATE_USER_ID createUserId");
            SELECT(" T.CREATE_TIME createTime");
            SELECT(" T.MODIFY_USER_ID modifyUserId");
            SELECT(" T.MODIFY_TIME modifyTime");
            SELECT(" T.AUDIT_DATA_VERSION auditDataVersion");
            SELECT(" T.DELETE_FLAG deleteFlag");
            SELECT(" T.PRICE_STATUS  priceStatus");

            SELECT(" PI.ITEM_CODE  itemCode");
            SELECT(" PI.ITEM_NAME  itemName");
            SELECT(" PI.SPEC  spec");
            SELECT(" PI.GRADE  grade");
            SELECT(" U.VAL_DESC  uom");

        }

        private void FROM_SQL() {
            FROM("PB_ITEM_SALEPRICE T");
            JOIN("PB_ITEM PI ON PI.ITEM_CODE = T.ITEM_CODE AND PI.OU_CODE = T.OU_ID");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = PI.UOM");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (PI.DELETE_FLAG <> 1 OR PI.DELETE_FLAG IS NULL) ");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(PbItemSalepriceQuery query) {
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" PI.OU_CODE = #{ouCode}");
            }
            //过滤客户
            if (!StringUtils.isEmpty(query.getCustCode())) {
                WHERE(" T.CUST_CODE = #{custCode}");
            }
            //过滤名称
            if (!StringUtils.isEmpty(query.getItemName())) {
                WHERE("PI.ITEM_NAME LIKE " + SqlUtil.toSqlLikeString(query.getItemName()));
            }
            //过滤生效失效日期
            if (!StringUtils.isEmpty(query.getValidFromDate())) {
                WHERE("TO_DATE(TO_CHAR(T.VALID_FROM,'YYYY-MM-DD'),'YYYY-MM-DD') >= #{validFromDate}");
            }
            if (!StringUtils.isEmpty(query.getValidToDate())) {
                WHERE("TO_DATE(TO_CHAR(T.VALID_TO,'YYYY-MM-DD'),'YYYY-MM-DD') <= #{validToDate}");
            }

        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(PbItemSalepriceQuery query) {
            SELECT("COUNT(1) n");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(PbItemSalepriceQuery query) {
            SELECT(" T.ID id");
            SELECT(" T.ITEM_ID  itemId");//商品id
            SELECT(" PI.ITEM_CODE  itemCode");//商品编码
            SELECT(" PI.ITEM_NAME  itemName");//商品名称
            SELECT(" T.PRICE price");//税后单价
            SELECT(" PI.SPEC  spec");
            SELECT(" O.OU_NAME  ouName");//公司
            SELECT(" T.CUST_ID  custId");//客户编码
            SELECT(" T.CURR_CODE  currCode"); //货币码
            SELECT(" T.PRICE_STATUS  priceStatus"); // 批准状态
            SELECT(" T.VALID_FROM validFrom");
            SELECT(" T.VALID_TO validTo");
            SELECT(" U.VAL_DESC  uom");
            SELECT(" PI.GRADE  grade");
            SELECT(" PI.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" PI.SPAN8DSC  span8dsc");//生产厂家
            SELECT(" PI.SPAN8  span8");//生产厂家编码

            FROM("PB_ITEM_SALEPRICE T");
            JOIN("PB_ITEM PI ON PI.ITEM_CODE = T.ITEM_CODE AND PI.OU_CODE = T.OU_ID");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_ID");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = PI.UOM");

            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (PI.DELETE_FLAG <> 1 OR PI.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
            FILTER_BY(query);
            ORDER_BY("T.ID DESC");
            return SqlUtil.paging(toString(), query);
        }

        private static final String FIND_BY_ID = "findById";

        public String findById() {
//            SELECT_ALL();
            SELECT(" T.ID id");
            SELECT(" PI.ITEM_CODE  itemCode");//商品编码
            SELECT(" PI.ITEM_NAME  itemName");//商品名称
            SELECT(" T.PRICE price");//税后单价
            SELECT(" PI.SPEC  spec");
            SELECT(" O.OU_NAME  ouName");//公司
            SELECT(" T.CUST_ID  custId");//客户编码
            SELECT(" T.CURR_CODE  currCode"); //货币码
            SELECT(" T.PRICE_STATUS  priceStatus"); // 批准状态
            SELECT(" T.VALID_FROM validFrom");
            SELECT(" T.VALID_TO validTo");

            SELECT(" U.VAL_DESC  uom");
            SELECT(" PI.GRADE  grade");
            SELECT(" PI.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" PI.SPAN8DSC  span8dsc");//生产厂家
            SELECT(" PI.SPAN8  span8");//生产厂家编码

            FROM("PB_ITEM_SALEPRICE T");
            JOIN("PB_ITEM PI ON PI.ITEM_CODE = T.ITEM_CODE AND PI.OU_CODE = T.OU_ID");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_ID");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = PI.UOM");

            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (PI.DELETE_FLAG <> 1 OR PI.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
            WHERE("T.ID =#{id}");
            return toString();
        }


        private void FROM_FOR_CUST_SQL(PbItemSalepriceQuery query) {
            FROM("(\n" +
                "  SELECT A.ID, A.ITEM_CODE, A.CURR_CODE, A.PRICE FROM(\n" +
                "    SELECT DISTINCT\n" +
                "      CASE WHEN P1.PRICE IS NOT NULL THEN P1.ID ELSE P2.ID END ID, " +
                "      CASE WHEN P1.PRICE IS NOT NULL \n" +
                "        THEN P1.PRICE ELSE P2.PRICE END PRICE,\n" +
                "      CASE WHEN P1.PRICE IS NOT NULL \n" +
                "        THEN P1.CURR_CODE ELSE P2.CURR_CODE END CURR_CODE,\n" +
                "       T.ITEM_CODE \n" +
                "    FROM (SELECT * FROM PB_ITEM WHERE OU_CODE=#{ouCode} \n" +
                "      AND ITEM_CODE IN (\n" +
                "        SELECT ITEM_CODE FROM PB_ITEM_SALEPRICE WHERE OU_ID=#{ouCode} " +
                "        AND CUST_CODE IN(#{custCode},'0')\n" +
                "      ) \n" +
                "     ) T \n" +
                "    LEFT JOIN (SELECT * FROM PB_ITEM_SALEPRICE A WHERE A.CUST_CODE =#{custCode}\n" +
                "      AND OU_ID=#{ouCode} " +
                "      AND ( A.VALID_FROM IS NULL OR A.VALID_FROM <= NOW( ) ) \n" +
                "      AND ( A.VALID_FROM IS NULL OR A.VALID_TO >= NOW( ) ) \n" +
                "      AND A.TENANT_ID = 1 \n" +
                "    ) P1 ON (\n" +
                "      P1.ITEM_CODE=T.ITEM_CODE" +
                "    )\n" +
                "    LEFT JOIN (SELECT * FROM PB_ITEM_SALEPRICE A WHERE A.CUST_CODE ='0'\n" +
                "         AND OU_ID=#{ouCode} \n" +
                "         AND ( A.VALID_FROM IS NULL OR A.VALID_FROM <= NOW( ) )\n" +
                "         AND ( A.VALID_FROM IS NULL OR A.VALID_TO >= NOW( ) )\n" +
                "         AND A.TENANT_ID = 1 \n" +
                "    ) P2 ON (\n" +
                "      P2.ITEM_CODE=T.ITEM_CODE" +
                "    )\n" +
                "    WHERE T.OU_CODE=#{ouCode}\n" +
                "  ) A\n" +
                "  WHERE A.PRICE>0\n" +
                ") T ");
            JOIN("PB_ITEM PI ON (PI.ITEM_CODE=T.ITEM_CODE AND PI.OU_CODE=#{ouCode})");
            JOIN("PB_OU O ON O.OU_CODE = PI.OU_ID");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = PI.UOM");

        }

        private void FILTER_FOR_CUST_BY(PbItemSalepriceQuery query) {
            //过滤名称
            if (!StringUtils.isEmpty(query.getItemCode())) {
                WHERE("(PI.ITEM_CODE LIKE " + SqlUtil.toSqlLikeString(query.getItemCode()) + " )");
            }
            //过滤名称
            if (!StringUtils.isEmpty(query.getItemName())) {
                WHERE("(PI.ITEM_NAME LIKE " + SqlUtil.toSqlLikeString(query.getItemName()) + " )");
            }
            //过滤规格
            if (!StringUtils.isEmpty(query.getSpec())) {
                WHERE("(PI.SPEC LIKE " + SqlUtil.toSqlLikeString(query.getSpec()) + " )");
            }
            //过滤单位
            if (!StringUtils.isEmpty(query.getUom())) {
                WHERE("(PI.UOM  = '" + query.getUom() + "' )");
            }
            //过滤厂家物料编码
            if (!StringUtils.isEmpty(query.getSpe8cjwlbm())) {
                WHERE(" PI.SPE8CJWLBM like " + SqlUtil.toSqlLikeString(query.getSpe8cjwlbm()));
            }
            //过滤生产厂家
            if (!StringUtils.isEmpty(query.getSpan8dsc())) {
                WHERE(" PI.SPAN8DSC like " + SqlUtil.toSqlLikeString(query.getSpan8dsc()));
            }
            //过滤生产厂家编码
            if (!StringUtils.isEmpty(query.getSpan8())) {
                WHERE(" PI.SPAN8 like " + SqlUtil.toSqlLikeString(query.getSpan8()));
            }
        }

        public static final String FIND_COUNT_FOR_CUST_SQL = "findCountForCustSql";

        public String findCountForCustSql(PbItemSalepriceQuery query) {
            SELECT("COUNT(1) n");
            FROM_FOR_CUST_SQL(query);
            FILTER_FOR_CUST_BY(query);
            return toString();
        }

        public static final String FIND_PAGE_FOR_CUST_SQL = "findPageForCustSql";

        public String findPageForCustSql(PbItemSalepriceQuery query) {
            SELECT(" T.ID id");//商品id
            SELECT(" PI.ITEM_CODE  itemCode");//商品编码
            SELECT(" PI.ITEM_NAME  itemName");//商品名称
            SELECT(" PI.SPEC  spec");
            SELECT(" PI.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" PI.SPAN8DSC  span8dsc");//生产厂家
            SELECT(" PI.SPAN8  span8");//生产厂家编码

            SELECT(" T.PRICE price");//税后单价
            SELECT(" T.PRICE basePrice");//税后单价
            SELECT("1 conv");

            SELECT(" T.CURR_CODE  currCode"); //货币码

            SELECT(" O.OU_NAME  ouName");//公司

            SELECT(" PI.UOM  uomCode");//计量单位
            SELECT(" U.VAL_DESC  uom");
            SELECT(" PI.GRADE  grade");

            SELECT(" PI.OUTER_CODE outerCode");


            FROM_FOR_CUST_SQL(query);
            FILTER_FOR_CUST_BY(query);
            ORDER_BY(" PI.ITEM_CODE ASC ");
            return SqlUtil.paging(toString(), query);
        }


        private static final String GET_PRICE_BY_ITEMCODE_SQL = "getPriceByItemCodeSql";

        public String getPriceByItemCodeSql() {
            SELECT(" T.PRICE price");
            FROM(" PB_ITEM_SALEPRICE T ");
            WHERE("  T.ITEM_CODE = #{itemCode} AND T.OU_ID = #{ouCode} AND " +
                "  IF( (SELECT COUNT(A.ITEM_CODE) FROM PB_ITEM_SALEPRICE  A WHERE  A.ITEM_CODE = #{itemCode} AND A.OU_ID = #{ouCode} AND  A.CUST_CODE = #{custCode})>0, T.CUST_CODE =  #{custCode},T.CUST_CODE = '0')");

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
    int findCount(PbItemSalepriceQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<PbItemSaleprice> findPage(PbItemSalepriceQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    PbItemSaleprice findById(@Param("id") long id);

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_FOR_CUST_SQL)
    int findCountForCust(PbItemSalepriceQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_FOR_CUST_SQL)
    List<PbItemSaleprice> findPageForCust(PbItemSalepriceQuery query);

    /**
     * 获取商品价格信息
     *
     * @param itemCode
     * @param ouCode
     * @param custCode
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.GET_PRICE_BY_ITEMCODE_SQL)
    BigDecimal getPriceByItemCode(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode, @Param("custCode") String custCode);

}
