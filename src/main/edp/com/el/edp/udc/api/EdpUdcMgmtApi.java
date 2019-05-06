package com.el.edp.udc.api;

import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcItem;
import com.el.core.udc.UdcOpService;
import com.el.edp.udc.domain.*;
import com.el.edp.udc.mapper.EdpUdcMapper;
import com.el.edp.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Simon.Hu
 * @since 18/3/16
 */
@Profile("edp")
@Slf4j
@RestController
@RequestMapping("/edp/mgmt")
@RequiredArgsConstructor
public class EdpUdcMgmtApi {

    private final EdpUdcMapper udcMapper;
    private final UdcOpService<EdpUdc, EdpUdcItem> udcOpService;

    /**
     * @param query 搜索条件
     * @return UDC列表
     */
    @GetMapping("/udcs")
    public PagingResult<EdpUdc> udcs(EdpUdcMgmtQuery query) {
        return PagingResult.of(udcMapper.udcsOf(query), udcMapper.udcCountOf(query));
    }

    /**
     * 保存 or 修改UDC
     * @param edpUdcDomain
     * @return
     */
    @Transactional
    @PostMapping("/udcs/update")
    public ResultUtil saveOrUpdateUdcType(@RequestBody @Validated EdpUdcDomain edpUdcDomain) {
        log.debug("[UDCType-saveOrUpdate] edpUdcDomain={}", edpUdcDomain);
        if(edpUdcDomain.getId() != null){
            edpUdcDomain.setSysFlag(false);
            udcMapper.updateByPrimaryKey(edpUdcDomain);
        }else{
            edpUdcDomain.setSysFlag(false);
            udcMapper.insert(edpUdcDomain);
        }
        for (UdcItemDomain udcItemDomain : edpUdcDomain.getItems()){
            udcItemDomain.setUdcTypeId(edpUdcDomain.getId());
            udcItemDomain.setDomainCode(edpUdcDomain.getDomainCode());
            udcItemDomain.setUdcCode(edpUdcDomain.getUdcCode());
            if (udcItemDomain.getId() > 0) {
                udcMapper.updateItemByPrimaryKey(udcItemDomain);
            }else{
                udcMapper.insertItem(udcItemDomain);
            }
        }
        return ResultUtil.success();
    }

    /**
     * @param id UDC主键
     * @return UDC详情
     */
    @GetMapping("/udcs/{id}")
    public EdpUdc udc(@PathVariable("id") long id) {
        return udcMapper.udcOf(id);
    }

    /**
     * @param id UDC主键
     * @return UDC明细
     */
    @GetMapping("/udcs/{id}/items")
    public List<EdpUdcItem> udcItems(@PathVariable("id") long id) {
        return Optional.ofNullable(udcMapper.udcOf(id))
            .map(udcMapper::udcItemsOf)
            .orElse(Collections.emptyList());
    }

    /**
     * 清空UDC缓存
     */
    @PostMapping("/udcs/evict")
    public void evictAll() {
        udcOpService.evictAll();
    }
}
