package com.el.edp.sec.service;

import com.el.core.domain.YesNoFlag;
import com.el.core.security.SecurityFilterChainsOperator;
import com.el.core.web.OpResult;
import com.el.edp.sec.entity.EdpAuthRoleEntity;
import com.el.edp.sec.mapper.EdpAuthRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Simon.Hu
 * @since 18/3/29
 */
@Profile("edp")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpAuthRoleServiceImpl implements EdpAuthRoleService {

    private final EdpAuthRoleMapper roleMapper;

    private final SecurityFilterChainsOperator securityFilterChainsOperator;

    @Override
    public EdpAuthRoleEntity roleBy(long id) {
        val role = roleMapper.roleBy(id);
        role.setPerms(roleMapper.rolePerms(id));
        return role;
    }

    @Transactional
    @Override
    public OpResult saveRole(EdpAuthRoleEntity entity) {
        if (roleMapper.nameIfExists(entity)) {
            return EdpAuthRoleOp.NG_AUTH_ROLE_EXISTS;
        }

        if (entity.isNew()) {
            roleMapper.insertRole(entity);
        } else {
            roleMapper.updateRole(entity);
            roleMapper.deleteRolePerm(entity.getId());
        }

        entity.getPerms().forEach(perm -> roleMapper.insertRolePerm(entity.getId(), perm));
        securityFilterChainsOperator.refreshFilterChains();
        return EdpAuthRoleOp.OK;
    }

    @Transactional
    @Override
    public OpResult disableRole(long id) {
        if (roleMapper.roleInService(id)) {
            return EdpAuthRoleOp.NG_AUTH_ROLE_IN_SERVICE;
        }
        roleMapper.toggleRole(id, String.valueOf(YesNoFlag.Y.getVal()));
        securityFilterChainsOperator.refreshFilterChains();
        return EdpAuthRoleOp.OK;
    }

    @Transactional
    @Override
    public OpResult enableRole(long id) {
        roleMapper.toggleRole(id, String.valueOf(YesNoFlag.N.getVal()));
        securityFilterChainsOperator.refreshFilterChains();
        return EdpAuthRoleOp.OK;
    }
}
