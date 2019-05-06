package com.el.edp.sec.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.sec.api.EdpAuthUserQuery;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author neo.pan
 * @since 17/8/17
 */
public interface EdpAuthUserMapper {

    class SqlBuilder extends Sql {

        private static final String USER_COUNT_BY_SQL = "userCountBySql";

        public String userCountBySql(@Param("q") EdpAuthUserQuery query) {
            FROM("PS_AUTH_USER u");
            filterBy(query);
            return toCountSql();
        }

        private static final String USERS_BY_SQL = "usersBySql";

        public String usersBySql(@Param("q") EdpAuthUserQuery query) {

            FROM("PS_AUTH_USER u");

            SELECT("u.ID id, u.LAYER layer, u.FIELD field, u.TENANT_ID tenantId");
            SELECT("u.EMP_ID empId, u.EMP_CODE empCode, u.EMP_ADDR empAddr");
            SELECT("u.ENT_ID entId, u.ENT_CODE entCode, u.ENT_ADDR entAddr");
            SELECT("u.NAME, u.EMAIL, u.PHONE");
            SELECT("u.DELETE_FLAG deleteFlag, u.LOGIN_NAME loginName");

            filterBy(query);

            ORDER_BY(query);

            return SqlUtil.paging(toString(), query);
        }

        private void filterBy(EdpAuthUserQuery query) {

            WHERE("u.LAYER = #{layer}");

//            val userLike = query.getUserLike();
//            if (StringUtils.hasText(userLike)) {
//                val userLikeSql = SqlUtil.toSqlLikeString(userLike);
//                WHERE("(u.NAME like " + userLikeSql
//                    + " or u.EMP_CODE like " + userLikeSql
//                    + " or u.PHONE like " + userLikeSql
//                    + " or u.EMAIL like " + userLikeSql
//                    + ")"
//                );
//            }
            //公司过滤
            if (!StringUtils.isEmpty(query.getEntCode())) {
                WHERE(" u.ENT_CODE like " +  SqlUtil.toSqlLikeString(query.getEntCode()));
            }
            if (!StringUtils.isEmpty(query.getLoginName())) {
                WHERE(" u.LOGIN_NAME like " + SqlUtil.toSqlLikeString(query.getLoginName()));
            }
            if (!StringUtils.isEmpty(query.getName())) {
                WHERE(" u.NAME like " + SqlUtil.toSqlLikeString(query.getName()));
            }
            if (!StringUtils.isEmpty(query.getEmpCode())) {
                WHERE(" u.EMP_CODE like " + SqlUtil.toSqlLikeString(query.getEmpCode()));
            }
        }

        private static final String NAME_IF_EXISTS_SQL = "nameIfExistsSql";

        public String nameIfExistsSql(EdpAuthUserEntity entity) {
            SELECT("case when count(0) > 0 then 1 else 0 end");
            FROM("PS_AUTH_USER");
//            WHERE("LAYER = #{layer}");
//            WHERE("FIELD = #{field}");
//            WHERE("ENT_CODE = #{entCode}");
//            WHERE("EMP_CODE = #{empCode}");
            WHERE("LOGIN_NAME = #{loginName}");

            if (entity.getId() != null) {
                WHERE("ID <> #{id}");
            }

            return toString();
        }
    }

    /**
     * @param query 检索条件（分页支持）
     * @return 用户检索的总数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.USER_COUNT_BY_SQL)
    int userCountBy(@Param("q") EdpAuthUserQuery query,
                    @Param("layer") EdpAuthLayer layer);

    /**
     * @param query 检索条件（分页支持）
     * @return 用户一览
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.USERS_BY_SQL)
    List<EdpAuthUserEntity> usersBy(@Param("q") EdpAuthUserQuery query,
                                    @Param("layer") EdpAuthLayer layer);

    /**
     * @param id 编号
     * @return 用户
     */
    @Select({
        "select u.ID, u.LAYER layer, u.FIELD field,",
        "       u.EMP_ID empId, u.EMP_CODE empCode, u.EMP_ADDR empAddr,",
        "       u.ENT_ID entId, u.ENT_CODE entCode, u.ENT_ADDR entAddr,",
        "       u.NAME, u.EMAIL, u.PHONE,",
        "       u.DELETE_FLAG deleteFlag,u.login_name loginName",
        "  from PS_AUTH_USER u",
        " where u.ID = #{id}",
    })
    EdpAuthUserEntity userBy(long id);

