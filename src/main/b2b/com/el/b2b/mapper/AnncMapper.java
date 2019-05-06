package com.el.b2b.mapper;

import com.el.b2b.api.AnncQuery;
import com.el.b2b.domain.Annc;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbAnncMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
public interface AnncMapper extends TPbAnncMapper {
    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.ANNC_SUBJECT  anncSubject");
            SELECT(" T.ANNC_CONTENT  anncContent");
            SELECT(" T.ANNC_TARGET  anncTarget");
            SELECT(" T.PUBLISH_TIME  publishTime");
            SELECT(" T.ANNC_STATUS  anncStatus");
        }

        private void FROM_SQL() {
            FROM("PB_ANNC T");
            JOIN("PB_OU O ON O.OU_CODE = T.OU_CODE");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
            WHERE(" (O.DELETE_FLAG <> 1 OR O.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(AnncQuery query) {
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE = #{ouCode}");
            }
            //过滤公告主题
            if (!StringUtils.isEmpty(query.getAnncSubject())) {
                WHERE(" T.ANNC_SUBJECT like " + SqlUtil.toSqlLikeString(query.getAnncSubject()));
            }
            //过滤时间
            if (!StringUtils.isEmpty(query.getPublishTime())) {
                WHERE("DATE_FORMAT(T.PUBLISH_TIME,'%Y-%m-%d %H-%i-%s') <= DATE_FORMAT('" + query.getPublishTime() + "','%Y-%m-%d %H-%i-%s')");
            }
        }

        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql(AnncQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY("  T.ID DESC");
            return toString();
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(AnncQuery query) {
            SELECT(" COUNT(1) n");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(AnncQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY(" T.ID DESC");
            return SqlUtil.paging(toString(), query);
        }

        static final String FIND_BY_ID = "findById";

        public String findById() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            WHERE(" T.ID = #{id} ");
            return toString();
        }
    }

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = AnncMapper.SqlBuilder.class, method = AnncMapper.SqlBuilder.FIND_COUNT_SQL)
    int findCount(AnncQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = AnncMapper.SqlBuilder.class, method = AnncMapper.SqlBuilder.FIND_PAGE_SQL)
    List<Annc> findPage(AnncQuery query);

    /**
     * 查询全部公告
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = AnncMapper.SqlBuilder.class, method = AnncMapper.SqlBuilder.FIND_ALL_SQL)
    List<Annc> findAll(AnncQuery query);

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    Annc findById(@Param("id") long id);
}
