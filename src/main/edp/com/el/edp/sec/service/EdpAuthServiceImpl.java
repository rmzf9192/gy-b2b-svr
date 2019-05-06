package com.el.edp.sec.service;

import com.el.core.security.auth.AuthCredentialsHasher;
import com.el.core.security.auth.AuthUtil;
import com.el.edp.sec.mapper.EdpAuthMapper;
import com.el.edp.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author neo.pan
 * @since 2018/03/28
 */
@Profile("edp")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpAuthServiceImpl implements EdpAuthService {

    /**
     * 随机密码长度
     */
    private static final int PWD_LEN = 12;
    /**
     * 随机盐的长度
     */
    private static final int PWD_SALT_LEN = 16;

    private static final String DEFAULT_PWD = "password";

    private final EdpAuthMapper authMapper;

    private final AuthCredentialsHasher credentialsHasher;

    public EdpAuthOp updatePassword(long userId, String oldPwd, String newPwd) {
        // 密码强度检查
        if (AuthUtil.evaluatePasswordStrength(newPwd) < PWD_STRENGTH_BASE) {
            return EdpAuthOp.NG_PWD_IS_POOR;
        }
        // 验证旧密码是否正确
        val userCredentials = authMapper.credentialsOf(userId);
        val newPwdHashed = credentialsHasher.hashCredentials(oldPwd, userCredentials.getHashSalt());
        log.debug("oldPwd:{}====dbPass:{}",newPwdHashed, userCredentials.getHashedCredentials());
        if (!Objects.equals(userCredentials.getHashedCredentials(), newPwdHashed)) {
            return EdpAuthOp.NG_OLD_PWD_BAD;
        }
        updatePassword(userId, newPwd);
        return null;
    }

    @Override
    public void updatePassword(long userId, String newPwd) {
        val salt = AuthUtil.randomText(PWD_SALT_LEN);
        val hashed = credentialsHasher.hashCredentials(newPwd, salt);
        if (1 == authMapper.updatePassword(userId, hashed, salt)) {
            log.info("[EDP-AUTH] u-{}'s password is reseted.", userId);
        } else {
            log.warn("[EDP-AUTH] u-{}'s password is not reseted.", userId);
        }
    }

    @Override
    public ResultUtil resetPassword(long userId) {
        updatePassword(userId, DEFAULT_PWD);
        return ResultUtil.success();
    }
}
