package com.el.b2b.api;

import com.el.b2b.domain.PbItem;
import com.el.b2b.service.PbItemService;
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

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品主文件Controller
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/pbItem")
@AllArgsConstructor
public class PbItemApi {

    private final PbItemService pbItemService;
    private final EdpPrincipalService principalService;//当前登录人

    /**
     * 按pbItemQuery分页查询
     *
     * @param pbItemQuery
     * @return
     */
    @GetMapping("/query")
    public ResultUtil page(PbItemQuery pbItemQuery) {

        EdpUser user = principalService.user();//获取当前登陆用户
        String entCode = user.getEntCode();//获取当前用户的公司code
        pbItemQuery.setOuCode(entCode);
        log.info("[PbItemApi-page] PbItemQuery: {}:", pbItemQuery);
        return ResultUtil.success(pbItemService.page(pbItemQuery));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public PbItem query(@PathVariable long id) {
        log.info("[PbItemApi-query] id: {}:", id);
        return pbItemService.findById(id);
    }

}
