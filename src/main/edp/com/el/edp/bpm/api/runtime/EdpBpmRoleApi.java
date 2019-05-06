package com.el.edp.bpm.api.runtime;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edp.bpm.entity.EdpBpmRoleEntity;
import com.el.edp.bpm.mapper.EdpBpmDefinitionMapper;
import com.el.edp.bpm.mapper.EdpBpmRoleMapper;
import com.el.edp.bpm.service.EdpBpmRoleMgmtService;
import com.el.edp.ews.mapper.EdpReminderMapper;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import com.el.edp.util.EdpCodeName;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.el.core.web.WebUtil.toResponseEntity;

/**
 * 系统管理 - 流程角色管理
 *
 * @author neo.pan
 * @since 2018/03/28
 */
@Profile("bpm")
@RestController
@RequestMapping("/edp/bpm")
@AllArgsConstructor
public class EdpBpmRoleApi {

    private final EdpPrincipalService principalService;

    private final EdpBpmRoleMapper bpmRoleMapper;

    private final EdpBpmRoleMgmtService bpmRoleMgmtService;

    private final EdpBpmDefinitionMapper bpmDefinitionMapper;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    private final EdpReminderMapper reminderMapper;

    private EdpAuthLayer authLayer() {
        return principalService.user().getAuthLayer();
    }

    @GetMapping("/roles")
    public PagingResult<EdpBpmRoleEntity> rolesBy(EdpBpmRoleQuery query) {
        val rows = bpmRoleMapper.rolesBy(query, authLayer());
        val tatal = bpmRoleMapper.roleCountBy(query, authLayer());
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), tatal);
    }

    @GetMapping("/roles/{id}")
    public EdpBpmRoleEntity role(@PathVariable("id") long id) {
        return bpmRoleMgmtService.roleBy(id);
    }

    @PostMapping("/roles")
    public ResponseEntity saveRole(@RequestBody @Validated EdpBpmRoleEntity entity) {
        entity.setLayer(EdpAuthLayer.E.name());
        return toResponseEntity(bpmRoleMgmtService.saveRole(entity));
    }

    @PostMapping("/roles/{id}/x")
    public ResponseEntity disableRole(@PathVariable("id") long id) {
        return toResponseEntity(bpmRoleMgmtService.disableRole(id));
    }

    @PostMapping("/roles/{id}/o")
    public ResponseEntity enableRole(@PathVariable("id") long id) {
        return toResponseEntity(bpmRoleMgmtService.enableRole(id));
    }

    @GetMapping("/tasks")
    public List<EdpCodeName> tasks() {
        return bpmDefinitionMapper.tasks();
    }

    @GetMapping("/reminders")
    public List<EdpCodeName> reminders() {
        return reminderMapper.reminders();
    }
}
