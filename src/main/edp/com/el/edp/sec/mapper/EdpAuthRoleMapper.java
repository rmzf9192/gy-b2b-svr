package com.el.edp.sec.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.sec.api.EdpAuthRoleQuery;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.entity.EdpAuthRoleEntity;
import com.el.edp.util.EdpCodeName;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.el.core.util.SqlUtil.paging;

/**
 * @author neo.pan
 * @since 17/8/17
 */
public interface EdpAuthRoleMapper {

    class SqlBuilder extends Sql {

        private static final String ROLES_BY_SQL = "rolesBySql";

        public String rolesBySql(@Param("q") EdpAuthRoleQuery query) {

            SELECT("r.ID, r.NAME, r.LAYER, r.FIELD, r.DELETE_FLAG deleteFlag");
            FROM("PS_AUTH_ROLE r");

            filterBy(query);
            ORDER_BY(query);

            return paging(toString(), query);
        }

        private void filterBy(EdpAuthRoleQuery query) {

            WHERE("r.LAYER = #{layer}");

            val roleLike = query.getRoleLike();
            if (StringUtils.hasText(roleLike)) {
                WHERE("r.NAME like " + SqlUtil.toSqlLikeString(roleLike));
            }
        }

        private static final String ROLE_COUNT_BY_SQL = "roleCountBySql";

        public String roleCountBySql(@Param("q") EdpAuthRoleQuery query) {

            SELECT("COUNT(1) n");
            FROM("PS_AUTH_ROLE r");

            filterBy(query);

            return toString();
        }

        private static final String NAME_IF_EXISTS_SQL = "nameIfExistsSql";

        public String nameIfExistsSql(EdpAuthRoleEntity entity) {
            SELECT("case when count(0) > 0 then 1 else 0 end");
            FROM("PS_AUTH_ROLE");
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
    int roleCountBy(@Param("q") EdpAuthRoleQuery query,
                    @Param("layer") EdpAuthLayer layer);

    /**
     * @param query 检索条件（分页支持）
     * @return 角色一览
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.ROLES_BY_SQL)
    List<EdpAuthRoleEntity> rolesBy(@Param("q") EdpAuthRoleQuery query,
                                    @Param("layer") EdpAuthLayer layer);

    /**
     * @param id 编号
     * @return 角色
     */
    @Select({
        "select r.ID, r.NAME, r.FIELD",
        "  from PS_AUTH_ROLE r",
        " where r.ID = #{id}",
    })
    EdpAuthRoleEntity roleBy(long id);

    /**
     * @param entity 角色对象
     * @return 对应角色层级、领域下是否存在指定角色名
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.NAME_IF_EXISTS_SQL)
    boolean nameIfExists(EdpAuthRoleEntity entity);

    /**
     * 新增 角色 记录
     *
     * @param entity 角色对象
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_AUTH_ROLE (",
//        "ID,",
        "LAYER,FIELD,",
        "NAME,DELETE_FLAG",
        ") values (",
//        "#{id,jdbcType=DECIMAL},",
        "#{layer,jdbcType=NVARCHAR},#{field,jdbcType=NVARCHAR},",
        "#{name,jdbcType=NVARCHAR},0",
        ")"
    })
//    @SelectKey(statement="select SEQ_PS_AUTH_ROLE.NEXTVAL from DUAL", keyProperty="id", before=true, resultType=Long.class)
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty="id", before=false, resultType=Long.class)
    int insertRole(EdpAuthRoleEntity entity);

    /**
     * 更新 角色 记录
     *
     * @param entity 角色对象
     * @return {更新记录数}
     */
    @Update({
        "update PS_AUTH_ROLE",
        "   set NAME = #{name,jdbcType=NVARCHAR},",
        "       MODIFY_TIME = now()",
        " where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateRole(EdpAuthRoleEntity entity);

    /**
     * 逻辑删除 角色 记录
     *
     * @param id 角色编号
     * @return {删除记录数}
     */
    @Update({
        "update PS_AUTH_ROLE",
        "   set DELETE_FLAG = #{flag,jdbcType=DECIMAL},",
        "       MODIFY_TIME = now()",
        " where ID = #{id}"
    })
    int toggleRole(@Param("id") long id, @Param("flag") String flag);

    /**
     * @param roleId 角色编号
     * @return 权限集合
     */
    @Select({
        "select PERM_CODE",
        "  from PS_AUTH_ROLE_PERM",
        " where ROLE_ID = #{roleId}",
    })
    List<String> rolePerms(long roleId);

    /**
     * 新增 角色权限 记录
     *
     * @param roleId 角色编号
     * @param permCode 权限代码
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_AUTH_ROLE_PERM (ROLE_ID,PERM_CODE) ",
        "values (#{role,jdbcType=DECIMAL},#{perm,jdbcType=NVARCHAR})"
    })
    int insertRolePerm(@Param("role") long roleId, @Param("perm") String permCode);

    /**
     * 删除 角色权限 记录
     *
     * @param roleId 角色编号
     * @return {删除记录数}
     */
    @Delete({
        "delete from PS_AUTH_ROLE_PERM where ROLE_ID = #{roleId}"
    })
    int deleteRolePerm(long roleId);

    /**
     * @param roleId 角色编号
     * @return 角色是否已被用户关联？
     */
    @Select({
        "select case when count(0) > 0 then 1 else 0 end",
        "  from PS_AUTH_USER_ROLE",
        " where ROLE_ID = #{roleId}",
    })
    boolean roleInService(long roleId);

    /**
     * @return 角色列表
     */
    @Select({
        "select ID, NAME from PS_AUTH_ROLE",
        " where LAYER = #{layer} and DELETE_FLAG = 0",
        " order by FIELD, NAME"
    })
    List<EdpCodeName> rolesOf(@Param("layer") EdpAuthLayer layer);
}
