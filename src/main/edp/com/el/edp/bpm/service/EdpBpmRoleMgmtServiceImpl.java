package com.el.edp.bpm.service;

import com.el.core.domain.YesNoFlag;
import com.el.core.web.OpResult;
import com.el.edp.bpm.entity.EdpBpmRoleEntity;
import com.el.edp.bpm.mapper.EdpBpmRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neo.pan
 * @since 2018/04/26
 */
@Profile("bpm")
@Slf4j
@Service
@RequiredArgsConstructor
public class EdpBpmRoleMgmtServiceImpl implements EdpBpmRoleMgmtService {

    private final EdpBpmRoleMapper flowRoleMapper;

    @Override
    public EdpBpmRoleEntity roleBy(long id) {
        val role = flowRoleMapper.roleBy(id);
        role.setTasks(flowRoleMapper.roleTasks(id));
        return role;
    }

    @Transactional
    @Override
    public OpResult saveRole(EdpBpmRoleEntity entity) {
        if (flowRoleMapper.nameIfExists(entity)) {
            return RoleMgmtOp.NG_BPM_ROLE_EXISTS;
        }

        if (entity.isNew()) {
            flowRoleMapper.insertRole(entity);
        } else {
            flowRoleMapper.updateRole(entity);
            flowRoleMapper.deleteRoleTask(entity.getId());
        }

        entity.getTasks().forEach(task -> flowRoleMapper.insertRoleTask(entity.getId(), task));
        return RoleMgmtOp.OK;
    }

    @Transactional
    @Override
    public OpResult disableRole(long id) {
        if (flowRoleMapper.roleInService(id)) {
            return RoleMgmtOp.NG_BPM_ROLE_IN_SERVICE;
        }
        flowRoleMapper.toggleRole(id, String.valueOf(YesNoFlag.Y.getVal()));
        return RoleMgmtOp.OK;
    }

    @Transactional
    @Override
    public OpResult enableRole(long id) {
        flowRoleMapper.toggleRole(id, String.valueOf(YesNoFlag.N.getVal()));
        return RoleMgmtOp.OK;
    }

}
