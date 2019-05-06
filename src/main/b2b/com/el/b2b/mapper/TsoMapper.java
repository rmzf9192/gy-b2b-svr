package com.el.b2b.mapper;

import com.el.b2b.api.TsoQuery;
import com.el.b2b.domain.TsoCodeName;
import com.el.b2b.domain.TsoDomain;
import com.el.b2b.sys.TableMeta;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TB2bTsoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
public interface TsoMapper extends TB2bTsoMapper {
    class SqlBuilder extends Sql {

        static final String GET_TSO_LIST = "getTsoList";

        public String getTsoList(TsoQuery query) {
            SELECT(" T.ID as id");
            SELECT(" T.DOC_NO AS code");
            SELECT(" T.DOC_NO AS name");

            FROM(TableMeta.B2B_TSO.getName() + " T");
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" T.OU_CODE = #{ouCode}");
            WHERE(" T.CUST_CODE = #{custCode}");
            ORDER_BY(" T.ID DESC ");
            return toString();
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(TsoQuery query) {
            SELECT("COUNT(1) n");
            FROM_SQL(query);
            FILTER_BY(query);
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(TsoQuery query) {
            return SqlUtil.paging(findListSql(query), query);
        }

        static final String REMOVE_TSOD_BY_ITEMCODE = "removeTsodByItemCode";

        public String removeTsodByItemCode(@Param("tsoId") Long tsoId, @Param("itemCode") String itemCode) {
            UPDATE(TableMeta.B2B_TSO_D.getName() + " T");
            SET("DELETE_FLAG = 1");
            WHERE("T.TSO_ID = #{tsoId}");
            WHERE("T.ITEM_CODE = #{itemCode}");
            return toString();
        }

        static final String FIND_BY_DOCNO = "findByDocno";

        public String findByDocno() {
            SELECT("T.ID AS id");
            FROM(TableMeta.B2B_TSO.getName() + " T");
            WHERE("T.DOC_NO = #{docNo}");
            WHERE("T.OU_CODE = #{ouCode}");
            WHERE("T.CUST_CODE = #{custCode}");
            return toString();
        }


        private void FILTER_BY(TsoQuery query) {
            if (StringUtils.notEmpty(query.getOuCode())) {
                WHERE("T.OU_CODE = #{ouCode}");
            }
            if (StringUtils.notEmpty(query.getCustCode())) {
                WHERE("T.CUST_CODE = #{custCode}");
            }
            if (StringUtils.notEmpty(query.getDocNo())) {
                WHERE("T.DOC_NO = #{docNo}");
            }
        }

        public String findListSql(TsoQuery query) {
            SELECT_ALL();
            FROM_SQL(query);
            FILTER_BY(query);
            ORDER_BY("T.ID");
            whereDeleteFlagSql();
            return toString();
        }

        private void SELECT_ALL() {
            SELECT("T.ID AS id");
            SELECT("T.TENANT_ID AS tenantId");
            SELECT("T.OU_ID AS ouId");
            SELECT("T.ORG_ID AS orgId");
            SELECT("T.DOC_NO AS docNo");
            SELECT("T.DOC_TYPE AS docType");
            SELECT("T.DOC_STATUS AS docStatus");
            SELECT("T.DOC_TIME AS docTime");
            SELECT("T.CUST_ID AS custId");
            SELECT("T.PAYMENT_TERM AS paymentTerm");
            SELECT("T.CURR_CODE AS currCode");
            SELECT("T.TAX_CODE AS taxCode");
            SELECT("T.DELIVER_WAY AS deliverWay");
            SELECT("T.DELIVER_CONTACT AS deliverContact");
            SELECT("T.DELIVER_ADDRESS_ID AS deliverAddressId");
            SELECT("T.REMARK AS remark");
            SELECT("T.CREATE_USER_ID AS createUserId");
            SELECT("T.CREATE_TIME AS createTime");
            SELECT("T.MODIFY_USER_ID AS modifyUserId");
            SELECT("T.MODIFY_TIME AS modifyTime");
            SELECT("T.AUDIT_DATA_VERSION AS auditDataVersion");
            SELECT("T.DELETE_FLAG AS deleteFlag");

            SELECT("T2.ID AS tsodId");
            SELECT("T2.TSO_ID AS tsoId");
            SELECT("T2.ITEM_ID AS itemId");
//            SELECT("T2.UOM AS uom");

            SELECT("T2.ITEM_CODE AS itemCode");
            SELECT("T5.ITEM_NAME AS itemName");
            SELECT("T5.SPEC AS spec");
            SELECT(" T5.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" T5.SPAN8DSC  span8dsc");//生产厂家
            SELECT(" T5.SPAN8  span8");//生产厂家编码

            SELECT(" T2.UOM  uom");
            SELECT(" U.VAL_CODE uomCode");
            SELECT("T6.PRICE AS price");

        }

        private void FROM_SQL(TsoQuery query) {
            FROM(TableMeta.B2B_TSO.getName() + " T");
            JOIN(TableMeta.B2B_TSO_D.getName() + " T2 ON T2.TSO_ID = T.ID");
            JOIN(TableMeta.PB_ITEM.getName() + " T5 ON T5.ITEM_CODE = T2.ITEM_CODE AND T5.OU_CODE = T.OU_CODE");
            JOIN(
                "(\n" +
                    "  SELECT A.ITEM_CODE, A.CURR_CODE, A.PRICE FROM(\n" +
                    "    SELECT DISTINCT\n" +
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
                    ")  T6 ON T6.ITEM_CODE = T2.ITEM_CODE ");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_DESC = T2.UOM");
        }

        private void whereDeleteFlagSql() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL)");
            WHERE(" (T2.DELETE_FLAG <> 1 OR T2.DELETE_FLAG IS NULL)");
            WHERE(" (T5.DELETE_FLAG <> 1 OR T5.DELETE_FLAG IS NULL)");
//            WHERE(" (T6.DELETE_FLAG <> 1 OR T6.DELETE_FLAG IS NULL)");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
        }

    }

    /**
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(TsoQuery query);

    /**
     * @param query 查询条件
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<TsoDomain> findPage(TsoQuery query);

    /**
     * 根据模板id，itemId删除指定的商品
     *
     * @param tsoId, itemId
     * @return 执行结果
     */
    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.REMOVE_TSOD_BY_ITEMCODE)
    int removeTsodByItemCode(@Param("tsoId") Long tsoId, @Param("itemCode") String itemCode);

    /**
     * 根据模板编码查询模板id
     *
     * @param docNo
     * @return 返回值
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_DOCNO)
    Long findByDocno(@Param("docNo") String docNo, @Param("ouCode") String ouCode, @Param("custCode") String custCode);

    /**
     * UDC 获取模版信息
     *
     * @param tsoQuery
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.GET_TSO_LIST)
    List<TsoCodeName> getTsoList(TsoQuery tsoQuery);
}
