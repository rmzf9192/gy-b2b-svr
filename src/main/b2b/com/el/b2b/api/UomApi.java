package com.el.b2b.api;

import com.el.b2b.domain.PbItemSaleprice;
import com.el.b2b.domain.TsoDomain;
import com.el.b2b.service.UomService;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: arthur.xu
 * @Description:单位转化关系
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/uom")
@AllArgsConstructor
public class UomApi {

    private final UomService uomService;
    /**
     * 按pbItemQuery分页查询
     *
     * @param pbItemUomQuery
     * @return
     */
    @GetMapping("/query")
    public ResultUtil page(PbItemUomQuery pbItemUomQuery) {
        log.info("[UomApi-page] PbItemUomQuery: {}:", pbItemUomQuery);
        return ResultUtil.success(uomService.page(pbItemUomQuery));
    }

    @PostMapping("/queryByItemOuterCode")
    public ResultUtil queryByItemOuterCode(@RequestBody List<PbItemSaleprice> addedItemList) {
        List<PbItemSaleprice> result = uomService.queryByItemOuterCode(addedItemList);
        if(result!=null && result.size()>0){
            return ResultUtil.success(result);
        }else{
            return ResultUtil.fail();
        }

    }

    @PostMapping("/queryByItemCode")
    public ResultUtil queryByItemCode(@RequestBody List<TsoDomain> addedItemList) {
        List<TsoDomain> result = uomService.queryByItemCode(addedItemList);
        if(result!=null && result.size()>0){
            return ResultUtil.success(result);
        }else{
            return ResultUtil.fail();
        }
    }
}
