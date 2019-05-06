package com.el.edp.mds.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.mds.api.EdpEmployeeQuery;
import com.el.edp.mds.domain.EdpEmployee;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.shiro.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
public interface EdpEmployeeMapper {

    class SqlBuilder extends Sql {

        private static final String EMPLOYEES_BY_SQL = "employeesBySql";

        public String employeesBySql(EdpEmployeeQuery query) {
            SELECT("e.ID");
            SELECT("e.EMP_CODE empCode");
            SELECT("e.EMP_NAME empName");
            SELECT("e.EMP_TYPE empType");
            SELECT("e.EMP_STATUS empStatus");
            SELECT("e.EMP_GENDER empGender");
            SELECT("e.EMP_LEVEL empLevel");
            SELECT("u.OU_NAME ouName");
            SELECT("o.ORG_NAME orgName");

            FROM("PB_EMP e");
            JOIN("PB_OU u on u.ID = e.OU_ID");
            JOIN("PB_ORG o on o.ID = e.ORG_ID");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String EMPLOYEE_COUNT_BY_SQL = "employeeCountBySql";

        public String employeeCountBySql(EdpEmployeeQuery query) {
            FROM("PB_EMP e");
            filterBy(query);
            return toCountSql();
        }

        private void filterBy(EdpEmployeeQuery query) {
            if (StringUtils.hasText(query.getEmpCode())) {
                WHERE("e.EMP_CODE like " + SqlUtil.toSqlLikeString(query.getEmpCode()));
            }
            if (StringUtils.hasText(query.getEmpName())) {
                WHERE("e.EMP_NAME like " + SqlUtil.toSqlLikeString(query.getEmpName()));
            }
        }

        private static final String EMPLOYEE_BY_SQL = "employeeBySql";

        public String employeeBySql() {
            SELECT("ID");
            SELECT("OU_ID ouId");
            SELECT("ORG_ID orgId");
            SELECT("USER_ID userId");
            SELECT("EMP_CODE empCode");
            SELECT("EMP_NAME empName");
            SELECT("EMP_TYPE empType");
            SELECT("EMP_STATUS empStatus");
            SELECT("ENGLISH_NAME englishName");
            SELECT("ADDR_NO addrNo");
            SELECT("PID pid");
            SELECT("EMP_GENDER empGender");
            SELECT("BIRTH_DATE birthDate");
            SELECT("EMAIL email");
            SELECT("MOBILE mobile");
            SELECT("ID_TYPE idType");
            SELECT("ID_NO idNo");
            SELECT("POSITION position");
            SELECT("EMP_LEVEL empLevel");
            SELECT("HOBBY hobby");
            SELECT("NATIVE_PLACE nativePlace");
            SELECT("RACE race");
            SELECT("POLITY polity");
            SELECT("EDUCATION education");
            SELECT("SPECIALTY specialty");
            SELECT("GRADUATED_FROM graduatedFrom");
            SELECT("DEGREE degree");
            SELECT("WORKEXP workexp");
            SELECT("TRAINING training");
            SELECT("TITLE title");
            SELECT("AWARD award");
            SELECT("PERFORMANCE performance");
            SELECT("ATTESTATION attestation");
            SELECT("JOININ_DATE joininDate");
            SELECT("LEAVE_DATE leaveDate");
            SELECT("ESCUAGE escuage");
            SELECT("SKILLS skills");
            SELECT("HEALTHY_CONDITION healthyCondition");
            SELECT("CRIMINAL_RECORD criminalRecord");
            SELECT("REMARK remark");
            SELECT("OUTER_CODE outerCode");
            SELECT("DELETE_FLAG deleteFlag");

            FROM("PB_EMP");
            WHERE("ID = #{id}");
            return toString();
        }
    }

    /**
     * @param query 搜索条件
     * @return 员工列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.EMPLOYEES_BY_SQL)
    List<EdpEmployee> employeesBy(EdpEmployeeQuery query);

    /**
     * @param query 搜索条件
     * @return 员工总记录
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.EMPLOYEE_COUNT_BY_SQL)
    int employeeCountBy(EdpEmployeeQuery query);

    /**
     * @param id 员工主键
     * @return 员工详情
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.EMPLOYEE_BY_SQL)
    EdpEmployee employeeBy(long id);
}
