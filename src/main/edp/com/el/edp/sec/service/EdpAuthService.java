package com.el.edp.sec.service;

import com.el.core.web.OpResult;
import com.el.edp.util.ResultUtil;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

/**
 * 认证授权相关服务
 *
 * @author neo.pan
 * @since 2018/03/28
 */
public interface EdpAuthService {

    /**
     * 密码强度(0～10)的基准值
     */
    int PWD_STRENGTH_BASE = 5;

    enum EdpAuthOp implements OpResult {
        NG_PWD_IS_POOR("密码太弱"),
        NG_OLD_PWD_BAD("旧密码不正确"),;

        @Override
        public String getCode() {
            return name();
        }

        @Getter
        private final String desc;

        EdpAuthOp(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    @Transactional
    EdpAuthOp updatePassword(long userId, String oldPwd, String newPwd);

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     */
    @Transactional
    void updatePassword(long userId, String newPwd);

    /**
     * 重置用户密码
     *
     * @param userId 用户ID
     */
    @Transactional
    ResultUtil resetPassword(long userId);

}
