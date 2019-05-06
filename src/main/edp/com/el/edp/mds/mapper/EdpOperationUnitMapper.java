package com.el.edp.mds.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.mds.api.EdpOperationUnitQuery;
import com.el.edp.mds.domain.EdpOperationUnit;
import com.el.edp.util.EdpCodeName;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.shiro.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
public interface EdpOperationUnitMapper {

    class SqlBuilder extends Sql {

        private static final String OPERATION_UNITS_BY_SQL = "operationUnitsBySql";

        public String operationUnitsBySql(EdpOperationUnitQuery query) {
            SELECT("ID");
            SELECT("OU_CODE ouCode");
            SELECT("OU_NAME ouName");
            SELECT("OU_TYPE ouType");
            SELECT("OU_STATUS ouStatus");
            SELECT("OU_CURR ouCurr");

            FROM("PB_OU");
            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String OPERATION_UNIT_COUNT_BY_SQL = "operationUnitCountBySql";

        public String operationUnitCountBySql(EdpOperationUnitQuery query) {
            FROM("PB_OU");
            filterBy(query);
            return toCountSql();
        }

        private void filterBy(EdpOperationUnitQuery query) {
            if (StringUtils.hasText(query.getOuCode())) {
                WHERE("OU_CODE like " + SqlUtil.toSqlLikeString(query.getOuCode()));
            }
            if (StringUtils.hasText(query.getOuName())) {
                WHERE("OU_NAME like " + SqlUtil.toSqlLikeString(query.getOuName()));
            }
            if (query.getDeleteFlag() != null) {
                WHERE("DELETE_FLAG = #{deleteFlag}");
            }
        }

        private static final String OPERATION_UNIT_BY_SQL = "operationUnitBySql";

        public String operationUnitBySql() {
            SELECT("ID");
            SELECT("OU_CODE ouCode");
            SELECT("OU_NAME ouName");
            SELECT("OU_ABBR ouAbbr");
            SELECT("OU_TYPE ouType");
            SELECT("OU_STATUS ouStatus");
            SELECT("PID pid");
            SELECT("OU_CURR ouCurr");
            SELECT("ADDR_NO addrNo");
            SELECT("REGION region");
            SELECT("REMARK remark");
            SELECT("OUTER_CODE outerCode");
            SELECT("DELETE_FLAG deleteFlag");

            FROM("PB_OU");
            WHERE("ID = #{id}");
            return toString();
        }

        private static final String OPERATION_UNITS_SQL = "operationUnitsSql";

        public String operationUnitsSql(String codeName) {
            SELECT("ID, OU_CODE code, OU_NAME name");
            FROM("PB_OU");
            WHERE("(DELETE_FLAG <> 1 OR DELETE_FLAG IS NULL)");
            WHERE("OU_CODE IN (select VAL_CODE from PB_UDC where DOMAIN_CODE='B2B' and UDC_CODE='OU')");

            if (StringUtils.hasText(codeName)) {
                WHERE("(OU_CODE like " + SqlUtil.toSqlLikeString(codeName)
                    + " or OU_NAME like " + SqlUtil.toSqlLikeString(codeName)
                    + ")");
            }
            return toString();
        }
    }

    /**
     * @param query 搜索条件
     * @return 公司列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.OPERATION_UNITS_BY_SQL)
    List<EdpOperationUnit> operationUnitsBy(EdpOperationUnitQuery query);

    /**
     * @param query 搜索条件
     * @return 公司总记录
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.OPERATION_UNIT_COUNT_BY_SQL)
    int operationUnitCountBy(EdpOperationUnitQuery query);

    /**
     * @param id 公司主键
     * @return 公司详情
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.OPERATION_UNIT_BY_SQL)
    EdpOperationUnit operationUnitBy(long id);

    /**
     * @param codeName 编码或名称
     * @return 公司列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.OPERATION_UNITS_SQL)
    List<EdpCodeName> operationUnits(String codeName);
}
