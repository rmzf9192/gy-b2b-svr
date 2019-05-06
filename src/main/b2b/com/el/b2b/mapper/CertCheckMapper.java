package com.el.b2b.mapper;

import com.el.b2b.domain.AddrQualfyDomain;
import com.el.b2b.domain.CertCheckDomain;
import com.el.b2b.domain.PbItem;
import com.el.core.jdbc.Sql;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 证照分离：校验
 * @Auther: arthur.xu
 * @Date: 2018/9/25
 */
public interface CertCheckMapper {

    class SqlBuilder extends Sql {
        private static final String UNIT_FLAG_BY_CUSTCODE = "unitFlagByCustCode";

        public String unitFlagByCustCode(@Param("custCode") String custCode) {
            SELECT(" T.UNIT_FLAG AS unitFlag");
            FROM("PB_CUST T");
            WHERE(" T.CUST_CODE =  #{custCode}");
            return toString();
        }


        private static final String CHECK_EVFLAG_BY_OU = "checkEvFlagByOu";

        public String checkEvFlagByOu(@Param("ouCode") String ouCode) {
            SELECT(" T.CCCO AS co");
            SELECT(" T.CCE8ZZLXA AS e8zzlxa");
            SELECT(" T.CCEV01 AS ev01");
            SELECT(" T.CCEV02 AS ev02");
            SELECT(" T.CCEV03 AS ev03");
            SELECT(" T.CCEV04 AS ev04");
            FROM("VE8010ZZ T");
            WHERE(" T.CCCO =  #{ouCode}");
            return toString();
        }

        private static final String ADDR_QUALIFY = "addrQualify";

        public String addrQualify(@Param("ouCode") String ouCode) {
            SELECT(" T.id AS id");
            SELECT(" T.QUALIFY_TYPE as qualifyType");
            SELECT(" T.CERT_STATUS AS certStatus");
            SELECT(" T.VALID_TO AS validTo");
            SELECT(" T.VALID_LV1 AS validLv1");
            SELECT(" T.VALID_LV2 AS validLv2");
            SELECT(" T.VALID_LV3 AS validLv3");
            FROM("PB_ADDR_QUALIFY T");
//            WHERE(" T.QUALIFY_TYPE =  #{qualifyType}");
            WHERE(" T.ADDR_NO =  #{ouCode}");
            return toString();
        }

        private static final String ITEM_GRADE = "itemGrade";

        public String itemGrade(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode) {
            SELECT(" T.id AS id");
            SELECT(" T.GRADE  grade");
            SELECT(" T.P1  p1");
            FROM("PB_ITEM T");
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" T.ITEM_CODE = #{itemCode}");
            WHERE(" T.OU_CODE = #{ouCode}");
            return toString();
        }

    }


    /**
     * 查询客户资质（零售个人/批发销售）
     *
     * @param custCode 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.UNIT_FLAG_BY_CUSTCODE)
    String unitFlagByCustCode(@Param("custCode") String custCode);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.CHECK_EVFLAG_BY_OU)
    List<CertCheckDomain> checkEvFlagByOu(@Param("ouCode") String ouCode);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ADDR_QUALIFY)
    List<AddrQualfyDomain> addrQualify(@Param("ouCode") String ouCode);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ITEM_GRADE)
    PbItem itemGrade(@Param("itemCode") String itemCode, @Param("ouCode") String ouCode);
}
