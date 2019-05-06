package com.el.edp.bpm.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.bpm.api.runtime.EdpBpmDefinitionQuery;
import com.el.edp.bpm.domain.runtime.EdpBpmDepDefinition;
import com.el.edp.util.EdpCodeName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 17/07/25
 */
public interface EdpBpmDefinitionMapper {

    class SqlBuilder extends Sql {

        private void filterBy(EdpBpmDefinitionQuery query) {
            FROM("ACT_RE_PROCDEF rp");
            INNER_JOIN("ACT_RE_DEPLOYMENT rd on rd.ID_ = rp.DEPLOYMENT_ID_");

            if (!StringUtils.isEmpty(query.getDepName())) {
                WHERE("rd.NAME_ like " + SqlUtil.toSqlLikeString(query.getDepName()));
            }
            if (!StringUtils.isEmpty(query.getSrcName())) {
                WHERE("rp.RESOURCE_NAME_ like " + SqlUtil.toSqlLikeString(query.getSrcName()));
            }
            if (!StringUtils.isEmpty(query.getDefName())) {
                WHERE("rp.NAME_ like " + SqlUtil.toSqlLikeString(query.getDefName()));
            }
        }

        private static final String DEFINITIONS_BY_SQL = "definitionsBySql";

        public String definitionsBySql(@Param("q") EdpBpmDefinitionQuery query) {
            SELECT("rd.ID_ depId");
            SELECT("rd.NAME_ depName");
            SELECT("rd.DEPLOY_TIME_ depTime");
            SELECT("rd.SOURCE_ depSrc");
            SELECT("rp.RESOURCE_NAME_ srcName");
            SELECT("rp.ID_ defId");
            SELECT("rp.NAME_ defName");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String DEFINITION_COUNT_BY_SQL = "definitionCountBySql";

        public String definitionCountBySql(@Param("q") EdpBpmDefinitionQuery query) {
            filterBy(query);
            return toCountSql();
        }
    }

    /**
     * 查询 流程定义 列表
     *
     * @param query 查询条件
     * @return {记录列表}
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.DEFINITIONS_BY_SQL)
    List<EdpBpmDepDefinition> definitionsBy(@Param("q") EdpBpmDefinitionQuery query);

    /**
     * 查询 流程定义 总记录条数
     *
     * @param query 查询条件
     * @return {总记录数}
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.DEFINITION_COUNT_BY_SQL)
    int definitionCountBy(@Param("q") EdpBpmDefinitionQuery query);

    /**
     * @return 任务定义集合
     */
    @Select({
        "select t.ID, t.KEY code, (d.NAME || ': ' || t.NAME) name ",
        "  from PS_FLOW_TASK t",
        " inner join PS_FLOW_DEF d on d.ID = t.DEF_ID",
        " where t.SETTLED = 'Y'",
        " order by d.ID, t.ID"
    })
    List<EdpCodeName> tasks();
}
