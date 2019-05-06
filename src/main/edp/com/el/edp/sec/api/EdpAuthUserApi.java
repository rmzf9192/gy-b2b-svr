package com.el.edp.sec.api;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.bpm.mapper.EdpBpmRoleMapper;
import com.el.edp.mds.mapper.EdpOperationUnitMapper;
import com.el.edp.sec.domain.EdpAuthLayer;
import com.el.edp.sec.entity.EdpAuthUserEntity;
import com.el.edp.sec.mapper.EdpAuthRoleMapper;
import com.el.edp.sec.mapper.EdpAuthUserMapper;
import com.el.edp.sec.service.EdpAuthUserService;
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
 * 系统管理 - 用户管理
 *
 * @author neo.pan
 * @since 2018/03/28
 */
@Profile("edp")
@RestController
@RequestMapping("/edp/sec")
@AllArgsConstructor
public class EdpAuthUserApi {

    private final EdpPrincipalService principalService;

    private final EdpAuthUserMapper userMapper;

    private final EdpAuthUserService userService;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    private final EdpBpmRoleMapper bpmRoleMapper;

    private final EdpAuthRoleMapper authRoleMapper;

    private final EdpOperationUnitMapper operationUnitMapper;

    private EdpAuthLayer authLayer() {
        return principalService.user().getAuthLayer();
    }

    @GetMapping("/users")
    public PagingResult<EdpAuthUserEntity> usersBy(EdpAuthUserQuery query) {

        EdpUser user = principalService.user();//获取当前登陆用户
        query.setEntCode(user.getEntCode());

        val rows = userMapper.usersBy(query, authLayer());
        for (int i = 0; i < rows.size() ; i++) {
            rows.get(i).setAuthRolesName(userMapper.authRolesName(rows.get(i).getId()));
        }
        val total = userMapper.userCountBy(query, authLayer());
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }

    @GetMapping("/users/{id}")
    public EdpAuthUserEntity user(@PathVariable("id") long id) {
        return userService.userBy(id);
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody @Validated EdpAuthUserEntity entity) {
        entity.setLayer(EdpAuthLayer.E.name());
        return toResponseEntity(userService.saveUser(entity));
    }

    @PostMapping("/users/{id}/x")
    public ResponseEntity disableUser(@PathVariable("id") long id) {
        return toResponseEntity(userService.disableUser(id));
    }

    @PostMapping("/users/{id}/o")
    public ResponseEntity enableUser(@PathVariable("id") long id) {
        return toResponseEntity(userService.enableUser(id));
    }

    @GetMapping("/auth-roles")
    public List<EdpCodeName> authRoles() {
        return authRoleMapper.rolesOf(authLayer());
    }

    @GetMapping("/bpm-roles")
    public List<EdpCodeName> bpmRoles() {
        return bpmRoleMapper.rolesOf(authLayer());
    }

    @GetMapping("/ous")
    public List<EdpCodeName> operationUnits(EdpAuthUserQuery query) {
        return operationUnitMapper.operationUnits(query.getKey());
    }
}
