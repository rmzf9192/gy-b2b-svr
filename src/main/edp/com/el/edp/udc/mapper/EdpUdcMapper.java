package com.el.edp.udc.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.udc.api.EdpUdcMgmtQuery;
import com.el.edp.udc.domain.*;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author neo.pan
 * @since 17/8/1
 */
public interface EdpUdcMapper {

    class SqlBuilder extends Sql {

        private static final String UDC_ITEMS_OF_SQL = "udcItemsOfSql";

        public String udcItemsOfSql() {
            SELECT("ID id, VAL_CODE valCode, VAL_DESC valDesc");
            SELECT("VAL_SPHD1 valSphd1, VAL_SPHD2 valSphd2, SORT_NO sortNo");

            FROM("PB_UDC");
            WHERE("DOMAIN_CODE = #{domainCode}");
            WHERE("UDC_CODE = #{udcCode}");
            WHERE("VAL_CODE <> ' '");
            ORDER_BY("SORT_NO");
            return toString();
        }

        private static final String UDCS_OF_SQL = "udcsOfSql";

        public String udcsOfSql(@Param("q") EdpUdcMgmtQuery query) {
            SELECT("ID, DOMAIN_CODE domainCode, UDC_CODE udcCode");
            SELECT("UDC_NAME udcName, UDC_DESC udcDesc, SYS_FLAG sysFlag");
            FROM("PB_UDC_TYPE");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String UDC_COUNT_OF_SQL = "udcCountOfSql";

        public String udcCountOfSql(@Param("q") EdpUdcMgmtQuery query) {
            FROM("PB_UDC_TYPE");
            filterBy(query);
            return toCountSql();
        }

        private void filterBy(EdpUdcMgmtQuery query) {
            WHERE("SYS_FLAG = 0");

            if (StringUtils.hasText(query.getUdcLike())) {
                val udcLikeSql = SqlUtil.toSqlLikeString(query.getUdcLike());
                WHERE("(DOMAIN_CODE like " + udcLikeSql
                    + " or UDC_CODE like " + udcLikeSql
                    + " or UDC_NAME like " + udcLikeSql
                    + ")"
                );
            }
        }
    }

    @Select({
        "select ID, DOMAIN_CODE domainCode, UDC_CODE udcCode,",
        "       UDC_NAME udcName, UDC_DESC udcDesc, SYS_FLAG sysFlag",
        "  from PB_UDC_TYPE"
    })
    List<EdpUdc> udcs();

    /**
     * @param id UDC主键
     * @return UDC详情
     */
    @Select({
        "select ID, DOMAIN_CODE domainCode, UDC_CODE udcCode,",
        "       UDC_NAME udcName, UDC_DESC udcDesc, SYS_FLAG sysFlag",
        "  from PB_UDC_TYPE where ID = #{id}"
    })
    EdpUdc udcOf(long id);

