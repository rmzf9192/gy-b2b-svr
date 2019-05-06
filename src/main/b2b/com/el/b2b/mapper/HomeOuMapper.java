package com.el.b2b.mapper;

import com.el.b2b.domain.HomeOu;
import com.el.core.jdbc.Sql;
import com.el.mbg.mapper.THomeOuMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
public interface HomeOuMapper extends THomeOuMapper {
    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.OU_NAME  ouName");
            SELECT(" T.BANK  bank");
            SELECT(" T.BANK_ACC  bankAcc");
            SELECT(" T.TAX_NO  taxNo");
            SELECT(" T.OU_ADDR  ouAddr");
            SELECT(" T.OU_ADDR2  ouAddr2");
            SELECT(" T.OU_ADDR3  ouAddr3");
            SELECT(" T.WAREHOUSE_ADDR  warehouseAddr");
            SELECT(" T.WAREHOUSE_ADDR2  warehouseAddr2");
            SELECT(" T.WAREHOUSE_ADDR3  warehouseAddr3");
            SELECT(" T.image_url  imageUrl");
            SELECT(" T.image_url2  imageUrl2");
            SELECT(" T.image_url3  imageUrl3");
            SELECT(" T.image_url4  imageUrl4");
            SELECT(" T.image_url5  imageUrl5");
            SELECT(" T.REMARK  remark");
        }

        private void FROM_SQL() {
            FROM("HOME_OU T");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
        }

//        private void FILTER_BY() {
//
//        }

        static final String FIND_BY_OUCODE_SQL = "findByOuCodeSql";

        public String findByOuCodeSql() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.OU_CODE = #{ouCode}");
            return toString();
        }
    }

    /**
     * 按ouCode查询首页-公司信息
     *
     * @param ouCode
     * @return
     */
    @SelectProvider(type = HomeOuMapper.SqlBuilder.class, method = HomeOuMapper.SqlBuilder.FIND_BY_OUCODE_SQL)
    HomeOu findByOuCode(@Param("ouCode") String ouCode);
}
