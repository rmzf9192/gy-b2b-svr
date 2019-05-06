package com.el.b2b.api;

import com.el.b2b.service.AttachService;
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
@RequestMapping("api/attach")
@AllArgsConstructor
public class AttachApi {
    private final EdpPrincipalService principalService;//当前登录人
    private final AttachService attachService;

    /**
     * 查询附件
     *
     * @return
     */
    @GetMapping("/page")
    public ResultUtil page(AttachQuery attachQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //附件list
        attachQuery.setOuCode(user.getEntCode());
        return ResultUtil.success(attachService.page(attachQuery));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    public ResultUtil remove(@RequestBody @Validated Long[] ids) {
        return ResultUtil.success(attachService.remove(ids));
    }
}
