package com.el.b2b.api;

import com.el.b2b.domain.Annc;
import com.el.b2b.service.AnncService;
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
@RequestMapping("api/annc")
@AllArgsConstructor
public class AnncApi {
    private final EdpPrincipalService principalService;//当前登录人
    private final AnncService anncService;

    /**
     * 查询通知公告
     *
     * @return
     */
    @GetMapping("/anncQuery")
    public ResultUtil page(AnncQuery anncQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //公告List
        anncQuery.setOuCode(user.getEntCode());
        return ResultUtil.success(anncService.page(anncQuery));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/anncQuery/{id}")
    public Annc findById(@PathVariable long id) {
        return anncService.findById(id);
    }

    /**
     * 保存或修改公告信息
     *
     * @param annc
     * @return
     */
    @PostMapping("/save")
    public ResultUtil save(@RequestBody Annc annc) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        annc.setOuCode(user.getEntCode());
        return anncService.save(annc);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    public ResultUtil remove(@RequestBody @Validated Long[] ids) {
        return ResultUtil.success(anncService.remove(ids));
    }
}
