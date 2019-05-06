package com.el.cfg.security;

import com.el.core.security.auth.AuthPrincipal;
import com.el.edi.iam.payload.EdpUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

/**
 * @author neo.pan
 * @since 17/7/31
 */
@Value(staticConstructor = "of")
@ToString(callSuper = true)
@EqualsAndHashCode(of = "user", callSuper = false)
public class EdpAuthPrincipal extends AuthPrincipal {

    @JsonIgnore
    @Override
    public Object getId() {
        return user.getId();
    }

    /**
     * @return 用户ID
     */
    public Long userId() {
        return user.getId();
    }

    /**
     * 用户信息
     */
    private final EdpUser user;

    /**
     * 权限角色
     */
    private final Set<String> roles;

    /**
     * @param role 角色
     * @return 是否拥有？
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    EdpAuthPrincipal(String realm, EdpUser user, Set<String> roles) {
        super(realm);
        this.user = user;
        this.roles = Collections.unmodifiableSet(roles);
    }

}
