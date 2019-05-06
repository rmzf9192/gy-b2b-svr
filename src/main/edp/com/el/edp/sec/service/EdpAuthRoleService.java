package com.el.edp.sec.service;

import com.el.core.web.OpResult;
import com.el.edp.sec.entity.EdpAuthRoleEntity;
import lombok.Getter;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
public interface EdpAuthRoleService {

    enum EdpAuthRoleOp implements OpResult {
        OK("成功"),
        NG_AUTH_ROLE_EXISTS("角色已被定义"),
        NG_AUTH_ROLE_IN_SERVICE("角色正在使用中");

        @Override
        public String getCode() {
            return name();
        }

        @Getter
        private final String desc;

        EdpAuthRoleOp(String desc) {
            this.desc = desc;
        }
    }

    EdpAuthRoleEntity roleBy(long id);

    OpResult saveRole(EdpAuthRoleEntity entity);

    OpResult disableRole(long id);

    OpResult enableRole(long id);
}
