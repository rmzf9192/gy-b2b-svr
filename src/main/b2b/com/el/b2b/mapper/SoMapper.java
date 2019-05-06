package com.el.b2b.mapper;

import com.el.b2b.api.SoQuery;
import com.el.b2b.domain.So;
import com.el.b2b.domain.SoD;
import com.el.b2b.domain.SoDelivered;
import com.el.b2b.domain.SoExport;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TB2bSoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description: 商品主文件Mapper
 */
public interface SoMapper extends TB2bSoMapper {



    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID AS id ");
            SELECT(" T.DOC_STATUS AS docStatus");//订单状态
            SELECT(" T.DOC_NO AS docNo");//单据编号
            SELECT(" U2.VAL_DESC  AS docType");// 订单类型
            SELECT(" O.OU_NAME AS ouName"); //收货单位  PB_OU
            SELECT(" T.DELIVER_ADDRESS AS deliverAddress");//收货地址
            SELECT(" T.DELIVER_CONTACT AS deliverContact");// 联系人
            SELECT(" T.MOBILE AS mobile");// 电话
            SELECT(" T.REMARK AS remark");//备注
            SELECT(" C.CUST_NAME AS custName");//客户名称
            SELECT(" T.DELIVER_ADDRESS_ID AS deliverAddressId");
            SELECT(" T.DELIVER_WAY AS deliverWay");//发运方式
            SELECT(" T.MODIFY_TIME AS modifyTime ");//更新时间
        }

        private void FROM_SQL() {
            FROM("B2B_SO T");
//            LEFT_OUTER_JOIN("PB_ADDRESS A ON A.ADDR_NO = T.DELIVER_ADDRESS_NO");
            JOIN("PB_CUST C ON (C.CUST_CODE = T.CUST_CODE AND C.OU_CODE=T.OU_CODE AND C.OU_ID <> 0 ) ");
            JOIN("PB_OU O ON  O.OU_CODE = T.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'SO_TYPE') U2 ON U2.VAL_CODE = T.DOC_TYPE");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (C.DELETE_FLAG <> 1 OR C.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (U2.DELETE_FLAG <> 1 OR U2.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(SoQuery query) {
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE =  '" + query.getOuCode() + "'");
            }
            if (!StringUtils.isEmpty(query.getCustCode())) {
                WHERE(" T.CUST_CODE =  '" + query.getCustCode() + "'");
            }
            if (!StringUtils.isEmpty(query.getEntId())) {
                WHERE(" T.SO_EMP_ID =  '" + query.getEntId() + "'");
            }
            if (!StringUtils.isEmpty(query.getDocNo())) {
                WHERE("T.DOC_NO like " + SqlUtil.toSqlLikeString(query.getDocNo()));
            }
            //过滤申请单号
            if(!StringUtils.isEmpty(query.getEhe8ptnum())){
                WHERE("V.EHE8PTNUM LIKE "+SqlUtil.toSqlLikeString(query.getEhe8ptnum()));
            }
            //过滤下单员工
            if (!StringUtils.isEmpty(query.getSoEmpId())) {
                WHERE(" T.SO_EMP_ID =  '" + query.getSoEmpId() + "'");
            }

            if (!StringUtils.isEmpty(query.getDocStatus())) {
                WHERE(" T.DOC_STATUS =  '" + query.getDocStatus() + "'");
            }

            if (!StringUtils.isEmpty(query.getDocStartDate()) && !StringUtils.isEmpty(query.getDocEndDate())) {
                WHERE("DATE_FORMAT(T.DOC_TIME,'%Y-%m-%d') >= DATE_FORMAT('" + query.getDocStartDate() + "','%Y-%m-%d')");
                WHERE("DATE_FORMAT(T.DOC_TIME,'%Y-%m-%d') <= DATE_FORMAT('" + query.getDocEndDate() + "','%Y-%m-%d')");
            } else if (!StringUtils.isEmpty(query.getDocStartDate())) {
                WHERE("DATE_FORMAT(T.DOC_TIME,'%Y-%m-%d') >= DATE_FORMAT('" + query.getDocStartDate() + "','%Y-%m-%d')");
            } else if (!StringUtils.isEmpty(query.getDocEndDate())) {
                WHERE("DATE_FORMAT(T.DOC_TIME,'%Y-%m-%d') <= DATE_FORMAT('" + query.getDocEndDate() + "','%Y-%m-%d')");
            }
            //判读发票号是否有值
            if(!StringUtils.isEmpty(query.getBillStatus())&&"Y".equals(query.getBillStatus())){
                WHERE("IFNULL(V.AB58BINVNUM2,'N')= 'Y'");
            }else if(!StringUtils.isEmpty(query.getBillStatus())&&"N".equals(query.getBillStatus())) {
                WHERE("IFNULL(V.AB58BINVNUM2,'N')= 'N'");
            }
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(SoQuery query) {
            SELECT(" COUNT(1) n");
            FROM_SQL();
            FILTER_BY(query);
            WHERE_DELETEFLAG_SQL();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(SoQuery query) {
            SELECT_ALL();
            FROM_SQL();
            FILTER_BY(query);
            WHERE_DELETEFLAG_SQL();
            ORDER_BY("T.MODIFY_TIME DESC, T.DOC_NO DESC ");
            return SqlUtil.paging(toString(), query);
        }

        private static final String FIND_BY_ID = "findById";

        public String findById() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ID =#{id}");
            return toString();
        }


        private static final String FIND_EXPORT_DOMAIN = "findExportDomain";

        public String findExportDomain(@Param("query") SoQuery query) {

            SELECT(" T.DOC_NO AS docNo");//单据编号     B2B_SO
            SELECT(" T.DOC_STATUS AS docStatus");//订单状态
            SELECT(" U2.VAL_DESC  AS docType");// 订单类型
            SELECT(" SOD.ID AS id ");
            SELECT(" SOD.SO_ID AS soId ");
            SELECT(" SOD.ITEM_CODE AS itemCode ");//产品编号
            SELECT(" SOD.QTY AS qty ");//数量
            SELECT(" SOD.PRICE * IFNULL(tf.UMCONV,1) AS price ");//单价
            SELECT(" SOD.AMT AS amt ");//金额
            SELECT(" SOD.PAY_STATUS AS status ");//状态（支付状态）
            SELECT(" I.ITEM_NAME AS itemName ");//产品名称
            SELECT(" I.SPEC AS spec ");//规格   PB_ITEM
            SELECT(" SOD.UOM AS uom");//单位

            SELECT(" SOD.ASSIGNED_QTY AS assignedQty ");//已分配数量
            SELECT(" SOD.UNASSIGNED_QTY AS unassignedQty ");//未分配数量
            SELECT(" SOD.CANCELLED_QTY AS cancelledQty ");//已取消数量
            SELECT(" SOD.ASSIGNED_AMT AS assignedAmt ");//已分配金额
            SELECT(" U1.VAL_DESC AS erpStatus ");//ERP订单状态
            SELECT(" I.spe8cjwlbm AS spe8cjwlbm ");//厂家物料编码
            SELECT(" I.SPAN8DSC AS span8dsc ");//生产厂家

            SELECT(" SOD.MODIFY_TIME AS modifyTime ");//更新时间

            FROM("B2B_SO T");
            JOIN("PB_OU O ON  O.OU_CODE = T.OU_CODE");
            JOIN("B2B_SO_D SOD ON SOD.SO_ID = T.ID");
            JOIN("PB_ITEM I ON I.ITEM_CODE = SOD.ITEM_CODE AND I.OU_CODE = T.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'ERP_STATUS') U1 ON U1.VAL_CODE = SOD.ERP_STATUS");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'SO_TYPE') U2 ON U2.VAL_CODE = T.DOC_TYPE");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE ,VAL_DESC  FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM' AND (DELETE_FLAG IS NULL OR DELETE_FLAG='0')) RU "
                + "ON RU.VAL_DESC=SOD.UOM ");
            LEFT_OUTER_JOIN("V41002 tf on tf.UMITM=I.OUTER_CODE and tf.UMMCU = ' ' and tf.UMRUM = I.UOM and tf.UMUM=RU.VAL_CODE");

            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (SOD.DELETE_FLAG <> 1 OR SOD.DELETE_FLAG IS NULL) ");
            WHERE(" (I.DELETE_FLAG <> 1 OR I.DELETE_FLAG IS NULL) ");
            FILTER_BY(query);
            if (!StringUtils.isEmpty(query.getErpStatus())) {
                WHERE(" SOD.ERP_STATUS =  '" + query.getErpStatus() + "'");
            }
            ORDER_BY("T.MODIFY_TIME DESC, T.DOC_NO DESC ");
            return toString();
        }

        private static final String FIND_EXPORT_DOMAIN_COUNT = "findExportDomainCount";

        public String findExportDomainCount(@Param("query") SoQuery query) {
            SELECT("COUNT(1) n");
            FROM("B2B_SO T");
            JOIN("PB_OU O ON  O.OU_CODE = T.OU_CODE");
            JOIN("B2B_SO_D SOD ON SOD.SO_ID = T.ID");
            JOIN("PB_ITEM I ON I.ITEM_CODE = SOD.ITEM_CODE  AND I.OU_CODE = T.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'ERP_STATUS') U1 ON U1.VAL_CODE = SOD.ERP_STATUS");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'SO_TYPE') U2 ON U2.VAL_CODE = T.DOC_TYPE");
            LEFT_OUTER_JOIN("(SELECT VAL_CODE ,VAL_DESC  FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM' AND (DELETE_FLAG IS NULL OR DELETE_FLAG='0')) RU "
                + "ON RU.VAL_DESC=SOD.UOM ");
            LEFT_OUTER_JOIN("V41002 tf on tf.UMITM=I.OUTER_CODE and tf.UMMCU = ' ' and tf.UMRUM = I.UOM and tf.UMUM=RU.VAL_CODE");
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (SOD.DELETE_FLAG <> 1 OR SOD.DELETE_FLAG IS NULL) ");
            WHERE(" (I.DELETE_FLAG <> 1 OR I.DELETE_FLAG IS NULL) ");
            FILTER_BY(query);
            if (!StringUtils.isEmpty(query.getErpStatus())) {
                WHERE(" SOD.ERP_STATUS =  '" + query.getErpStatus() + "'");
            }
            return toString();
        }
        private static final String Find_DELIVERED_DOMAIN_COUNT="findDeliveredDomainCount";

        public String findDeliveredDomainCount(@Param("query") SoQuery query){
            SELECT("COUNT(1) n ");
            FROM("VE84211W V");
            JOIN("B2B_SO T ON V.EHE8PTNUM = T.DOC_NO AND V.SDKCOO=T.OU_CODE");
            JOIN("B2B_SO_D SOD ON V.EHE8PTNUM=SOD.DOC_NO");
            FILTER_BY(query);
            return toString();
        }

        public static final String FIND_DELIVERED_DOMAIN="findDeliveredDomain";

        public String findDeliveredDomain(@Param("query") SoQuery query){
            SELECT("V.EHE8PTNUM AS ehe8ptnum");//申请单号
            SELECT("V.ID AS id");
            SELECT("V.SDDOCO AS sddoco");//ERP订单号
            SELECT("T.COMMENT_TIME AS commentTime");//提交日期
            SELECT("JULIAN_TO_DATE(V.SDADDJ) AS sdaddj");//发货日期
            SELECT("V.SDLITM AS sdlitm");//产品编码
            SELECT("V.SDDSC1 AS sddsc1");//名称
            SELECT("V.SDDSC2 AS sddsc2");//规格型号
            SELECT("V.IOLOT1 AS iolot1");//生产批号
            SELECT("V.FCE8ZZBM AS fce8zzbm");//注册证号
            SELECT("JULIAN_TO_DATE(V.FCD01) AS fcd01");//生效日期
            SELECT("JULIAN_TO_DATE(V.IOMMEJ) AS iommej");//失效日期
            SELECT("SOD.UOM AS sduom");//单位
            SELECT("V.DTTUPRC/10000 AS dttuprc");//含税单价
            SELECT("V.SDSOQS/10000 AS sdsoqs");//发货数量
            SELECT("V.DTTAEXP/100 AS dttaexp");//含税金额
            SELECT("V.AB58BINVNUM2 AS ab58binvnum2");//发票号码
            FROM("VE84211W V");
            JOIN("B2B_SO T ON V.EHE8PTNUM = T.DOC_NO AND V.SDKCOO=T.OU_CODE");
            JOIN("B2B_SO_D SOD ON V.EHE8PTNUM=SOD.DOC_NO");
            WHERE("V.SDLTTR>'560'");
            FILTER_BY(query);
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
    int findCount(SoQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<So> findPage(SoQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    So findById(@Param("id") long id);

    /**
     * 订单导出，展示主、表明细表
     *
     * @param query
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_EXPORT_DOMAIN_COUNT)
    int exportDomainCount(SoQuery query);

    /**
     * 订单导出，展示主、表明细表
     *
     * @param query
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_EXPORT_DOMAIN)
    List<SoExport> exportDomain(SoQuery query);

    /**
     * 已发货订单导出，获取总数
     * @param query
     * @return
     */
    @SelectProvider(type = SqlBuilder.class,method = SqlBuilder.Find_DELIVERED_DOMAIN_COUNT)
    int deliveredDomainCount(SoQuery query);

    @SelectProvider(type=SqlBuilder.class,method = SqlBuilder.FIND_DELIVERED_DOMAIN)
    List<SoDelivered> deliveredDomain(SoQuery query);
}