    /**
     * @param udc UDC
     * @return UDC明细
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.UDC_ITEMS_OF_SQL)
    List<EdpUdcItem> udcItemsOf(EdpUdc udc);

    /**
     * @param query 搜索条件
     * @return UDC列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.UDCS_OF_SQL)
    List<EdpUdc> udcsOf(@Param("q") EdpUdcMgmtQuery query);

    /**
     * @param query 搜索条件
     * @return UDC总记录
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.UDC_COUNT_OF_SQL)
    int udcCountOf(@Param("q") EdpUdcMgmtQuery query);

//    @Insert({
//        "insert into PB_UDC_TYPE (" +
////            "ID, " +
//            "DOMAIN_CODE, ",
//        "UDC_CODE, UDC_NAME, ",
//        "UDC_DESC, SYS_FLAG",
//        ")",
//        "values (" +
////            "#{id,jdbcType=DECIMAL}, " +
//            "#{domainCode,jdbcType=NVARCHAR}, ",
//        "#{udcCode,jdbcType=NVARCHAR}, #{udcName,jdbcType=NVARCHAR}, ",
//        "#{udcDesc,jdbcType=NVARCHAR}, #{sysFlag,jdbcType=DECIMAL} ",
//        ")"
//    })
//    int insert(EdpUdcDomain record);



//    @Update({
//        "update PB_UDC_TYPE",
//        "set ",
//        "DOMAIN_CODE = #{domainCode,jdbcType=NVARCHAR},",
//        "UDC_CODE = #{udcCode,jdbcType=NVARCHAR},",
//        "UDC_NAME = #{udcName,jdbcType=NVARCHAR},",
//        "UDC_DESC = #{udcDesc,jdbcType=NVARCHAR},",
//        "SYS_FLAG = #{sysFlag,jdbcType=DECIMAL} ",
//        "where ID = #{id,jdbcType=DECIMAL}"
//    })
//    int updateByPrimaryKey(EdpUdcDomain record);

//    @Insert({
//        "insert into PB_UDC (" ,
////            "ID, " ,
//            "VAL_CODE, ",
//        "VAL_DESC, VAL_SPHD1, ",
//        "VAL_SPHD2, SORT_NO,",
//        "DOMAIN_CODE,UDC_CODE,",
//        "UDC_TYPE_ID",
//        ")",
//        "values (" ,
////            "#{id,jdbcType=DECIMAL}, " +
//            "#{valCode,jdbcType=NVARCHAR}, ",
//        "#{valDesc,jdbcType=NVARCHAR}, #{valSphd1,jdbcType=NVARCHAR}, ",
//        "#{valSphd2,jdbcType=NVARCHAR}, #{sortNo,jdbcType=DECIMAL}, ",
//        "#{domainCode,jdbcType=NVARCHAR}, #{udcCode,jdbcType=NVARCHAR}, ",
//        " #{udcTypeId,jdbcType=DECIMAL}",
//        ")"
//    })
//    int insertItem(EdpUdcItem record);
//    @Update({
//        "update PB_UDC",
//        "set ",
//        "VAL_CODE = #{valCode,jdbcType=NVARCHAR},",
//        "VAL_DESC = #{valDesc,jdbcType=NVARCHAR},",
//        "VAL_SPHD1 = #{valSphd1,jdbcType=NVARCHAR},",
//        "VAL_SPHD2 = #{valSphd2,jdbcType=NVARCHAR},",
//        "SORT_NO = #{sortNo,jdbcType=DECIMAL}",
//        "where ID = #{id,jdbcType=DECIMAL}"
//    })
//    int updateItemByPrimaryKey(EdpUdcItem record);


    @Insert({
        "insert into PB_UDC_TYPE (" +
//            "TENANT_ID, " +
            "DOMAIN_CODE, ",
        "UDC_CODE, UDC_NAME, ",
        "UDC_DESC, SYS_FLAG, " +
//            "CREATE_USER_ID, ",
//        "CREATE_TIME, MODIFY_USER_ID, ",
//        "MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG)",
        "values (" +
//            "#{tenantId,jdbcType=DECIMAL}, " +
            "#{domainCode,jdbcType=VARCHAR}, ",
        "#{udcCode,jdbcType=VARCHAR}, #{udcName,jdbcType=VARCHAR}, ",
        "#{udcDesc,jdbcType=VARCHAR}, #{sysFlag,jdbcType=BIT}," +
//            " #{createUserId,jdbcType=DECIMAL}, ",
//        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
//        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty="id", before=false, resultType=Long.class)
    int insert(UdcDomain record);

    @Update({
        "update PB_UDC_TYPE",
        "set " +
//            " TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
        "DOMAIN_CODE = #{domainCode,jdbcType=VARCHAR},",
        "UDC_CODE = #{udcCode,jdbcType=VARCHAR},",
        "UDC_NAME = #{udcName,jdbcType=VARCHAR},",
        "UDC_DESC = #{udcDesc,jdbcType=VARCHAR},",
        "SYS_FLAG = #{sysFlag,jdbcType=BIT},",
//        "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
//        "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
//        "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
//        "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
//        "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
        "DELETE_FLAG = #{deleteFlag,jdbcType=BIT}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UdcDomain record);

    @Insert({
        "insert into PB_UDC (" +
//            "TENANT_ID," +
            " UDC_TYPE_ID, ",
        "DOMAIN_CODE, UDC_CODE, ",
        "VAL_CODE, LANG, VAL_DESC, ",
        "VAL_DESC2, SORT_NO, ",
        "ENABLE_FLAG, VAL_SPHD1, ",
        "VAL_SPHD2, VAL_SPHD3, ",
        "VAL_SPHD4, VAL_SPHD5, ",
        "VAL_SPHD6, VAL_SPHD7, ",
        "VAL_SPHD8, VAL_SPHD9, ",
        "VAL_SPHD10, PID, ",
//        "MODIFY_USER_ID, MODIFY_TIME, ",
//        "AUDIT_DATA_VERSION, CREATE_USER_ID, ",
//        "CREATE_TIME, " +
            "DELETE_FLAG)",
        "values (" +
//            "#{tenantId,jdbcType=DECIMAL}," +
            " #{udcTypeId,jdbcType=DECIMAL}, ",
        "#{domainCode,jdbcType=VARCHAR}, #{udcCode,jdbcType=VARCHAR}, ",
        "#{valCode,jdbcType=VARCHAR}, #{lang,jdbcType=VARCHAR}, #{valDesc,jdbcType=VARCHAR}, ",
        "#{valDesc2,jdbcType=VARCHAR}, #{sortNo,jdbcType=DOUBLE}, ",
        "#{enableFlag,jdbcType=BIT}, #{valSphd1,jdbcType=VARCHAR}, ",
        "#{valSphd2,jdbcType=VARCHAR}, #{valSphd3,jdbcType=VARCHAR}, ",
        "#{valSphd4,jdbcType=VARCHAR}, #{valSphd5,jdbcType=VARCHAR}, ",
        "#{valSphd6,jdbcType=VARCHAR}, #{valSphd7,jdbcType=VARCHAR}, ",
        "#{valSphd8,jdbcType=VARCHAR}, #{valSphd9,jdbcType=VARCHAR}, ",
        "#{valSphd10,jdbcType=VARCHAR}, #{pid,jdbcType=DECIMAL}, ",
//        "#{modifyUserId,jdbcType=DECIMAL}, #{modifyTime,jdbcType=TIMESTAMP}, ",
//        "#{auditDataVersion,jdbcType=DECIMAL}, #{createUserId,jdbcType=DECIMAL}, ",
//        "#{createTime,jdbcType=TIMESTAMP}, " +
            "#{deleteFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty="id", before=false, resultType=Long.class)
    int insertItem(UdcItemDomain record);

    @Update({
        "update PB_UDC",
        "set " +
//            "TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
        "UDC_TYPE_ID = #{udcTypeId,jdbcType=DECIMAL},",
        "DOMAIN_CODE = #{domainCode,jdbcType=VARCHAR},",
        "UDC_CODE = #{udcCode,jdbcType=VARCHAR},",
        "VAL_CODE = #{valCode,jdbcType=VARCHAR},",
        "LANG = #{lang,jdbcType=VARCHAR},",
        "VAL_DESC = #{valDesc,jdbcType=VARCHAR},",
        "VAL_DESC2 = #{valDesc2,jdbcType=VARCHAR},",
        "SORT_NO = #{sortNo,jdbcType=DOUBLE},",
        "ENABLE_FLAG = #{enableFlag,jdbcType=BIT},",
        "VAL_SPHD1 = #{valSphd1,jdbcType=VARCHAR},",
        "VAL_SPHD2 = #{valSphd2,jdbcType=VARCHAR},",
        "VAL_SPHD3 = #{valSphd3,jdbcType=VARCHAR},",
        "VAL_SPHD4 = #{valSphd4,jdbcType=VARCHAR},",
        "VAL_SPHD5 = #{valSphd5,jdbcType=VARCHAR},",
        "VAL_SPHD6 = #{valSphd6,jdbcType=VARCHAR},",
        "VAL_SPHD7 = #{valSphd7,jdbcType=VARCHAR},",
        "VAL_SPHD8 = #{valSphd8,jdbcType=VARCHAR},",
        "VAL_SPHD9 = #{valSphd9,jdbcType=VARCHAR},",
        "VAL_SPHD10 = #{valSphd10,jdbcType=VARCHAR},",
        "PID = #{pid,jdbcType=DECIMAL},",
//        "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
//        "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
//        "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
//        "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
//        "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
        "DELETE_FLAG = #{deleteFlag,jdbcType=BIT}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateItemByPrimaryKey(UdcItemDomain record);

}
