package com.el.edp.sec.service;

import com.el.core.web.OpResult;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import lombok.Getter;

/**
 * @author neo.pan
 * @since 17/8/21
 */
public interface EdpAuthUserService {

    enum EdpUserOp implements OpResult {
        OK("成功"),
        NG_USER_EXISTS("用户已添加");

        @Override
        public String getCode() {
            return name();
        }

        @Getter
        private final String desc;

        EdpUserOp(String desc) {
            this.desc = desc;
        }
    }

    EdpAuthUserEntity userBy(long id);

    OpResult saveUser(EdpAuthUserEntity entity);

    OpResult disableUser(long id);

    OpResult enableUser(long id);

    EdpAuthUserEntity userOf(String entCode, String empCode);

    OpResult updateUser(EdpAuthUserEntity entity);
}
