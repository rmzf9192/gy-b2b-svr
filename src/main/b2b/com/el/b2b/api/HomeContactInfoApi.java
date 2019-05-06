package com.el.b2b.api;

import com.el.b2b.domain.HomeContactInfo;
import com.el.b2b.service.HomeContactInfoService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */

@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/homeContactInfo")
@AllArgsConstructor
public class HomeContactInfoApi {
    private final EdpPrincipalService principalService;//当前登录人
    private final HomeContactInfoService homeContactInfoService;

    /**
     * 保存或修改联系人信息
     *
     * @param
     * @return
     */
    @PostMapping("/save")
    public ResultUtil save(@RequestBody HomeContactInfo homeContactInfo) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        homeContactInfo.setOuCode(user.getEntCode());
        return homeContactInfoService.save(homeContactInfo);
    }

    /**
     * 查询联络方式
     *
     * @return
     */
    @GetMapping("/query")
    public ResultUtil query(HomeContactInfoQuery homeContactInfoQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //联络方式List
        homeContactInfoQuery.setOuCode(user.getEntCode());
        List<HomeContactInfo> homeContactInfoList = homeContactInfoService.findAll(homeContactInfoQuery);
        return ResultUtil.success(homeContactInfoList);
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public HomeContactInfo query(@PathVariable long id) {
        return homeContactInfoService.findById(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    public ResultUtil remove(@RequestBody @Validated Long[] ids) {
        return ResultUtil.success(homeContactInfoService.removeContact(ids));
    }
}
