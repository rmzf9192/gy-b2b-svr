package com.el.b2b.mapper;

import com.el.b2b.api.SoQuery;
import com.el.b2b.domain.SoDo;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.VE84211WMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/7/9
 * @Description: 订单收货Mapper
 */
public interface SoDoMapper extends VE84211WMapper {

    class SqlBuilder extends Sql {
        private void SELECT_ALL() {

            SELECT("CONCAT( T.SDKCOO, T.SDDOCO, T.SDDCTO ) AS id"); //虚拟主键
            SELECT(" T.EHE8PTNUM AS ehe8ptnum");//单据编号
            SELECT(" T.SDDOCO AS sddoco");//定单 号
            SELECT(" U2.VAL_DESC  AS sddcto");// 订单类型

            SELECT(" O.OU_NAME AS ouName"); //订单公司
            SELECT(" C.CUST_NAME as soOuName");//收货公司
            SELECT(" S.DELIVER_ADDRESS AS deliverAddress");//收货地址
            SELECT(" S.DELIVER_CONTACT AS deliverContact");// 联系人
            SELECT(" S.MOBILE AS mobile");// 电话
            SELECT(" S.REMARK AS remark");//备注

        }

        private void FROM_SQL(SoQuery query) {
            if (!StringUtils.isEmpty(query.getShipStatus())) {
                //待办事项 -  待收货过滤
                FROM("(SELECT DISTINCT T1.EHE8PTNUM,T1.SDDOCO,T1.SDDCTO,T1.SDKCOO  FROM VE84211W T1 WHERE T1.SHIP_STATUS = '" + query.getShipStatus() + "') T");
            } else {
                //订单收货
                FROM("(SELECT DISTINCT T1.EHE8PTNUM,T1.SDDOCO,T1.SDDCTO,T1.SDKCOO  FROM VE84211W T1) T");
            }

            JOIN("B2B_SO S ON  S.DOC_NO = T.EHE8PTNUM AND S.OU_CODE = T.SDKCOO");
//            LEFT_OUTER_JOIN("PB_ADDRESS A ON A.ADDR_NO = S.DELIVER_ADDRESS_NO");
            JOIN("PB_OU O ON  O.OU_CODE = T.SDKCOO");
            JOIN("PB_CUST C ON (C.CUST_CODE = S.CUST_CODE AND C.OU_CODE=S.OU_CODE) ");
//            JOIN("PB_OU O2 ON  O2.OU_CODE = C.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'SO_TYPE') U2 ON U2.VAL_CODE = S.DOC_TYPE");

        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (S.DELETE_FLAG <> 1 OR S.DELETE_FLAG IS NULL) ");
            WHERE(" (C.DELETE_FLAG <> 1 OR C.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
            WHERE(" (U2.DELETE_FLAG <> 1 OR U2.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(SoQuery query) {
            if (!StringUtils.isEmpty(query.getDocNo())) {
                WHERE("T.EHE8PTNUM like " + SqlUtil.toSqlLikeString(query.getDocNo()));
            }
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.SDKCOO =  '" + query.getOuCode() + "'");
            }
            //过滤下单员工
            if (!StringUtils.isEmpty(query.getCustCode())) {
                WHERE(" S.CUST_CODE =  '" + query.getCustCode() + "'");
            }

        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(SoQuery query) {
            SELECT(" COUNT(1) n");
            FROM_SQL(query);
            FILTER_BY(query);
            WHERE_DELETEFLAG_SQL();
            return toString();
        }

        static final String FIND_UNRECEIVE_COUNT_SQL = "findUnReceiveSoCountSql";

        public String findUnReceiveSoCountSql(SoQuery query) {
            SELECT(" COUNT(1) n");

            FROM("(SELECT DISTINCT T1.EHE8PTNUM,T1.SDDOCO,T1.SDDCTO,T1.SDKCOO  FROM VE84211W T1 WHERE T1.SHIP_STATUS = 0 ) T");
            JOIN("B2B_SO S ON  S.DOC_NO = T.EHE8PTNUM AND S.OU_CODE = T.SDKCOO");
//            JOIN("PB_ADDRESS A ON A.ADDR_NO = S.DELIVER_ADDRESS_NO");
            JOIN("PB_OU O ON  O.OU_CODE = T.SDKCOO");
            JOIN("PB_CUST C ON (C.CUST_CODE = S.CUST_CODE AND C.OU_CODE=S.OU_CODE) ");
            JOIN("PB_OU O2 ON  O2.OU_CODE = C.OU_CODE");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = 'SO' AND trim(UDC_CODE) = 'SO_TYPE') U2 ON U2.VAL_CODE = S.DOC_TYPE");
            FILTER_BY(query);
            WHERE_DELETEFLAG_SQL();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(SoQuery query) {
            SELECT_ALL();
            FROM_SQL(query);
            FILTER_BY(query);
            WHERE_DELETEFLAG_SQL();
            ORDER_BY("T.SDKCOO,T.SDDCTO,T.SDDOCO");
            return SqlUtil.paging(toString(), query);
        }

        static final String FIND_BY_ID_SQL = "findByIdSql";

        public String findByIdSql(@Param("id") String docNo, @Param("shipStatus") String shipStatus) {
            SELECT(" CONCAT(T.SDKCOO,T.SDDOCO,T.SDDCTO,T.SDLNID) AS id"); //虚拟主键
            SELECT(" T.EHE8PTNUM AS ehe8ptnum");//单据编号
            SELECT(" T.SDDOCO AS sddoco");//定单 号
            SELECT(" T.SDLITM AS sdlitm");//商品编码
            SELECT(" T.SDDSC1 AS sddsc1");//商品名称
            SELECT(" T.SDDSC2 AS sddsc2");//规格型号

            SELECT(" T.SDUORG AS sduorg");//定购 数量
            SELECT(" T.SDSOQS AS sdsoqs");//发运 数量
//            SELECT(" T.SDUOM AS sduom");//计量单位
            SELECT(" U.VAL_DESC AS sduom");//计量单位
            SELECT(" T.IOLOT1 AS iolot1");//生成批号
            SELECT(" JULIAN_TO_DATE(T.FCD01) AS fcd01Str");//生成日期  (应用函数)
            SELECT(" JULIAN_TO_DATE(T.IOMMEJ) AS iommejStr");//失效日期 (应用函数)
            SELECT(" T.SDUPRC AS sduprc");//单 价
            SELECT(" T.SDAEXP AS sdaexp");//总 价
            SELECT(" T.DTTUPRC AS dttuprc");//含税单 价
            SELECT(" T.DTTAEXP AS dttaexp");//含税总 价

            SELECT("T.SHIP_STATUS AS shipStatus");//状态
            SELECT("IF(T.SDLTTR > 600 ,'已开票','已发运') AS lttrStatus");//配送单状态
            SELECT(" I.SPE8CJWLBM  spe8cjwlbm");//厂家物料编码
            SELECT(" I.SPAN8DSC  span8dsc");//生产厂家

            FROM(" VE84211W T");
            JOIN("(SELECT VAL_CODE,VAL_DESC,DELETE_FLAG FROM PB_UDC WHERE trim(DOMAIN_CODE) = '00' AND trim(UDC_CODE) = 'UM') U ON U.VAL_CODE = T.SDUOM");
            JOIN("PB_ITEM I ON I.ITEM_CODE = T.SDLITM AND I.OU_CODE = T.SDKCOO");

            WHERE("CONCAT(T.SDKCOO,T.SDDOCO,T.SDDCTO)  =  #{id}");
            if (shipStatus != null) {
                WHERE("T.SHIP_STATUS =  #{shipStatus}");
            }
            ORDER_BY("T.SDKCOO,T.SDDCTO,T.SDDOCO,T.SDLITM,T.SDLNID");
            return toString();
        }

        static final String UPDATE_SHIP_STATUS = "updateShipStatusSql";

        public String updateShipStatusSql(@Param("ids") List<String> ids) {
            UPDATE(" VE84211W T");
            SET("SHIP_STATUS = 1");
            WHERE("CONCAT(T.SDKCOO,T.SDDOCO,T.SDDCTO,T.SDLNID) in " + SqlUtil.toSqlStringSet(ids));
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


    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_UNRECEIVE_COUNT_SQL)
    int findUnReceivedSoCount(SoQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<SoDo> findPage(SoQuery query);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID_SQL)
    List<SoDo> findSoDoById(@Param("id") String docNo, @Param("shipStatus") String shipStatus);

    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.UPDATE_SHIP_STATUS)
    int updateShipStatus(@Param("ids") List<String> ids);

}
