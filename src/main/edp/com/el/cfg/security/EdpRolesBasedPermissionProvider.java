package com.el.cfg.security;

import com.el.core.security.RolesBasedPermission;
import com.el.core.security.RolesBasedPermissionProvider;
import com.el.edp.sec.mapper.EdpAuthMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;

/**
 * @author neo.pan
 * @since 17/4/20
 */
@Slf4j
@AllArgsConstructor
public class EdpRolesBasedPermissionProvider implements RolesBasedPermissionProvider {

    private final EdpAuthMapper authMapper;

    @Override
    public List<RolesBasedPermission> permissions() {
        val perms = authMapper.permissions();
        log.trace("[EDP-AUTH] permissions loaded: {}", perms);
        return perms;
    }

}
