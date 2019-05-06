package com.el.cfg;

import com.el.cfg.security.*;
import com.el.core.security.SecurityProperties;
import com.el.core.security.auth.AuthPrincipalService;
import com.el.core.security.auth.AuthTokenChecker;
import com.el.core.security.auth.FormCaptchaChecker;
import com.el.edp.sec.mapper.EdpAuthMapper;
import com.el.cfg.security.EdpAuthUserResolver;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/4/20
 */
@Profile("edp")
@Slf4j
@Configuration
public class EdpSecConfig {

    @Bean
    public EdpCredentialsMatcher credentialsMatcher() {
        return new EdpCredentialsMatcher();
    }

    @Bean
    public EdpFormRealm formRealm(CredentialsMatcher credentialsMatcher, EdpAuthMapper authMapper,
                                  SecurityProperties securityProperties, EdpAuthUserResolver authUserResolver) {
        log.info("[EDP-AUTH] formRealm");
        val tokenChecker = securityProperties.captchaEnabled()
            ? new FormCaptchaChecker() : AuthTokenChecker.NO_CHECKER;
        return new EdpFormRealm(credentialsMatcher, (AuthTokenChecker) tokenChecker, authMapper, authUserResolver);
    }

    @Bean
    public EdpAuthListener authListener() {
        log.info("[EDP-AUTH] authListener");
        return new EdpAuthListener();
    }

    @Bean
    public EdpPrincipalService principalService(AuthPrincipalService principalService) {
        log.info("[EDP-AUTH] principalService");
        return () -> (EdpAuthPrincipal) principalService.getPrincipal();
    }

    @Bean
    public EdpRolesBasedPermissionProvider rolesBasedPermissionProvider(EdpAuthMapper authMapper) {
        log.info("[EDP-AUTH] rolesBasedPermissionProvider");
        return new EdpRolesBasedPermissionProvider(authMapper);
    }

}
