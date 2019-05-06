package com.el.cfg.security;

import com.el.core.security.auth.AuthListener;
import com.el.core.security.auth.AuthToken;
import com.el.core.web.WebUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * @author neo.pan
 * @since 17/10/13
 */
@Slf4j
public class EdpAuthListener implements AuthListener {

    @Override
    public void onSuccessfulLogin(AuthToken authToken, Subject subject) {
        authTrace(authToken);
    }

    @Override
    public void onFailedLogin(AuthToken authToken, AuthenticationException e) {
        authTrace(authToken);
    }

    private void authTrace(AuthToken authToken) {
        if (log.isTraceEnabled()) {
            val request = authToken.getRequest();
            val clientIp = WebUtil.clientIp(request);
            val principalId = WebUtil.principalId(request);
            log.trace("[EDP-AUTH] User({}) checked in from {} via {}", principalId, clientIp, authToken.getPrincipal());
        }
    }

}
