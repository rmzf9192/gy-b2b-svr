package com.el.edp.sec.service;

import com.el.core.domain.YesNoFlag;
import com.el.core.security.auth.AuthCredentialsHasher;
import com.el.core.security.auth.AuthUtil;
import com.el.core.web.OpResult;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import com.el.edp.sec.mapper.EdpAuthUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neo.pan
 * @since 17/8/21
 */
@Profile("edp")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpAuthUserServiceImpl implements EdpAuthUserService {

    private final EdpAuthUserMapper userMapper;

    private final AuthCredentialsHasher credentialsHasher;

    /**
     * 随机盐的长度
     */
    private static final int PWD_SALT_LEN = 16;

    private static final String DEFAULT_PWD = "password";

    @Override
    public EdpAuthUserEntity userBy(long id) {
        val user = userMapper.userBy(id);
        user.setAuthRoles(userMapper.authRoles(id));
//        user.setBpmRoles(userMapper.bpmRoles(id));
        return user;
    }

    @Transactional
    @Override
    public OpResult saveUser(EdpAuthUserEntity entity) {
        if (userMapper.nameIfExists(entity)) {
            return EdpUserOp.NG_USER_EXISTS;
        }

        if (entity.isNew()) {
            val salt = AuthUtil.randomText(PWD_SALT_LEN);
            val hashedPwd = credentialsHasher.hashCredentials(DEFAULT_PWD, salt);
            entity.setSalt(salt);
            entity.setPassword(hashedPwd);

            userMapper.insertUser(entity);
        } else {
            userMapper.updateUser(entity);
            userMapper.deleteAuthUserRole(entity.getId());
//            userMapper.deleteBpmUserRole(entity.getId());
        }

        entity.getAuthRoles().forEach(id -> userMapper.insertAuthUserRole(entity.getId(), id));
//        entity.getBpmRoles().forEach(id -> userMapper.insertBpmUserRole(entity.getId(), id));
        return EdpUserOp.OK;
    }

    @Override
    public OpResult disableUser(long id) {
        userMapper.toggleUser(id, String.valueOf(YesNoFlag.Y.getVal()));
        return EdpUserOp.OK;
    }

    @Override
    public OpResult enableUser(long id) {
        userMapper.toggleUser(id, String.valueOf(YesNoFlag.N.getVal()));
        return EdpUserOp.OK;
    }

    @Override
    public EdpAuthUserEntity userOf(String entCode, String empCode) {
        return userMapper.userOf(entCode, empCode);
    }

    @Override
    public OpResult updateUser(EdpAuthUserEntity entity) {
        if (userMapper.nameIfExists(entity)) {
            return EdpUserOp.NG_USER_EXISTS;
        }
        userMapper.updateUser(entity);
        return EdpUserOp.OK;
    }
}
