package com.el.b2b.mapper;

import com.el.b2b.api.HomeContactInfoQuery;
import com.el.b2b.domain.HomeContactInfo;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.THomeContactInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
public interface HomeContactInfoMapper extends THomeContactInfoMapper {
    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.TITLE  title");
            SELECT(" T.CONTACT  contact");
            SELECT(" T.TEL  tel");
            SELECT(" T.PHONE  phone");
            SELECT(" T.EMAIL  email");
            SELECT(" T.REMARK  remark");
        }

        private void FROM_SQL() {
            FROM("HOME_CONTACT_INFO T");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(HomeContactInfoQuery query) {
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE = #{ouCode}");
            }
            //过滤标题
            if (!StringUtils.isEmpty(query.getTitle())) {
                WHERE(" T.TITLE like " + SqlUtil.toSqlLikeString(query.getTitle()));
            }
            //过滤姓名
            if (!StringUtils.isEmpty(query.getContact())) {
                WHERE(" T.CONTACT like " + SqlUtil.toSqlLikeString(query.getContact()));
            }

        }

        static final String FIND_BY_ID = "findById";

        public String findById() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ID = #{id}");
            return toString();
        }

        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql(HomeContactInfoQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY(" T.ID DESC");
            return toString();
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(HomeContactInfoQuery query) {
            SELECT("  COUNT(1) n ");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(HomeContactInfoQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY("  T.ID DESC");
            return SqlUtil.paging(toString(), query);
        }
    }

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = HomeContactInfoMapper.SqlBuilder.class, method = HomeContactInfoMapper.SqlBuilder.FIND_COUNT_SQL)
    int findCount(HomeContactInfoQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = HomeContactInfoMapper.SqlBuilder.class, method = HomeContactInfoMapper.SqlBuilder.FIND_PAGE_SQL)
    List<HomeContactInfo> findPage(HomeContactInfoQuery query);

    /**
     * 查询全部公告
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = HomeContactInfoMapper.SqlBuilder.class, method = HomeContactInfoMapper.SqlBuilder.FIND_ALL_SQL)
    List<HomeContactInfo> findAll(HomeContactInfoQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    HomeContactInfo findById(@Param("id") long id);
}
