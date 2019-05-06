package com.el.cfg.security;

import com.el.core.security.auth.AuthAccount;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * @author neo.pan
 * @since 17/7/31
 */
@Value(staticConstructor = "of")
@ToString(exclude = {"credentials"}, callSuper = true)
@EqualsAndHashCode(of = "principal", callSuper = false)
public class EdpAuthAccount extends AuthAccount {

    private final EdpAuthPrincipal principal;
    private final EdpAuthCredentials credentials;

    @Override
    public String getHashedCredentials() {
        return credentials.getHashedCredentials();
    }

    @Override
    public String getSalt() {
        return credentials.getHashSalt();
    }

}
