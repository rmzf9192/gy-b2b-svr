package com.el.b2b.mapper;

import com.el.b2b.api.AttachQuery;
import com.el.b2b.domain.Attach;
import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.util.StringUtils;
import com.el.mbg.mapper.TPbAttachMapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description: 附件Mapper
 */
public interface AttachMapper extends TPbAttachMapper {

    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.ENTRY_CLS  entryCls");
            SELECT(" T.ENTRY_ID  entryId");
            SELECT(" T.ATTACH_NAME  attachName");
            SELECT(" T.ATTACH_DATA_TYPE  attachDataType");
            SELECT(" T.ATTACH_PATH  attachPath");
            SELECT(" T.UPLOAD_TIME  uploadTime");
            SELECT(" T.REMARK  remark");
            SELECT(" T.OU_CODE  ouCode");
        }

        private void FROM_SQL() {
            FROM("PB_ATTACH T");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE(" (T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL) ");
        }

        private void FILTER_BY(AttachQuery query) {
            //过滤公司
            if (!StringUtils.isEmpty(query.getOuCode())) {
                WHERE(" T.OU_CODE = #{ouCode}");
            }
            //过滤文件名
            if (!StringUtils.isEmpty(query.getAttachName())) {
                WHERE(" T.ATTACH_NAME like " + SqlUtil.toSqlLikeString(query.getAttachName()));
            }

        }

        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql(AttachQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY("  T.ID DESC ");
            return toString();
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(AttachQuery query) {
            SELECT(" COUNT(1) n ");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(AttachQuery query) {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            FILTER_BY(query);
            ORDER_BY("  T.ID DESC ");
            return SqlUtil.paging(toString(), query);
        }
    }

    /**
     * 分页查询条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(AttachQuery query);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<Attach> findPage(AttachQuery query);

    /**
     * 查询全部附件
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_SQL)
    List<Attach> findAll(AttachQuery query);

}
