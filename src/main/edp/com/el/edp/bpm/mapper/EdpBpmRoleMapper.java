package com.el.edp.bpm.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.bpm.api.runtime.EdpBpmRoleQuery;
import com.el.edp.bpm.entity.EdpBpmRoleEntity;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.util.EdpCodeName;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author neo.pan
 * @since 17/8/17
 */
public interface EdpBpmRoleMapper {

    class SqlBuilder extends Sql {

        private static final String ROLES_BY_SQL = "rolesBySql";

        public String rolesBySql(@Param("q") EdpBpmRoleQuery query) {
            SELECT("r.ID, r.NAME, r.LAYER, r.FIELD");
            SELECT("rs.REMIND_TYPE reminderType, r.DELETE_FLAG deleteFlag");

            FROM("PS_FLOW_ROLE r");
            JOIN("PB_REMIND_SET rs on rs.ID = r.REMIND_ID");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private void filterBy(EdpBpmRoleQuery query) {
            WHERE("r.LAYER = #{layer}");

            val roleLike = query.getRoleLike();
            if (StringUtils.hasText(roleLike)) {
                WHERE("r.NAME like " + SqlUtil.toSqlLikeString(roleLike));
            }
        }

        private static final String ROLE_COUNT_BY_SQL = "roleCountBySql";

        public String roleCountBySql(@Param("q") EdpBpmRoleQuery query) {
            FROM("PS_FLOW_ROLE r");
            filterBy(query);
            return toCountSql();
        }

        private static final String NAME_IF_EXISTS_SQL = "nameIfExistsSql";

        public String nameIfExistsSql(EdpBpmRoleEntity entity) {
            SELECT("case when count(0) > 0 then 1 else 0 end");
            FROM("PS_FLOW_ROLE");
            WHERE("LAYER = #{layer}");
            WHERE("FIELD = #{field}");
            WHERE("NAME = #{name}");

            if (entity.getId() != null) {
                WHERE("ID <> #{id}");
            }

            return toString();
        }
    }

    /**
     * @param query 检索条件（分页支持）
     * @return 角色检索的总数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ROLE_COUNT_BY_SQL)
    int roleCountBy(@Param("q") EdpBpmRoleQuery query,
                    @Param("layer") EdpAuthLayer layer);

    /**
     * @param query 检索条件（分页支持）
     * @return 角色一览
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ROLES_BY_SQL)
    List<EdpBpmRoleEntity> rolesBy(@Param("q") EdpBpmRoleQuery query,
                                   @Param("layer") EdpAuthLayer layer);

    /**
     * @param id 编号
     * @return 角色
     */
    @Select({
        "select ID, NAME, FIELD, REMIND_ID reminderId",
        "  from PS_FLOW_ROLE",
        " where ID = #{id}",
    })
    EdpBpmRoleEntity roleBy(long id);

    /**
     * @param entity 角色对象
     * @return 对应角色层级、领域下是否存在指定角色名
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.NAME_IF_EXISTS_SQL)
    boolean nameIfExists(EdpBpmRoleEntity entity);

    /**
     * 新增 角色 记录
     *
     * @param entity 角色对象
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_FLOW_ROLE (",
        "ID, LAYER, FIELD,",
        "NAME, REMIND_ID",
        ") values (",
        "#{id,jdbcType=DECIMAL},#{layer,jdbcType=NVARCHAR},#{field,jdbcType=NVARCHAR},",
        "#{name,jdbcType=NVARCHAR},#{reminderId,jdbcType=DECIMAL}",
        ")"
    })
    @SelectKey(statement="select SEQ_PS_FLOW_ROLE.NEXTVAL from DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insertRole(EdpBpmRoleEntity entity);

    /**
     * 更新 角色 记录
     *
     * @param entity 角色对象
     * @return {更新记录数}
     */
    @Update({
        "update PS_FLOW_ROLE",
        "   set NAME = #{name,jdbcType=NVARCHAR},",
        "       REMIND_ID = #{reminderId,jdbcType=DECIMAL},",
        "       MODIFY_TIME = sysdate",
        " where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateRole(EdpBpmRoleEntity entity);

    /**
     * 逻辑删除 角色 记录
     *
     * @param id 角色编号
     * @return {删除记录数}
     */
    @Update({
        "update PS_FLOW_ROLE",
        "   set DELETE_FLAG = #{flag,jdbcType=DECIMAL},",
        "       MODIFY_TIME = sysdate",
        " where ID = #{id}"
    })
    int toggleRole(@Param("id") long id, @Param("flag") String flag);

    /**
     * @param roleId 角色编号
     * @return 任务集合
     */
    @Select({
        "select TASK_ID",
        "  from PS_FLOW_ROLE_TASK",
        " where ROLE_ID = #{roleId}",
        " order by TASK_ID"
    })
    List<Long> roleTasks(long roleId);

    /**
     * 新增 角色权限 记录
     *
     * @param roleId 角色编号
     * @param taskId 任务编号
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_FLOW_ROLE_TASK (ROLE_ID,TASK_ID) ",
        "values (#{role,jdbcType=DECIMAL},#{task,jdbcType=DECIMAL})"
    })
    int insertRoleTask(@Param("role") long roleId, @Param("task") long taskId);

    /**
     * 删除 角色任务 记录
     *
     * @param roleId 角色编号
     * @return {删除记录数}
     */
    @Delete({
        "delete from PS_FLOW_ROLE_TASK where ROLE_ID = #{roleId}"
    })
    int deleteRoleTask(long roleId);

    /**
     * @param roleId 角色编号
     * @return 角色是否已被用户关联？
     */
    @Select({
        "select case when count(0) > 0 then 1 else 0 end",
        "  from PS_FLOW_USER_ROLE",
        " where ROLE_ID = #{roleId}",
    })
    boolean roleInService(long roleId);

    /**
     * @return 角色列表
     */
    @Select({
        "select ID, NAME from PS_FLOW_ROLE",
        " where LAYER = #{layer} and DELETE_FLAG = 0",
        " order by FIELD, NAME"
    })
    List<EdpCodeName> rolesOf(@Param("layer") EdpAuthLayer layer);
}
