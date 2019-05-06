package com.el.cfg;

import com.el.cfg.jdbc.EdpSqlTenantInfoResolver;
import com.el.edp.sec.domain.EdpAuthUser;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import com.el.cfg.security.EdpAuthUserResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2018/04/27
 */
@Profile("b2b")
@Slf4j
@Component
@RequiredArgsConstructor
public class B2bAuthUserResolver implements EdpAuthUserResolver {

    private final EdpSqlTenantInfoResolver tenantInfoResolver;

    @Override
    public Optional<EdpAuthUser> toAuthUser(EdpAuthUserEntity entity) {
        try {
            val entType = B2bEntType.valueOf(entity.getEntType());
            val tenantId = tenantInfoResolver.tenantId();
            val authUser = EdpAuthUser.of(tenantId, entity, entType.getAuthField(), entType);
            return Optional.of(authUser);
        } catch (Throwable e) {
            log.error("[B2B-AUTH] invalid user info: {} - {}", entity, e.getMessage());
            return Optional.empty();
        }
    }

}
