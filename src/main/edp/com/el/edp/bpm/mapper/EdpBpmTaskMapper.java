package com.el.edp.bpm.mapper;

import com.el.core.jdbc.Sql;
import com.el.edp.bpm.domain.EdpBpmTaskDef;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/23
 */
public interface EdpBpmTaskMapper {

    class SqlBuilder extends Sql {

        private static final String CHECK_BPM_ROLE_SETTLE_CORRECTLY_SQL = "checkBpmRoleSettleCorrectlySql";

        public String checkBpmRoleSettleCorrectlySql() {
            SELECT("case when count(1) > 0 then 0 else 1 end");
            FROM("PS_FLOW_TASK t");
            INNER_JOIN("PS_FLOW_DEF d on d.ID = t.DEF_ID");
            JOIN("PS_FLOW_ROLE_TASK rt on rt.TASK_ID = t.ID");
            JOIN("PS_FLOW_USER_ROLE ur on ur.ROLE_ID = rt.ROLE_ID");
            JOIN("PS_AUTH_USER u on u.ID = ur.USER_ID");
            JOIN("PS_FLOW_ROLE r on r.ID = ur.ROLE_ID");
            WHERE("(u.ID is null or r.ID is null)");
            WHERE("u.DELETE_FLAG = 0");
            WHERE("r.DELETE_FLAG = 0");
            WHERE("t.SETTLED = 'Y'");
            WHERE("d.KEY = #{defKey}");
            return toString();
        }

        private static final String TASK_ROLE_MAPPING_SQL = "taskRoleMappingSql";

        public String taskRoleMappingSql() {
            SELECT("t.KEY key");
            SELECT("listagg(u.ID, ',') within group(order by u.ID) candidates");
            FROM("PS_FLOW_TASK t");
            INNER_JOIN("PS_FLOW_DEF d on d.ID = t.DEF_ID");
            JOIN("PS_FLOW_ROLE_TASK rt on rt.TASK_ID = t.ID");
            JOIN("PS_FLOW_USER_ROLE ur on ur.ROLE_ID = rt.ROLE_ID");
            JOIN("PS_AUTH_USER u on u.ID = ur.USER_ID and u.DELETE_FLAG = 0");
            WHERE("t.SETTLED = 'Y'");
            WHERE("d.KEY = #{defKey}");
            GROUP_BY("t.KEY");
            return toString();
        }
    }

    /**
     * 验证流程角色
     * @param defKey 流程定义
     * @return 是否有效
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.CHECK_BPM_ROLE_SETTLE_CORRECTLY_SQL)
    boolean checkBpmRoleSettleCorrectly(String defKey);

    /**
     * @param defKey 流程定义
     * @return 任务定义列表
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.TASK_ROLE_MAPPING_SQL)
    List<EdpBpmTaskDef> taskRoleMapping(String defKey);
}
