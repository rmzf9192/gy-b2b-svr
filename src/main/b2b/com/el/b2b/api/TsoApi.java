package com.el.b2b.api;

import com.el.b2b.domain.TsoCodeName;
import com.el.b2b.domain.TsoObject;
import com.el.b2b.domain.TsodDomain;
import com.el.b2b.service.TsoService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/tso")
public class TsoApi {
    @Autowired
    private TsoService tsoService;
    @Autowired
    private EdpPrincipalService principalService;//当前登录人

    /**
     * 根据条件查询订单模板信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     ***/
    @GetMapping("/page")
    public ResultUtil pageVariable(TsoQuery query) {
        EdpUser user = principalService.user();//获取当前登陆用户
        query.setOuCode(user.getEntCode());
        query.setCustCode(user.getEmpCode());
        log.debug("[TSO-PAGE] query={}", query);
        return ResultUtil.success(tsoService.findPage(query));
    }

    /**
     * 新增/修改订单模板
     *
     * @param domain
     * @return 执行结果
     */
    @PostMapping("/saveOrUpdate")
    public ResultUtil saveOrUpdate(@RequestBody @Validated TsoObject domain) {
        log.debug("[TSO-SAVEORUPDATE] domain={}", domain);
        return ResultUtil.isSuccess(tsoService.saveOrUpdate(domain));
    }

    /**
     * 根据模板id，itemId删除指定的商品
     *
     * @param domain
     * @return 执行结果
     */
    @PostMapping("/removeTsodByItemCode")
    public ResultUtil removeTsodByItemCode(@RequestBody @Validated TsodDomain domain) {
        log.debug("[TSO-REMOVETSODBYITEMID] domain={}", domain);
        return ResultUtil.success(tsoService.removeTsodByItemCode(domain));
    }

    /**
     * 根据模板id删除指定的商品
     *
     * @param ids
     * @return 执行结果
     */
    @PostMapping("/removeTsodById")
    public ResultUtil removeTsodById(@RequestBody @Validated Long[] ids) {
        return ResultUtil.success(tsoService.delTsodByIds(ids));
    }

    /**
     * UDC 获取模版信息
     *
     * @param tsoQuery
     * @return
     */
    @GetMapping("/getTsoList")
    public List<TsoCodeName> getTsoList(TsoQuery tsoQuery) {
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();
        tsoQuery.setCustCode(user.getEmpCode());
        tsoQuery.setOuCode(ouCode);
        return tsoService.getTsoList(tsoQuery);
    }
}
