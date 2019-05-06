package com.el.edp.bpm.service;

import com.el.core.web.OpResult;
import com.el.edp.bpm.entity.EdpBpmRoleEntity;
import lombok.Getter;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
public interface EdpBpmRoleMgmtService {

    enum RoleMgmtOp implements OpResult {
        OK("成功"),
        NG_BPM_ROLE_EXISTS("角色已被定义"),
        NG_BPM_ROLE_IN_SERVICE("角色正在使用中");

        @Override
        public String getCode() {
            return name();
        }

        @Getter
        private final String desc;

        RoleMgmtOp(String desc) {
            this.desc = desc;
        }
    }

    EdpBpmRoleEntity roleBy(long id);

    OpResult saveRole(EdpBpmRoleEntity entity);

    OpResult disableRole(long id);

    OpResult enableRole(long id);
}
