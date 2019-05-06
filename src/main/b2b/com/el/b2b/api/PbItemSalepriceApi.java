package com.el.b2b.api;

import com.el.b2b.domain.PbItemSaleprice;
import com.el.b2b.service.PbItemSalepriceService;
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
@RequestMapping("api/pbItemSaleprice")
@AllArgsConstructor
public class PbItemSalepriceApi {

    private final PbItemSalepriceService pbItemSalepriceService;
    private final EdpPrincipalService principalService;//当前登录人

    /**
     * 按pbItemSalepriceQuery分页查询
     *
     * @param pbItemSalepriceQuery
     * @return
     */
    @GetMapping("/query")
    public ResultUtil page(PbItemSalepriceQuery pbItemSalepriceQuery) {

        EdpUser user = principalService.user();//获取当前登陆用户
        String custCode = user.getEmpCode();//当前用户的code
        String entCode = user.getEntCode();//获取当前用户的公司code
        pbItemSalepriceQuery.setCustCode(custCode);
        pbItemSalepriceQuery.setOuCode(entCode);
        log.info("[PbItemSalepriceApi-page] PbItemSalepriceQuery: {}:", pbItemSalepriceQuery);
        return ResultUtil.success(pbItemSalepriceService.page(pbItemSalepriceQuery));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public PbItemSaleprice query(@PathVariable long id) {
        log.info("[PbItemSalepriceApi-query] id: {}:", id);
        return pbItemSalepriceService.findById(id);
    }
}
