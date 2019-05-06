package com.el.b2b.service;

import com.el.b2b.api.PbItemUomQuery;
import com.el.b2b.domain.PbItemSaleprice;
import com.el.b2b.domain.PbItemUom;
import com.el.b2b.domain.SoD;
import com.el.b2b.domain.TsoDomain;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 */
public interface UomService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<PbItemUom> page(PbItemUomQuery query);

    List<PbItemSaleprice> queryByItemOuterCode(List<PbItemSaleprice> init);

    List<TsoDomain> queryByItemCode(List<TsoDomain> init);

    List<SoD> setUomList(List<SoD> init);
}
