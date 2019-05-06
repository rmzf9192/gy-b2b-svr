package com.el.cfg.security;

import com.el.edp.sec.domain.EdpAuthUser;
import com.el.edp.sec.entity.EdpAuthUserEntity;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2018/04/27
 */
public interface EdpAuthUserResolver {

    /**
     * @param entity DB中的用户信息
     * @return 认证用的用户信息
     */
    Optional<EdpAuthUser> toAuthUser(EdpAuthUserEntity entity);

}
