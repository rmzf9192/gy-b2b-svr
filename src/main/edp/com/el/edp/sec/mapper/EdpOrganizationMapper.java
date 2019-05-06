package com.el.edp.sec.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.sec.api.EdpOrganizationQuery;
import com.el.edp.sec.domain.EdpOrganization;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.shiro.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
public interface EdpOrganizationMapper {

    class SqlBuilder extends Sql {

        private static final String ORGANIZATIONS_BY_SQL = "organizationsBySql";

        public String organizationsBySql(EdpOrganizationQuery query) {
            SELECT("o.ID");
            SELECT("o.ORG_CODE orgCode");
            SELECT("o.ORG_NAME orgName");
            SELECT("o.ORG_TYPE orgType");
            SELECT("o.ORG_STATUS orgStatus");
            SELECT("o.ORG_LEVEL orgLevel");
            SELECT("u.OU_NAME ouName");

            FROM("PB_ORG o");
            JOIN("PB_OU u on u.ID = o.OU_ID");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String ORGANIZATION_COUNT_BY_SQL = "organizationCountBySql";

        public String organizationCountBySql(EdpOrganizationQuery query) {
            FROM("PB_ORG o");
            filterBy(query);
            return toCountSql();
        }

        private void filterBy(EdpOrganizationQuery query) {
            if (StringUtils.hasText(query.getOrgCode())) {
                WHERE("o.ORG_CODE like " + SqlUtil.toSqlLikeString(query.getOrgCode()));
            }
            if (StringUtils.hasText(query.getOrgName())) {
                WHERE("o.ORG_NAME like " + SqlUtil.toSqlLikeString(query.getOrgName()));
            }
        }

        private static final String ORGANIZATION_BY_SQL = "organizationBySql";

        public String organizationBySql() {
            SELECT("ID");
            SELECT("OU_ID ouId");
            SELECT("ORG_CODE orgCode");
            SELECT("ORG_NAME orgName");
            SELECT("ORG_ABBR orgAbbr");
            SELECT("ORG_TYPE orgType");
            SELECT("ORG_STATUS orgStatus");
            SELECT("PID pid");
            SELECT("ADDR_NO addrNo");
            SELECT("ORG_LEVEL orgLevel");
            SELECT("PIC_EMP_ID picEmpId");
            SELECT("REMARK remark");
            SELECT("OUTER_CODE outerCode");
            SELECT("DELETE_FLAG deleteFlag");

            FROM("PB_ORG");
            WHERE("ID = #{id}");
            return toString();
        }
    }

    /**
     * @param query 搜索条件
     * @return 组织列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ORGANIZATIONS_BY_SQL)
    List<EdpOrganization> organizationsBy(EdpOrganizationQuery query);

    /**
     * @param query 搜索条件
     * @return 组织总记录
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ORGANIZATION_COUNT_BY_SQL)
    int organizationCountBy(EdpOrganizationQuery query);

    /**
     * @param id 组织主键
     * @return 组织详情
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ORGANIZATION_BY_SQL)
    EdpOrganization organizationBy(long id);
}
