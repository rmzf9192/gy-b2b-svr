package com.el.cfg.security;

import com.el.edi.iam.payload.EdpUser;
import lombok.val;

import java.util.Set;

/**
 * 用户上下文信息提供者
 *
 * @author neo.pan
 * @since 17/4/30
 */
public interface EdpPrincipalService {

    /**
     * @return 当前用户上下文
     */
    EdpAuthPrincipal getPrincipal();

    /**
     * @return 当前用户
     */
    default EdpUser user() {
        val principal = getPrincipal();
        return principal == null ? null : principal.getUser();
    }

    /**
     * @return 当前用户
     */
    default Set<String> roles() {
        val principal = getPrincipal();
        return principal == null ? null : principal.getRoles();
    }

    /**
     * @return 当前用户ID(游客为0)
     */
    default long userId() {
        val principal = getPrincipal();
        return principal == null ? 0L : principal.userId();
    }

}
