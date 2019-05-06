package com.el.b2b.mapper;

import com.el.b2b.api.PbItemUomQuery;
import com.el.b2b.domain.PbItemUom;
import com.el.b2b.domain.Uom;
import com.el.b2b.domain.UomTrans;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UomMapper {


    class SqlBuilder extends Sql {

        private static final String QUERY_BY_ITEM_CODE = "queryByItemCode";
        public String queryByItemCode(@Param("itemCode") String itemCode, @Param("mainUom") String mainUom) {
            SELECT("v2.UMMCU MCU");
            SELECT("item.OUTER_CODE ITM");
            SELECT("v2.UMUM UM");
            SELECT("v2.UMRUM RUM");
            SELECT("v2.UMCONV CONV");
            SELECT("v2.UMCNV1 CNV1");
            SELECT("U.VAL_DESC UMDESC");
            FROM("V41002 v2");
            INNER_JOIN("PB_ITEM item on v2.umitm=item.OUTER_CODE");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = v2.UMUM");
            WHERE(" item.ITEM_CODE = #{itemCode}");
            WHERE(" UMRUM = #{mainUom} ");
            WHERE("UMMCU = ' '");
            return toString() + " limit 1";

        }

        private static final String QUERY_MAIN_UOM = "queryMainUom";
        public String queryMainUom(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode) {
            SELECT("item.OUTER_CODE outerCode");
            SELECT("item.UOM uomCode");
            SELECT("U.VAL_DESC uomDesc");
            FROM("PB_ITEM item");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = item.UOM");
            WHERE(" item.ITEM_CODE = #{itemCode}");
            WHERE(" item.OU_CODE = #{ouCode}");
            return toString();

        }

        private void SELECT_ALL() {
            SELECT("item.ITEM_CODE itemCode");
            SELECT("item.ITEM_NAME itemName");
            SELECT("v2.UMUM umCode");
            SELECT("U1.VAL_DESC umName");
            SELECT("v2.UMCONV CONV");
            SELECT("v2.UMRUM rumCode");
            SELECT("U2.VAL_DESC rumName");
        }
        private void FROM_SQL() {
            FROM("V41002 v2");
            INNER_JOIN("PB_ITEM item on v2.umitm=item.OUTER_CODE");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE,VAL_DESC FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM' AND (DELETE_FLAG <> 1 OR DELETE_FLAG IS NULL)) U1 ON U1.VAL_CODE = v2.UMUM");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE,VAL_DESC FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM' AND (DELETE_FLAG <> 1 OR DELETE_FLAG IS NULL)) U2 ON U2.VAL_CODE = v2.UMRUM");
        }
        private void FILTER_BY(PbItemUomQuery query) {
            WHERE("item.ou_code = #{ouCode}");
            if (!StringUtils.isEmpty(query.getItemCode())) {
                WHERE(" item.ITEM_CODE LIKE " + SqlUtil.toSqlLikeString(query.getItemCode()));
            }
            if (!StringUtils.isEmpty(query.getItemName())) {
                WHERE(" item.ITEM_NAME LIKE " + SqlUtil.toSqlLikeString(query.getItemName()));
            }
            if (!StringUtils.isEmpty(query.getUmCode())) {
                WHERE(" v2.UMUM = #{umCode}");
            }
            if (!StringUtils.isEmpty(query.getUmName())) {
                WHERE(" U1.VAL_DESC = #{umName}");
            }
            if (!StringUtils.isEmpty(query.getRumCode())) {
                WHERE(" v2.UMRUM = #{rumCode}");
            }
            if (!StringUtils.isEmpty(query.getRumName())) {
                WHERE(" U2.VAL_DESC = #{rumName}");
            }
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(PbItemUomQuery query) {
            SELECT("COUNT(1) n ");
            FROM_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(PbItemUomQuery query) {
            SELECT_ALL();
            FROM_SQL();
            FILTER_BY(query);
            return SqlUtil.paging(toString(), query);
        }
    }

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.QUERY_BY_ITEM_CODE)
    List<UomTrans> queryByItemCode(@Param("itemCode") String itemCode, @Param("mainUom") String mainUom);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.QUERY_MAIN_UOM)
    Uom queryMainUom(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode);

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(PbItemUomQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<PbItemUom> findPage(PbItemUomQuery query);

}
