package com.el.cfg.security;

import com.el.core.security.auth.*;
import com.el.edp.sec.mapper.EdpAuthMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author neo.pan
 * @since 17/10/13
 */
@Slf4j
public class EdpFormRealm extends FormAuthRealm {

    private static final String REALM_NAME = "EDP_FORM";

    private final EdpAuthMapper authMapper;

    private final EdpAuthUserResolver authUserResolver;

    public EdpFormRealm(
        CredentialsMatcher credentialsMatcher, AuthTokenChecker tokenChecker,
        EdpAuthMapper authMapper, EdpAuthUserResolver authUserResolver) {
        super(REALM_NAME, credentialsMatcher, tokenChecker);
        this.authMapper = authMapper;
        this.authUserResolver = authUserResolver;
    }

    @Override
    public AuthToken createToken(HttpServletRequest request) {
        EdpFormToken token = new EdpFormToken(request, this.getName());
        return token.valid() ? token : null;
    }

    @Override
    protected AuthAccount getAccount(AuthToken authToken) throws AuthOpException {
        val token = (EdpFormToken) authToken;
        val loginNo = token.getLoginNo();

        log.info("[EDP-AUTH] fetch user account by {}", loginNo);
        val userEntity = authMapper.userBy(loginNo);
        if (userEntity == null) {
            log.trace("[EDP-AUTH] unknown account: {}", loginNo);
            throw new AuthOpException(AuthOp.NG_PRINCIPAL);
        }
        val user = authUserResolver.toAuthUser(userEntity)
            .orElseThrow(() -> new AuthOpException(AuthOp.NG_ACCOUNT));
        val roles = authMapper.userRolesOf(user.getId());
        if (roles.isEmpty()) {
            log.trace("[EDP-AUTH] no roles is granted to {}", loginNo);
            throw new AuthOpException(AuthOp.NG_ACCOUNT);
        }
        log.info("[EDP-AUTH] user account: {}", user);

        return EdpAuthAccount.of(new EdpAuthPrincipal(
            REALM_NAME, user, roles), user.credentials());
    }

}
