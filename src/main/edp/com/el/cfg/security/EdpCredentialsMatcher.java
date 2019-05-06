package com.el.cfg.security;

import com.el.core.security.auth.AuthAccount;
import com.el.core.security.auth.AuthCredentialsHasher;
import com.el.core.security.auth.FormAuthToken;
import lombok.val;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author neo.pan
 * @since 17/10/13
 */
public class EdpCredentialsMatcher implements CredentialsMatcher, AuthCredentialsHasher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        val authToken = (FormAuthToken) token;
        val authInfo = (AuthAccount) info;
        val hashedTokenCredentials = hashCredentials(authToken.getPassword(), authInfo.getSalt());
        return authInfo.getHashedCredentials().equals(hashedTokenCredentials);
    }

    @Override
    public String hashCredentials(String credentials, String salt) {
        return new Sha256Hash(credentials, salt).toHex();
    }

}
