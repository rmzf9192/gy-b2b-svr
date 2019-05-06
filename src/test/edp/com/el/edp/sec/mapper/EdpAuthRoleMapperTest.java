package com.el.edp.sec.mapper;

import com.el.edp.EdpTest;
import com.el.edp.sec.api.EdpAuthRoleQuery;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.entity.EdpAuthRoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/20
 */
@Slf4j
public class EdpAuthRoleMapperTest extends EdpTest {

    @Autowired
    private EdpAuthRoleMapper authRoleMapper;

    private EdpAuthRoleQuery query = new EdpAuthRoleQuery() {{
        setRoleLike("role");
    }};

    private EdpAuthLayer authLayer = EdpAuthLayer.E;

    private EdpAuthRoleEntity entity = new EdpAuthRoleEntity() {{
        setLayer("T");
        setField("F1");
        setName("test");
    }};

    @Test
    public void roleCountBy() {
        log.info("[TEST] roleCountBy={}", authRoleMapper.roleCountBy(query, authLayer));
    }

    @Test
    public void rolesBy() {
        log.info("[TEST] rolesBy={}", authRoleMapper.rolesBy(query, authLayer));
    }

    @Test
    public void roleBy() {
        log.info("[TEST] roleBy={}", authRoleMapper.roleBy(1));
    }

    @Test
    public void nameIfExists() {
        log.info("[TEST] nameIfExists={}", authRoleMapper.nameIfExists(entity));
    }

    @Test
    public void insertRole() {
        log.info("[TEST] insertRole={}", authRoleMapper.insertRole(entity));
    }

    @Test
    public void updateRole() {
        log.info("[TEST] updateRole={}", authRoleMapper.updateRole(entity));
    }

    @Test
    public void toggleRole() {
        log.info("[TEST] toggleRole={}", authRoleMapper.toggleRole(1, "0"));
    }

    @Test
    public void rolePerms() {
        log.info("[TEST] rolePerms={}", authRoleMapper.rolePerms(1));
    }

    @Test
    public void insertRolePerm() {
        log.info("[TEST] insertRolePerm={}", authRoleMapper.insertRolePerm(1, "/test"));
    }

    @Test
    public void deleteRolePerm() {
        log.info("[TEST] deleteRolePerm={}", authRoleMapper.deleteRolePerm(1));
    }

    @Test
    public void roleInService() {
        log.info("[TEST] roleInService={}", authRoleMapper.roleInService(1));
    }

    @Test
    public void rolesOf() {
        log.info("[TEST] rolesOf={}", authRoleMapper.rolesOf(authLayer));
    }
}
