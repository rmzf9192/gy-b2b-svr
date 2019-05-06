package com.el.b2b.service;

import com.el.b2b.api.PbItemUomQuery;
import com.el.b2b.domain.*;
import com.el.b2b.mapper.UomMapper;
import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.edi.iam.payload.EdpUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class UomServiceImpl implements UomService {

    private final UomMapper uomMapper;
    private final EdpPrincipalService principalService;//当前登录人


    private List<UomTrans> uomList(String itemCode){
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();//获取当前用户的公司id
        Uom mainUom = uomMapper.queryMainUom(itemCode,ouCode);
        List<UomTrans> uomList= uomMapper.queryByItemCode(itemCode, mainUom.getUomCode());
        if(uomList==null || uomList.size()==0){
            uomList = new ArrayList<>();
        }
        uomList.add(new UomTrans(){{
            setMcu(" ");
            setItm(mainUom.getOuterCode());
            setUm(mainUom.getUomCode());
            setUmdesc(mainUom.getUomDesc());
            setRum(mainUom.getUomCode());
            setConv(1);
            setCnv1(1);
        }});
        return uomList;
    }

    @Override
    public PagingResult<PbItemUom> page(PbItemUomQuery query) {
        EdpUser user = principalService.user();//获取当前登陆用户
        String entCode = user.getEntCode();//获取当前用户的公司code
        query.setOuCode(entCode);
        int total = uomMapper.findCount(query);
        return total > 0 ? PagingResult.of(uomMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<PbItemSaleprice> queryByItemOuterCode(List<PbItemSaleprice> addedItemList) {
        if(addedItemList!=null && addedItemList.size()>0){
            for(PbItemSaleprice d:addedItemList){
                List<UomTrans> uomList = uomList(d.getItemCode());
                for(UomTrans u: uomList){
                    if(u.getUm().equals(d.getUomCode())){
                        d.setConv(new BigDecimal(String.valueOf(u.getConv())));
                        break;
                    }
                }
                d.setUomList(uomList);
            }
            return addedItemList;
        }else{
            return null;
        }
    }

    @Override
    public List<TsoDomain> queryByItemCode(List<TsoDomain> addedItemList) {
        if(addedItemList!=null && addedItemList.size()>0){
            for(TsoDomain d:addedItemList){
                List<UomTrans> uomList = uomList(d.getItemCode());
                for(UomTrans u: uomList){
                    if(u.getUm().equals(d.getUomCode())){
                        d.setConv(new BigDecimal(String.valueOf(u.getConv())));
                        break;
                    }
                }
                d.setUomList(uomList);
            }
            return addedItemList;
        }else{
            return null;
        }
    }

    @Override
    public List<SoD> setUomList(List<SoD> addedItemList) {
        if(addedItemList!=null && addedItemList.size()>0){
            for(SoD d:addedItemList){
                List<UomTrans> uomList = uomList(d.getItemCode());
                for(UomTrans u: uomList){
                    if(u.getUm().equals(d.getUomCode())){
                        d.setConv(new BigDecimal(String.valueOf(u.getConv())));
                        break;
                    }
                }
                d.setUomList(uomList);
            }
            return addedItemList;
        }else{
            return null;
        }
    }
}

