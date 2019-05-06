package com.el.cfg.security;

import com.el.core.security.auth.FormAuthToken;

import javax.servlet.http.HttpServletRequest;

/**
 * @author neo.pan
 * @since 2018/03/23
 */
public class EdpFormToken extends FormAuthToken {

    /**
     * 企业号
     */
    public EdpFormToken(HttpServletRequest request, String realmName) {
        super(request, realmName);
    }

    @Override
    public boolean valid() {
        return super.valid();
    }

}
