package com.el.b2b.mapper;

import com.el.b2b.api.OuQuery;
import com.el.b2b.domain.OuDomain;
import com.el.b2b.sys.TableMeta;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbOuMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 公司信息相关业务
 * Created by jerry.feng
 * on 2018/5/10.
 */
@Mapper
public interface OuMapper extends TPbOuMapper {
    class SqlBuilder extends Sql {
        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(OuQuery query) {
            SELECT("COUNT(1) n");
            FROM_SQL();
            FILTER_BY(query);
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(OuQuery query) {
            return SqlUtil.paging(findListSql(query), query);
        }

        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql() {
            SELECT_ALL();
            FROM_SQL();
            whereDeleteFlagSql();
            ORDER_BY("T1.ID DESC");
            return toString();
        }


        static final String FIND_BY_ID = "findById";

        public String findById(@Param("id") Long id) {
            SELECT_ALL();
            FROM_SQL();
            WHERE("T1.ID = #{id}");
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_BY_OU_CODE = "findByOuCode";

        public String findByOuCode() {
            SELECT_ALL();
            FROM_SQL();
            WHERE("T1.OU_CODE = #{code}");
            whereDeleteFlagSql();
            return toString();
        }


        private void FILTER_BY(OuQuery query) {
            if (StringUtils.notEmpty(query.getOuCode())) {
                WHERE("T1.OU_CODE like " + SqlUtil.toSqlLikeString(query.getOuCode()));
            }
            if (StringUtils.notEmpty(query.getOuName())) {
                WHERE("T1.OU_NAME like " + SqlUtil.toSqlLikeString(query.getOuName()));
            }
        }

        public String findListSql(OuQuery query) {
            SELECT_ALL();
            FROM_SQL();
            whereDeleteFlagSql();
            FILTER_BY(query);
            ORDER_BY(query);
            return toString();
        }

        private void SELECT_ALL() {
            SELECT("T1.ID AS id");
            SELECT("T1.TENANT_ID AS tenantId");
            SELECT("T1.OU_CODE AS ouCode");
            SELECT("T1.OU_NAME AS ouName");
            SELECT("T1.OU_ABBR AS ouAbbr");
            SELECT("T1.OU_TYPE AS ouType");
            SELECT("T1.OU_STATUS AS ouStatus");
            SELECT("T1.PID AS pid");
            SELECT("T1.OU_CURR AS ouCurr");
            SELECT("T1.ADDR_NO AS addrNo");
            SELECT("T1.REGION AS region");
            SELECT("T1.REMARK AS remark");
            SELECT("T1.OUTER_CODE AS outerCode");
            SELECT("T1.CREATE_USER_ID AS createUserId");
            SELECT("T1.CREATE_TIME AS createTime");
            SELECT("T1.MODIFY_USER_ID AS modifyUserId");
            SELECT("T1.MODIFY_TIME AS modifyTime");
            SELECT("T1.AUDIT_DATA_VERSION AS auditDataVersion");
            SELECT("T1.DELETE_FLAG AS deleteFlag");

            SELECT("T2.ADDR_NAME AS addrName");
            SELECT("T2.DETAILADDR AS detailaddr");

        }

        private void FROM_SQL() {
            FROM(TableMeta.PB_OU.getName() + " T1");
            JOIN(TableMeta.PB_ADDRESS.getName() + " T2 ON T2.ADDR_NO = T1.ADDR_NO");
        }

        private void whereDeleteFlagSql() {
            WHERE("(T1.DELETE_FLAG <> 1 OR T1.DELETE_FLAG IS NULL)");
            WHERE("(T2.DELETE_FLAG <> 1 OR T2.DELETE_FLAG IS NULL)");
        }

    }

    /**
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(OuQuery query);

    /**
     * @param query 查询条件
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<OuDomain> findPage(OuQuery query);


    /**
     * @return 查询所有
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_SQL)
    List<OuDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    OuDomain findById(Long id);

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_OU_CODE)
    OuDomain findByOuCode(String code);


}
