package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.api.runtime.EdpBpmRoleQuery;
import com.el.edp.bpm.entity.EdpBpmRoleEntity;
import com.el.edp.sec.domain.EdpAuthLayer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/22
 */
@Slf4j
public class EdpBpmRoleMapperTest extends EdpTest {

    @Autowired
    private EdpBpmRoleMapper flowRoleMapper;

    private EdpBpmRoleQuery query = new EdpBpmRoleQuery() {{
        setRoleLike("role");
    }};

    private EdpAuthLayer authLayer = EdpAuthLayer.E;

    private EdpBpmRoleEntity entity = new EdpBpmRoleEntity() {{
        setLayer("T");
        setField("F1");
        setName("test");
        setReminderType("t");
    }};

    @Test
    public void roleCountBy() {
        log.info("[TEST] roleCountBy={}", flowRoleMapper.roleCountBy(query, authLayer));
    }

    @Test
    public void rolesBy() {
        log.info("[TEST] rolesBy={}", flowRoleMapper.rolesBy(query, authLayer));
    }

    @Test
    public void roleBy() {
        log.info("[TEST] roleBy={}", flowRoleMapper.roleBy(1));
    }

    @Test
    public void nameIfExists() {
        log.info("[TEST] nameIfExists={}", flowRoleMapper.nameIfExists(entity));
    }

    @Test
    public void insertRole() {
        log.info("[TEST] insertRole={}", flowRoleMapper.insertRole(entity));
    }

    @Test
    public void updateRole() {
        log.info("[TEST] updateRole={}", flowRoleMapper.updateRole(entity));
    }

    @Test
    public void toggleRole() {
        log.info("[TEST] toggleRole={}", flowRoleMapper.toggleRole(1, "0"));
    }

    @Test
    public void roleTasks() {
        log.info("[TEST] roleTasks={}", flowRoleMapper.roleTasks(1));
    }

    @Test
    public void insertRoleTask() {
        log.info("[TEST] insertRoleTask={}", flowRoleMapper.insertRoleTask(1, 1));
    }

    @Test
    public void deleteRoleTask() {
        log.info("[TEST] deleteRoleTask={}", flowRoleMapper.deleteRoleTask(1));
    }

    @Test
    public void roleInService() {
        log.info("[TEST] roleInService={}", flowRoleMapper.roleInService(1));
    }

    @Test
    public void rolesOf() {
        log.info("[TEST] rolesOf={}", flowRoleMapper.rolesOf(authLayer));
    }
}
