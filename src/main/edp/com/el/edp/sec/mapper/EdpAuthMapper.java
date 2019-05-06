package com.el.edp.sec.mapper;

import com.el.cfg.security.EdpAuthCredentials;
import com.el.core.security.RolesBasedPermission;
import com.el.core.util.SqlUtil;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.domain.EdpMenu;
import com.el.edp.sec.domain.EdpPerm;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Simon.Hu
 * @since 18/3/12
 */
public interface EdpAuthMapper {

    class SqlBuilder extends SQL {

        private static final String MENUS_OF_SQL = "menusOfSql";

        public String menusOfSql(@Param("roles") Collection<String> roles) {

            SELECT("p.CODE menuId, p.PCODE upperId, p.NAME menuName");
            SELECT("p.MENU_URL menuUrl, p.MENU_ICO menuIcon, p.TYPE menuType");

            FROM("PS_AUTH_PERM p");
            JOIN("PS_AUTH_ROLE_PERM rp on rp.PERM_CODE = p.CODE");

            WHERE("rp.ROLE_ID in " + SqlUtil.toSqlStringSet(roles));
            WHERE("p.LAYER = #{layer}");

            ORDER_BY("p.LVL, p.SEQ");

            return toString();
        }
    }

    /**
     * @param layer 登录用户信息
     * @param roles 角色列表
     * @return 菜单一览
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.MENUS_OF_SQL)
    List<EdpMenu> menusOf(@Param("layer") EdpAuthLayer layer,
                          @Param("roles") Collection<String> roles);

    /**
     * @param layer 用户层级
     * @return 权限一览
     */
    @Select({
        "select p.CODE, p.PCODE, p.NAME, p.APIS, p.TYPE",
        "  from PS_AUTH_PERM p",
        " where p.LAYER = #{layer}",
        "   and p.TYPE in ('M', 'F')",
        " order by p.LVL, p.SEQ",
    })
    List<EdpPerm> permsOf(@Param("layer") EdpAuthLayer layer);

    /**
     * @return API访问配置一览
     */
    @Select({
        "select p.APIS paths,",
        "       GROUP_CONCAT(rp.ROLE_ID) roles",
        "  from PS_AUTH_PERM p",
        "  JOIN PS_AUTH_ROLE_PERM rp on rp.PERM_CODE = p.CODE",
        "  JOIN PS_AUTH_ROLE r on r.ID = rp.ROLE_ID",
        " where p.APIS is not null and r.DELETE_FLAG = 0 and p.TYPE in ('M', 'F')",
        " group by p.LVL, p.APIS",
        " order by p.LVL desc",
    })
    List<RolesBasedPermission> permissions();

    /**
     * 根据企业号和员工号获取用户信息
     *
     * @param empCode 员工号
     * @return 用户账户信息
     */
    @Select({
        "select u.PASSWORD, u.SALT",
        ", u.ID id, u.LAYER layer, u.FIELD field, u.ENT_TYPE entType",
        ", u.ENT_ID entId, u.ENT_CODE entCode, u.ENT_ADDR entAddr",
        ", u.EMP_ID empId, u.EMP_CODE empCode, u.EMP_ADDR empAddr",
        ", u.NAME, u.EMAIL, u.PHONE",
        "  from PS_AUTH_USER u",
        " where u.DELETE_FLAG = 0 and u.LOGIN_NAME = #{loginName}",
    })
    EdpAuthUserEntity userBy(@Param("loginName") String loginName);

    /**
     * @param id 用户ID
     * @return 用户账户凭证
     */
    @Select({
        "select u.PASSWORD hashedCredentials, u.SALT hashSalt",
        "  from PS_AUTH_USER u where u.ID = #{id}",
    })
    EdpAuthCredentials credentialsOf(@Param("id") long id);

    /**
     * @param userId 用户ID
     * @return 用户拥有的角色一览
     */
    @Select({
        "select r.ID from PS_AUTH_USER_ROLE ur",
        " inner join PS_AUTH_ROLE r on r.ID = ur.ROLE_ID",
        " where ur.USER_ID = #{userId}"
    })
    Set<String> userRolesOf(@Param("userId") long userId);

    /**
     * 修改用户密码
     *
     * @param id   ID
     * @param pwd  密码散列
     * @param salt 密码的盐
     * @return 更新行数
     */
    @Update({
        "update PS_AUTH_USER set PASSWORD = #{pwd}, SALT = #{salt} where ID = #{id}"
    })
    int updatePassword(@Param("id") long id, @Param("pwd") String pwd, @Param("salt") String salt);
}
