package com.el.b2b.api;

import com.el.b2b.domain.HomeOu;
import com.el.b2b.service.HomeOuService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */

@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/homeOu")
@AllArgsConstructor
public class HomeOuApi {
    private final EdpPrincipalService principalService;//当前登录人
    private final HomeOuService homeOuService;

    /**
     * 保存或修改 公司信息
     *
     * @param
     * @return
     */
    @PostMapping("/save")
    public ResultUtil save(@RequestBody HomeOu homeOu) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        homeOu.setOuCode(user.getEntCode());
        return homeOuService.save(homeOu);
    }

    /**
     * 查询首页-公司信息
     *
     * @return
     */
    @GetMapping("/query")
    public ResultUtil query() {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        HomeOu homeOu = homeOuService.findByOuCode(user.getEntCode());
        return ResultUtil.success(homeOu);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    public ResultUtil remove(@RequestBody @Validated Long[] ids) {
        return ResultUtil.success(homeOuService.remove(ids));
    }
}
