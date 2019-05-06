package com.el.b2b.mapper;

import com.el.b2b.api.SoDQuery;
import com.el.b2b.domain.SoD;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TB2bSoDMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/28
 * @Description: 订单明细Mapper
 */
public interface SoDMapper extends TB2bSoDMapper {


    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID AS id ");
            SELECT(" T.TENANT_ID AS tenantId ");
            SELECT(" T.SO_ID AS soId ");
            SELECT(" T.LINE_NO AS lineNo ");
            SELECT(" T.WH_ID AS whId ");
            SELECT(" T.ITEM_ID AS itemId ");
            SELECT(" T.ITEM_CODE AS itemCode ");
            SELECT(" T.ITEM_NAME AS itemName ");
            SELECT(" T.SKU_ID AS skuId ");
            SELECT(" T.LINE_TYPE AS lineType ");
            SELECT(" T.LINE_STATUS AS lineStatus ");
            SELECT(" T.PAY_STATUS AS payStatus ");
            SELECT(" T.QTY AS qty ");
            SELECT(" T.UOM AS uom ");
            SELECT(" T.WEIGHT AS weight ");
            SELECT(" T.WEIGHT_UOM AS weightUom ");
            SELECT(" T.WEIGHT_RATIO AS weightRatio ");
            SELECT(" T.BASE_PRICE AS basePrice ");
            SELECT(" T.PRICE_TYPE AS priceType ");
            SELECT(" T.PRICE AS price ");
            SELECT(" T.DISC_TYPE AS discType ");
            SELECT(" T.DISC_RATIO AS discRatio ");
            SELECT(" T.DISC_AMT AS discAmt ");
            SELECT(" T.TAX_RATE AS taxRate ");
            SELECT(" T.TAX_AMT AS taxAmt ");
            SELECT(" T.NET_AMT AS netAmt ");
            SELECT(" T.AMT AS amt ");
            SELECT(" T.REMARK AS remark ");
            SELECT(" T.CREATE_USER_ID AS createUserId ");
            SELECT(" T.CREATE_TIME AS createTime ");
            SELECT(" T.MODIFY_USER_ID AS modifyUserId ");
            SELECT(" T.MODIFY_TIME AS modifyTime ");
            SELECT(" T.AUDIT_DATA_VERSION AS auditDataVersion ");
            SELECT(" T.DELETE_FLAG AS deleteFlag ");

            SELECT(" T.ASSIGNED_QTY AS assignedQty ");//已分配数量
            SELECT(" T.UNASSIGNED_QTY AS unassignedQty ");//未分配数量
            SELECT(" T.CANCELLED_QTY AS cancelledQty ");//已取消数量
            SELECT(" T.ASSIGNED_AMT AS assignedAmt ");//已分配金额
            SELECT(" T.ERP_STATUS AS erpStatus ");//ERP订单状态
            SELECT(" T.OU_CODE AS ouCode");
            SELECT(" T.DOC_NO AS docNo");
        }

        private void FROM_SQL() {
            FROM("B2B_SO_D T");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(SoDQuery query) {
            if (!StringUtils.isEmpty(query.getId())) {
                WHERE(" T.ID = " + query.getId());
            }
            if (!StringUtils.isEmpty(query.getSoId())) {
                WHERE(" T.SO_ID = " + query.getSoId());
            }
        }


        static final String FIND_BY_SOID_SQL = "findBySoIdSql";

        public String findBySoIdSql() {
            SELECT(" T.ID AS id ");
            SELECT(" T.SO_ID AS soId ");
            SELECT(" S.DOC_NO AS docNo");//单据编号     B2B_SO
            SELECT(" T.OU_CODE AS ouCode");//单据编号     B2B_SO
            SELECT(" I.ITEM_CODE AS itemCode ");//产品编号
            SELECT(" I.ITEM_NAME AS itemName ");//产品名称
            SELECT(" I.SPEC AS spec ");//规格   PB_ITEM
            SELECT(" T.QTY AS qty ");//数量
            SELECT(" T.UOM  uom");
            SELECT(" U.VAL_CODE  uomCode");
            SELECT(" T.PRICE AS price ");//单价
            SELECT(" T.AMT AS amt ");//金额
            SELECT(" T.REMARK AS remark ");//备注
            SELECT(" T.PAY_STATUS AS status ");//状态（支付状态）

            SELECT(" T.ASSIGNED_QTY AS assignedQty ");//已分配数量
            SELECT(" T.UNASSIGNED_QTY AS unassignedQty ");//未分配数量
            SELECT(" T.CANCELLED_QTY AS cancelledQty ");//已取消数量
            SELECT(" T.ASSIGNED_AMT AS assignedAmt ");//已分配金额
            SELECT(" U1.VAL_DESC AS erpStatus ");//ERP订单状态

            SELECT(" T.ERP_STATUS AS sodStatus");//sod订单状态

            SELECT(" I.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" I.SPAN8DSC  span8dsc");//生产厂家
            SELECT(" I.SPAN8  span8");//生产厂家编码

            FROM("B2B_SO_D T");
            JOIN("B2B_SO S ON S.ID = T.SO_ID");
            JOIN("PB_ITEM I ON I.ITEM_CODE = T.ITEM_CODE AND I.OU_CODE = S.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_DESC = T.UOM");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'ERP_STATUS') U1 ON U1.VAL_CODE = T.ERP_STATUS");

            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (S.DELETE_FLAG <> 1 OR S.DELETE_FLAG IS NULL) ");
            WHERE(" (I.DELETE_FLAG <> 1 OR I.DELETE_FLAG IS NULL) ");
            WHERE(" (U.DELETE_FLAG <> 1 OR U.DELETE_FLAG IS NULL) ");
            WHERE(" T.SO_ID =#{soId}");
            return toString();
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(SoDQuery query) {
            SELECT("COUNT(1) as n");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(SoDQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY(" T.ID  DESC");
            return SqlUtil.paging(toString(), query);
        }

        private static final String FIND_BY_ID = "findById";


        public String findById() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ID  =  #{id} ");
            return toString();
        }

        private static final String FIND_SOD_BY_Kdi = "findSodByKdi";

        public String findSodByKdi() {
            SELECT(" T.ERP_STATUS AS erpStatus ");//单价
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ITEM_CODE = #{itemCode} and T.DOC_NO= #{docNo} and T.OU_CODE= #{ouCode}");
            return toString();
        }

    }

    @SelectProvider(type = SoDMapper.SqlBuilder.class, method = SoDMapper.SqlBuilder.FIND_SOD_BY_Kdi)
    String findSodByKdi(@Param("itemCode") String itemCode,@Param("docNo") String docNo,@Param("ouCode") String ouCode);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    SoD findById(@Param("id") long id);


    /**
     * 按soId查询
     *
     * @param soId
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_SOID_SQL)
    List<SoD> findBySoId(@Param("soId") long soId);

    @Delete({"DELETE FROM B2B_SO_D WHERE ID = #{id}"})
    int deleteBySoId(long id);
}
