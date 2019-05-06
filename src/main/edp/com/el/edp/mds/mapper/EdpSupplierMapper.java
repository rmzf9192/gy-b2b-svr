package com.el.edp.mds.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.EdpCodeName;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.shiro.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
public interface EdpSupplierMapper {

    class SqlBuilder extends Sql {

        private static final String SUPPLIERS_SQL = "suppliersSql";

        public String suppliersSql(String codeName) {
            SELECT("ID, SUPP_CODE code, SUPP_NAME name");
            FROM("PB_SUPP");
            WHERE("DELETE_FLAG = 0");

            if (StringUtils.hasText(codeName)) {
                WHERE("(SUPP_CODE like " + SqlUtil.toSqlLikeString(codeName)
                    + " or SUPP_NAME like " + SqlUtil.toSqlLikeString(codeName)
                    + ")");
            }
            return toString();
        }
    }

    /**
     * @param codeName 编码或名称
     * @return 供应商列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.SUPPLIERS_SQL)
    List<EdpCodeName> suppliers(String codeName);
}
