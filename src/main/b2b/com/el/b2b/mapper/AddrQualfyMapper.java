package com.el.b2b.mapper;

import com.el.b2b.api.AddrQualfyQuery;
import com.el.b2b.domain.AddrQualfyDomain;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbAddressMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 客户证照信息相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
public interface AddrQualfyMapper extends TPbAddressMapper {
    class SqlBuilder extends Sql {
        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(AddrQualfyQuery query) {
            SELECT(" COUNT(1) n");
            FROM_SQL();
            FILTER_BY(query);
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_BY_ADDRNO_SQL = "findByAddrNoSql";

        public String findByAddrNoSql() {
            SELECT_ALL();
            FROM_SQL();
            WHERE(" T.ADDR_NO = #{addrNo} ");
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(AddrQualfyQuery query) {
            return SqlUtil.paging(findListSql(query), query);
        }

        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql() {
            SELECT_ALL();
            FROM_SQL();
            whereDeleteFlagSql();
            ORDER_BY(" T.ID DESC");
            return toString();
        }

        static final String FIND_BY_ID = "findById";

        public String findById(long id) {
            SELECT_ALL();
            FROM_SQL();
            WHERE(" T.ID = #{id}");
            whereDeleteFlagSql();
            return toString();
        }


        private void FILTER_BY(AddrQualfyQuery query) {
            if (StringUtils.notEmpty(query.getQualifyName())) {
                WHERE(" T.QUALIFY_NAME like " + SqlUtil.toSqlLikeString(query.getQualifyName()));
            }
            if (StringUtils.notEmpty(query.getQualifyType())) {
                WHERE(" T.QUALIFY_TYPE like " + SqlUtil.toSqlLikeString(query.getQualifyType()));
            }
        }

        public String findListSql(AddrQualfyQuery query) {
            SELECT_ALL();
            FROM_SQL();
            FILTER_BY(query);
            ORDER_BY(query);
            return toString();
        }

        private void SELECT_ALL() {
            SELECT("ID AS id");
            SELECT(" TENANT_ID AS tenantId");
            SELECT("ADDR_NO AS addrNo");
            SELECT("LINE_NO AS lineNo");
            SELECT("QUALIFY_TYPE AS qualifyType");
            SELECT("QUALIFY_NAME AS qualifyName");
            SELECT("QUALIFY_NO AS qualifyNo");
            SELECT("VALID_FROM AS validFrom");
            SELECT("VALID_TO AS validTo");
            SELECT("NEXT_CHECK_DATE AS nextCheckDate");
            SELECT("ATTACH_REPO AS attachRepo");
            SELECT("REMARK AS remark");
            SELECT(" CREATE_USER_ID AS createUserId");
            SELECT(" CREATE_TIME AS createTime");
            SELECT("MODIFY_USER_ID AS modifyUserId");
            SELECT("MODIFY_TIME AS modifyTime");
            SELECT("AUDIT_DATA_VERSION AS auditDataVersion");
            SELECT("DELETE_FLAG AS deleteFlag");
            SELECT("VALID_LV1 AS validLv1");
            SELECT("VALID_LV2 AS validLv2");
            SELECT("VALID_LV3 AS validLv3");
            SELECT("CERT_STATUS AS certStatus");

        }

        private void FROM_SQL() {
            FROM("PB_ADDR_QUALIFY T");
        }

        private void whereDeleteFlagSql() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
        }

    }

    /**
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(AddrQualfyQuery query);

    /**
     * @param query 查询条件
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<AddrQualfyDomain> findPage(AddrQualfyQuery query);


    /**
     * @return 查询所有客户证照信息
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_SQL)
    List<AddrQualfyDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    AddrQualfyDomain findById(long id);

    /**
     * @param addrNo 地址号
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ADDRNO_SQL)
    List<AddrQualfyDomain> findByAddrNo(@Param("addrNo") String addrNo);
}
