package com.el.b2b.mapper;

import com.el.b2b.api.CustQuery;
import com.el.b2b.domain.CustDomain;
import com.el.b2b.sys.TableMeta;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by jerry.feng
 * on 2018/5/11.
 */
public interface CustMapper {
    class SqlBuilder extends Sql {
        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(CustQuery query) {
            SELECT("COUNT(1) n");
            FROM_SQL();
            FILTER_BY(query);
            whereDeleteFlagSql();
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(CustQuery query) {
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

        static final String FIND_BY_CUST_CODE = "findByCustCode";

        public String findByCustCode() {
            SELECT_ALL();
            FROM_SQL();
            WHERE("T1.CUST_CODE = #{custCode}");
            whereDeleteFlagSql();
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


        private void FILTER_BY(CustQuery query) {
            if (StringUtils.notEmpty(query.getCustCode())) {
                WHERE("T1.CUST_CODE like " + SqlUtil.toSqlLikeString(query.getCustCode()));
            }
            if (StringUtils.notEmpty(query.getCustName())) {
                WHERE("T1.CUST_NAME like " + SqlUtil.toSqlLikeString(query.getCustName()));
            }
        }

        public String findListSql(CustQuery query) {
            SELECT_ALL();
            FROM_SQL();
            FILTER_BY(query);
            ORDER_BY(query);
            return toString();
        }

        private void SELECT_ALL() {
            SELECT("T1.ID AS id");
            SELECT("T1.TENANT_ID AS tenantId");
            SELECT("T1.OU_ID AS ouId");
            SELECT("T1.ORG_ID AS orgId");
            SELECT("T1.CUST_CODE AS custCode");
            SELECT("T1.CUST_NAME AS custName");
            SELECT("T1.CUST_ABBR AS custAbbr");
            SELECT("T1.CUST_TYPE AS custType");
            SELECT("T1.CUST_STATUS AS custStatus");
            SELECT("T1.PID AS pid");
            SELECT("T1.ADDR_NO AS addrNo");
            SELECT("T1.PINYIN AS pinyin");
            SELECT("T1.PINYIN_SH AS pinyinSh");
            SELECT("T1.COMP_CAPITAL AS compCapital");
            SELECT("T1.AGENT_EMP_ID AS agentEmpId");
            SELECT("T1.CUST_CURR AS custCurr");
            SELECT("T1.CREDIT_LIMIT AS creditLimit");
            SELECT("T1.CREDIT_BAL AS creditBal");
            SELECT("T1.PAY_METHOD AS payMethod");
            SELECT("T1.PAYMENT_TERM AS paymentTerm");
            SELECT("T1.SETTLE_TYPE AS settleType");
            SELECT("T1.RECON_PERIOD AS reconPeriod");
            SELECT("T1.SETTLE_MONTHLY_DAY AS settleMonthlyDay");
            SELECT("T1.DEF_ORG_ID AS defOrgId");
            SELECT("T1.DEF_WH_ID AS defWhId");
            SELECT("T1.C1 AS c1");
            SELECT("T1.C2 AS c2");
            SELECT("T1.C3 AS c3");
            SELECT("T1.COUNTRY AS country");
            SELECT("T1.POSTCODE AS postcode");
            SELECT("T1.REGION AS region");
            SELECT("T1.CUST_LEVEL AS custLevel");
            SELECT("T1.CUST_GROUP AS custGroup");
            SELECT("T1.VIP_NO AS vipNo");
            SELECT("T1.VIP_LEVEL AS vipLevel");
            SELECT("T1.VIP_GROUP AS vipGroup");
            SELECT("T1.CUST_POINT_FLAG AS custPointFlag");
            SELECT("T1.POINT AS point");
            SELECT("T1.CUST_SOURCE AS custSource");
            SELECT("T1.VALID_FROM AS validFrom");
            SELECT("T1.VALID_TO AS validTo");
            SELECT("T1.TAXPAYER_TYPE AS taxpayerType");
            SELECT("T1.INV_TYPE AS invType");
            SELECT("T1.INV_TITLE AS invTitle");
            SELECT("T1.INV_TAXNO AS invTaxno");
            SELECT("T1.TAX_CODE AS taxCode");
            SELECT("T1.IC_REGISTER_NO AS icRegisterNo");
            SELECT("T1.REGISTER_DATE AS registerDate");
            SELECT("T1.REGISTER_ADDRESS AS registerAddress");
            SELECT("T1.REGISTER_TRADEMARK_NO AS registerTrademarkNo");
            SELECT("T1.REGISTER_FUND AS registerFund");
            SELECT("T1.REGISTER_FUND_CURRY AS registerFundCurry");
            SELECT("T1.REPR AS repr");
            SELECT("T1.COMP_NAME AS compName");
            SELECT("T1.COMP_PROP AS compProp");
            SELECT("T1.COMP_SCALE AS compScale");
            SELECT("T1.COMP_BUSSADDR AS compBussaddr");
            SELECT("T1.COMP_MAINBUSS AS compMainbuss");
            SELECT("T1.COMP_BUSSRANGE AS compBussrange");
            SELECT("T1.REMARK AS remark");
            SELECT("T1.OUTER_CODE AS outerCode");
            SELECT("T1.CREATE_USER_ID AS createUserId");
            SELECT("T1.CREATE_TIME AS createTime");
            SELECT("T1.MODIFY_USER_ID AS modifyUserId");
            SELECT("T1.MODIFY_TIME AS modifyTime");
            SELECT("T1.AUDIT_DATA_VERSION AS auditDataVersion");
            SELECT("T1.DELETE_FLAG AS deleteFlag");
            SELECT("T1.UNIT_FLAG AS unitFlag");

            SELECT("T2.OU_NAME AS ouName");
            SELECT("T2.OU_CODE AS ouCode");


        }

        private void FROM_SQL() {
            FROM(TableMeta.PB_CUST.getName() + " T1");
            JOIN(TableMeta.PB_OU.getName() + " T2 ON T2.OU_CODE = T1.OU_CODE");
        }

        private void whereDeleteFlagSql() {

            WHERE(" (T1.DELETE_FLAG <> 1 OR T1.DELETE_FLAG IS NULL) ");
            WHERE(" (T2.DELETE_FLAG <> 1 OR T2.DELETE_FLAG IS NULL) ");
        }

    }

    /**
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(CustQuery query);

    /**
     * @param query 查询条件
     * @return 查询结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<CustDomain> findPage(CustQuery query);


    /**
     * @return 查询所有客户信息
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_SQL)
    List<CustDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    CustDomain findById(Long id);

    /**
     * 根据custCode查询具体数据
     *
     * @param custCode 查询条件
     * @return 返回结果
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_CUST_CODE)
    CustDomain findByCustCode(@Param("custCode") String custCode);
}
