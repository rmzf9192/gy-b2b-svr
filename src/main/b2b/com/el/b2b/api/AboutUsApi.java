package com.el.b2b.api;

import com.el.b2b.domain.Annc;
import com.el.b2b.domain.HomeContactInfo;
import com.el.b2b.domain.HomeOu;
import com.el.b2b.service.AnncService;
import com.el.b2b.service.AttachService;
import com.el.b2b.service.HomeContactInfoService;
import com.el.b2b.service.HomeOuService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */

@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/aboutUs")
@AllArgsConstructor
public class AboutUsApi {
    private final EdpPrincipalService principalService;//当前登录人
    private final AnncService anncService;
    private final AttachService attachService;
    private final HomeOuService homeOuService;
    private final HomeContactInfoService homeContactInfoService;


    /**
     * 查询公司信息
     *
     * @param
     * @return
     */
    @GetMapping("/ouQuery")
    public ResultUtil ouQuery() {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        HomeOu ouInfo = homeOuService.findByOuCode(user.getEntCode());
        return ResultUtil.success(ouInfo);
    }

    /**
     * 查询联络方式
     *
     * @return
     */
    @GetMapping("/contactListQuery")
    public ResultUtil contactListQuery(HomeContactInfoQuery homeContactInfoQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //联络方式List
        homeContactInfoQuery.setOuCode(user.getEntCode());
        List<HomeContactInfo> homeContactInfoList = homeContactInfoService.findAll(homeContactInfoQuery);
        return ResultUtil.success(homeContactInfoList);
    }

    /**
     * 查询通知公告
     *
     * @return
     */
    @GetMapping("/anncQuery")
    public ResultUtil anncQuery(AnncQuery anncQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //公告List
        anncQuery.setOuCode(user.getEntCode());
        anncQuery.setPublishTime(LocalDateTime.now());//首页查看时，未发布的通知不可见
        return ResultUtil.success(anncService.page(anncQuery));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/anncQuery/{id}")
    public Annc query(@PathVariable long id) {
        return anncService.findById(id);
    }

    /**
     * 查询附件
     *
     * @return
     */
    @GetMapping("/attachQuery")
    public ResultUtil attachQuery(AttachQuery attachQuery) {
        //获取当前登陆用户
        EdpUser user = principalService.user();
        //附件list
        attachQuery.setOuCode(user.getEntCode());
        return ResultUtil.success(attachService.page(attachQuery));
    }
}