    /**
     * @param entity 用户对象
     * @return 对应用户层级、领域下是否存在指定用户名
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.NAME_IF_EXISTS_SQL)
    boolean nameIfExists(EdpAuthUserEntity entity);

    /**
     * 新增 用户 记录
     *
     * @param entity 用户对象
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_AUTH_USER (",
//        "ID,",
        "LAYER,FIELD,ENT_TYPE,",
        "EMP_ID,EMP_CODE,EMP_ADDR,",
        "ENT_ID,ENT_CODE,ENT_ADDR,",
        "NAME,EMAIL,PHONE,",
        "PASSWORD,SALT,",
        "DELETE_FLAG,LOGIN_NAME",
        ") values (",
//        "#{id,jdbcType=DECIMAL},",
        "#{layer,jdbcType=NVARCHAR},#{field,jdbcType=NVARCHAR},",
        "'oper', ", //"#{entType,jdbcType=NVARCHAR},",
        "#{empId,jdbcType=DECIMAL},#{empCode,jdbcType=NVARCHAR},#{empAddr,jdbcType=DECIMAL},",
        "#{entId,jdbcType=DECIMAL},#{entCode,jdbcType=NVARCHAR},#{entAddr,jdbcType=DECIMAL},",
        "#{name,jdbcType=NVARCHAR},#{email,jdbcType=NVARCHAR},#{phone,jdbcType=NVARCHAR},",
        "#{password,jdbcType=NVARCHAR},#{salt,jdbcType=NVARCHAR},0,#{loginName,jdbcType=NVARCHAR}",
        ")"
    })
//    @SelectKey(statement = "select SEQ_PS_AUTH_USER.NEXTVAL from DUAL", keyProperty = "id", before = true, resultType = Long.class)
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty = "id", before = false, resultType = Long.class)
    int insertUser(EdpAuthUserEntity entity);

    /**
     * 更新 用户 记录
     *
     * @param entity 用户对象
     * @return {更新记录数}
     */
    @Update({
        "update PS_AUTH_USER",
        "   set ENT_CODE = #{entCode,jdbcType=NVARCHAR},",
        "       ENT_ADDR = #{entAddr,jdbcType=NVARCHAR},",
        "       EMP_CODE = #{empCode,jdbcType=NVARCHAR},",
        "       EMP_ADDR = #{empAddr,jdbcType=NVARCHAR},",
        "       NAME = #{name,jdbcType=NVARCHAR},",
        "       EMAIL = #{email,jdbcType=NVARCHAR},",
        "       PHONE = #{phone,jdbcType=NVARCHAR}",
        " where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateUser(EdpAuthUserEntity entity);

    /**
     * 逻辑删除 用户 记录
     *
     * @param id 用户编号
     * @return {删除记录数}
     */
    @Update({
        "update PS_AUTH_USER",
        "set DELETE_FLAG = #{flag,jdbcType=DECIMAL}",
        "where ID = #{id}"
    })
    int toggleUser(@Param("id") long id, @Param("flag") String flag);

    /**
     * @param userId 用户编号
     * @return 授权角色集合
     */
    @Select({
        "select ROLE_ID from PS_AUTH_USER_ROLE where USER_ID = #{user}"
    })
    List<Long> authRoles(@Param("user") long userId);

    @Select({
        "SELECT GROUP_CONCAT(R.NAME) FROM PS_AUTH_USER_ROLE T JOIN PS_AUTH_ROLE R ON R.ID = T.ROLE_ID WHERE T.USER_ID = #{user}"
    })
    String authRolesName(@Param("user") long userId);

    /**
     * 新增 用户授权角色 记录
     *
     * @param userId 用户编号
     * @param roleId 授权角色编号
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_AUTH_USER_ROLE (USER_ID, ROLE_ID) ",
        "values (#{user,jdbcType=DECIMAL},#{role,jdbcType=DECIMAL})"
    })
    int insertAuthUserRole(@Param("user") long userId, @Param("role") long roleId);

    /**
     * 删除 用户授权角色 记录
     *
     * @param userId 用户编号
     * @return {删除记录数}
     */
    @Delete({
        "delete from PS_AUTH_USER_ROLE where USER_ID = #{userId}"
    })
    int deleteAuthUserRole(long userId);

    /**
     * @param userId 用户编号
     * @return 流程角色集合
     */
    @Select({
        "select ROLE_ID from PS_FLOW_USER_ROLE where USER_ID = #{user}"
    })
    List<Long> bpmRoles(@Param("user") long userId);

    /**
     * 新增 用户流程角色 记录
     *
     * @param userId 用户编号
     * @param roleId 流程角色编号
     * @return {新增记录数}
     */
    @Insert({
        "insert into PS_FLOW_USER_ROLE (USER_ID, ROLE_ID) ",
        "values (#{user,jdbcType=DECIMAL},#{role,jdbcType=DECIMAL})"
    })
    int insertBpmUserRole(@Param("user") long userId, @Param("role") long roleId);

    /**
     * 删除 用户流程角色 记录
     *
     * @param userId 用户编号
     * @return {删除记录数}
     */
    @Delete({
        "delete from PS_FLOW_USER_ROLE where USER_ID = #{userId}"
    })
    int deleteBpmUserRole(long userId);

    /**
     * 根据企业号和员工号获取用户信息
     *
     * @param entCode 企业号
     * @param empCode 员工号
     * @return 用户账户信息
     */
    @Select({
        "select u.ID, u.LAYER, u.FIELD, u.PASSWORD",
        ", u.EMP_ID empId, u.EMP_CODE empCode, u.EMP_ADDR empAddr",
        ", u.ENT_ID entId, u.ENT_CODE entCode, u.ENT_ADDR entAddr",
        ", u.NAME, u.EMAIL, u.PHONE",
        "  from PS_AUTH_USER u",
        " where u.ENT_CODE = #{entCode} and u.EMP_CODE = #{empCode}",
    })
    EdpAuthUserEntity userOf(@Param("entCode") String entCode, @Param("empCode") String empCode);
}
