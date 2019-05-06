package com.el.b2b.api;

import com.el.b2b.domain.*;
import com.el.b2b.mapper.SoDMapper;
import com.el.b2b.service.SoService;
import com.el.b2b.service.UomService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:订单表Controller
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/so/soApply")
@AllArgsConstructor
public class SoApi {

    private final SoDMapper soDMapper;
    private final SoService soService;

    private final EdpPrincipalService principalService;//当前登录人
    private final UomService uomService;


    /**
     * 按soQuery分页查询
     *
     * @param soQuery
     * @return
     */
    @GetMapping("/query")
    public ResultUtil page(SoQuery soQuery) {
        EdpUser user = principalService.user();//获取当前登陆用户
        soQuery.setOuCode(user.getEntCode());
        soQuery.setCustCode(user.getEmpCode());

        int total = soService.findCount(soQuery);
        if (total < 1) {
            return ResultUtil.success(PagingResult.of(null, 0));
        }
        List<So> soList = soService.findPage(soQuery);
        for (So so : soList) {
            List<SoD> sodList = uomService.setUomList(soDMapper.findBySoId(so.getId()));
            if(sodList!=null && sodList.size()>0){
                for(SoD sod: sodList){
                    sod.setAssignedQty(sod.getAssignedQty() / 10000);
                    sod.setUnassignedQty(sod.getUnassignedQty() / 10000);
                    String erpStatus = sod.getSodStatus();
                    if(!"C".equals(erpStatus)){
                        sod.setAssignedAmt(BigDecimal.valueOf(sod.getPrice().doubleValue() * sod.getAssignedQty()));
                        sod.setBasePrice(sod.getPrice());
                        sod.setPrice(sod.getPrice().multiply(sod.getConv()));
                    }

                }
            }
            so.setSodList(sodList);
        }
        return ResultUtil.success(PagingResult.of(soList, total));
    }


    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public So query(@PathVariable long id) {
        log.info("[SoController-query] id: {}:", id);
        So so = soService.findById(id);
        if (so == null) {
            return null;
        }
        List<SoD> items = uomService.setUomList(soDMapper.findBySoId(so.getId()));
        if(items!=null && items.size()>0){
            for(SoD t: items){
                t.setBasePrice(t.getPrice());
                t.setPrice(t.getPrice().multiply(t.getConv()));
            }
        }
        so.setSodList(items);
        return so;
    }

    @GetMapping("/findCount")
    public ResultUtil findTotalCount() {
        SoQuery query = new SoQuery();
        EdpUser user = principalService.user();//获取当前登陆用户

        query.setOuCode(user.getEntCode());//设置当前登录人所在单位
        query.setCustCode(user.getEmpCode());

        query.setDocStatus("ORDERED");
        int ordered = soService.findCount(query);

        query.setDocStatus("SUBMITTED");
        int submitted = soService.findCount(query);

        query.setShipStatus("0");//待收货
        int unReceived = soService.findUnReceivedSoCount(query);

        int all = ordered + submitted;
        SoItemCount soItemCount = new SoItemCount();
        soItemCount.setAll(all);
        soItemCount.setOrdered(ordered);
        soItemCount.setSubmitted(submitted);
        soItemCount.setUnReceived(unReceived);

        return ResultUtil.success(soItemCount);
    }


    /**
     * 保存订单信息
     *
     * @param soObject
     * @return
     */
    @PostMapping("/saveForm")
    public ResultUtil saveForm(@RequestBody SoObject soObject) {
        return soService.saveForm(soObject);
    }

    /**
     * 批量删除订单明细
     *
     * @param ids
     * @return 执行结果
     */
    @PostMapping("/delete")
    public ResultUtil delete(@RequestBody @Validated Long[] ids ) {
        boolean flag=false;
        return soService.delByIds(ids, flag);
    }
    /**
     * 数据实时同步接口
     *
     * @param ids
     * @return 执行结果
     */
 /*   @PostMapping("/sync")
    public ResultUtil sync() {
        return soService.sync();
    }*/

    /**
     * 订单导出查询
     *
     * @param query
     * @return result
     */
    @GetMapping("/exportDomain")
    public ResultUtil soExport(SoQuery query) {
        return ResultUtil.success(soService.exportDomain(query));
    }

    /**
     * 已发货订单导出查询
     * @param query
     * @return
     */
    @GetMapping("/deliveredDomain")
    public ResultUtil soDelivered(SoQuery query) {
        return ResultUtil.success(soService.deliveredDomain(query));
    }

    /**
     * 按soQuery分页查询
     *
     * @param soQuery
     * @return
     */
    @GetMapping("/soDoQuery")
    public ResultUtil soDoPage(SoQuery soQuery) {
        EdpUser user = principalService.user();//获取当前登陆用户
        soQuery.setOuCode(user.getEntCode());
        soQuery.setCustCode(user.getEmpCode());

        int total = soService.findSoDoCount(soQuery);
        if (total < 1) {
            return ResultUtil.success(PagingResult.of(null, 0));
        }
        List<SoDo> soDoList = soService.findSoDoPage(soQuery);
        for (SoDo sodo : soDoList) {
            List<SoDo> soDoDetailList = soService.findSoDoById(sodo.getId(), soQuery.getShipStatus() != null ? soQuery.getShipStatus() : null);
            for (SoDo soDoDetail : soDoDetailList) {
                soDoDetail.setSddsc1(soDoDetail.getSddsc1().trim());
                soDoDetail.setSddsc2(soDoDetail.getSddsc2().trim());
                soDoDetail.setIolot1(soDoDetail.getIolot1().trim());

               /* soDoDetail.setSduorg(soDoDetail.getSduorg() / 10000);
                soDoDetail.setSdsoqs(soDoDetail.getSdsoqs() / 10000);
                soDoDetail.setDttuprc(soDoDetail.getDttuprc().divide(new BigDecimal(10000)));
                soDoDetail.setDttaexp(soDoDetail.getDttaexp().divide(new BigDecimal(100)));*/
                soDoDetail.setSduorg(soDoDetail.getSduorg());
                soDoDetail.setSdsoqs(soDoDetail.getSdsoqs());
                soDoDetail.setDttuprc(soDoDetail.getDttuprc());
                soDoDetail.setDttaexp(soDoDetail.getDttaexp());
            }
            sodo.setSodoDetailList(soDoDetailList);
        }
        return ResultUtil.success(PagingResult.of(soDoList, total));
    }

    @PostMapping("/soReceive")
    public ResultUtil updateShipStatus(@RequestBody @Validated String[] ids) {
        return ResultUtil.isSuccess(soService.updateShipStatus(ids));
    }

    /**
     * 删除订单
     *
     * @param id
     * @return 执行结果
     */
    @GetMapping("/soDeleteById/{id}")
    public ResultUtil soDeleteById(@PathVariable long id) {
        log.info("[SoController-soDeleteById] id: {}:", id);
        return ResultUtil.isSuccess(soService.deleteOrdered(id));
    }
}
