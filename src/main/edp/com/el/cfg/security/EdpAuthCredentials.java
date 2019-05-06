package com.el.cfg.security;

import lombok.Value;

/**
 * @author neo.pan
 * @since 2018/03/29
 */
@Value(staticConstructor = "of")
public class EdpAuthCredentials {

    /**
     * 散列后的密码
     */
    private final String hashedCredentials;

    /**
     * 散列用的盐
     */
    private final String hashSalt;

}
