package com.el.edp.sec.api;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.domain.EdpPerm;
import com.el.edp.sec.entity.EdpAuthRoleEntity;
import com.el.edp.sec.mapper.EdpAuthMapper;
import com.el.edp.sec.mapper.EdpAuthRoleMapper;
import com.el.edp.sec.service.EdpAuthRoleService;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.el.core.web.WebUtil.toResponseEntity;

/**
 * 系统管理 - 角色管理
 *
 * @author neo.pan
 * @since 2018/03/28
 */
@Profile("edp")
@RestController
@RequestMapping("/edp/sec")
@AllArgsConstructor
public class EdpAuthRoleApi {

    private final EdpPrincipalService principalService;

    private final EdpAuthMapper authMapper;

    private final EdpAuthRoleMapper roleMapper;

    private final EdpAuthRoleService roleService;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    private EdpAuthLayer authLayer() {
        return principalService.user().getAuthLayer();
    }

    @GetMapping("/roles")
    public PagingResult<EdpAuthRoleEntity> rolesBy(EdpAuthRoleQuery query) {
        val rows = roleMapper.rolesBy(query, authLayer());
        val total = roleMapper.roleCountBy(query, authLayer());
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }

    @GetMapping("/perms")
    public List<EdpPerm> perms() {
        return EdpPerm.toTrees(authMapper.permsOf(authLayer()));
    }

    @GetMapping("/roles/{id}")
    public EdpAuthRoleEntity role(@PathVariable("id") long id) {
        return roleService.roleBy(id);
    }

    @PostMapping("/roles")
    public ResponseEntity saveRole(@RequestBody @Validated EdpAuthRoleEntity entity) {
        entity.setLayer(EdpAuthLayer.E.name());
        return toResponseEntity(roleService.saveRole(entity));
    }

    @PostMapping("/roles/{id}/x")
    public ResponseEntity disableRole(@PathVariable("id") long id) {
        return toResponseEntity(roleService.disableRole(id));
    }

    @PostMapping("/roles/{id}/o")
    public ResponseEntity enableRole(@PathVariable("id") long id) {
        return toResponseEntity(roleService.enableRole(id));
    }
}
