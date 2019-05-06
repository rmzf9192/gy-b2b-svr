package com.el.edp.bpm.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.bpm.api.runtime.EdpBpmInstanceQuery;
import com.el.edp.bpm.domain.runtime.EdpBpmInstance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 17/07/25
 */
public interface EdpBpmInstanceMapper {

    class SqlBuilder extends Sql {

        private void filterBy(EdpBpmInstanceQuery query) {
            FROM("ACT_HI_PROCINST hp");
            JOIN("(select KEY_, NAME_ from ACT_RE_PROCDEF group by KEY_, NAME_) rp on rp.KEY_ = hp.PROC_DEF_KEY_");

            if (StringUtils.hasText(query.getState())) {
                WHERE("hp.STATE_ = #{q.state}");
            }
            if (StringUtils.hasText(query.getDefId())) {
                WHERE("hp.PROC_DEF_ID_ like " + SqlUtil.toSqlLikeString(query.getDefId()));
            }
            if (StringUtils.hasText(query.getDefName())) {
                WHERE("rp.NAME_ like " + SqlUtil.toSqlLikeString(query.getDefName()));
            }
        }

        private static final String INSTANCES_BY_SQL = "instancesBySql";

        public String instancesBySql(@Param("q") EdpBpmInstanceQuery query) {
            SELECT("rp.NAME_ defName");
            SELECT("hp.PROC_DEF_ID_ defId");
            SELECT("hp.PROC_INST_ID_ prcId");
            SELECT("hp.START_TIME_ startTime");
            SELECT("hp.END_TIME_ endTime");
            SELECT("case when hp.STATE_ = 'ACTIVE' then '运行中' " +
                "when hp.STATE_ = 'COMPLETED' then '已结束' " +
                "when hp.STATE_ = 'INTERNALLY_TERMINATED' then '被终止' end stateName");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String INSTANCE_COUNT_BY_SQL = "instanceCountBySql";

        public String instanceCountBySql(@Param("q") EdpBpmInstanceQuery query) {
            filterBy(query);
            return toCountSql();
        }
    }

    /**
     * 查询 流程实例 列表
     *
     * @param query 查询条件
     * @return {记录列表}
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.INSTANCES_BY_SQL)
    List<EdpBpmInstance> instancesBy(@Param("q") EdpBpmInstanceQuery query);

    /**
     * 查询 流程实例 总记录条数
     *
     * @param query 查询条件
     * @return {总记录数}
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.INSTANCE_COUNT_BY_SQL)
    int instanceCountBy(@Param("q") EdpBpmInstanceQuery query);
}
