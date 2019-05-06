package com.el.edp.sec.api;

import com.el.cfg.security.EdpPrincipalService;
import com.el.core.security.auth.AuthUtil;
import com.el.edp.sec.domain.EdpMenu;
import com.el.edp.sec.mapper.EdpAuthMapper;
import com.el.edp.sec.service.EdpAuthService;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.el.core.web.WebUtil.toResponseEntity;

/**
 * 认证授权相关API
 *
 * @author neo.pan
 * @since 17/8/17
 */
@Profile("edp")
@Slf4j
@RestController
@RequestMapping("/sec")
@AllArgsConstructor
public class EdpAuthApi {

    private final EdpAuthMapper authMapper;
    private final EdpAuthService authService;
    private final EdpPrincipalService principalService;

    /**
     * @return 用户菜单
     */
    @GetMapping("/menus")
    public List<EdpMenu> menus() {
        val principal = principalService.getPrincipal();
        return authMapper.menusOf(principal.getUser().getAuthLayer(), principal.getRoles());
    }

    /**
     * 获取密码强度
     *
     * @param password 密码
     * @return 强度值
     */
    @GetMapping("/pwd/strength")
    public int passwordStrength(@RequestParam("p") String password) {
        return AuthUtil.evaluatePasswordStrength(password);
    }

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    @PostMapping("/pwd/change")
    public ResponseEntity changePassword(
        @RequestParam String oldPwd, @RequestParam String newPwd) {
        val userId = principalService.userId();
        val result = authService.updatePassword(userId, oldPwd, newPwd);
        return toResponseEntity(result);
    }

    /**
     * 重置密码
     * @return
     */
    @PostMapping("/pwd/reset/{id}")
    public ResultUtil resetPassword(@PathVariable long id) {
        return authService.resetPassword(id);
    }
}
