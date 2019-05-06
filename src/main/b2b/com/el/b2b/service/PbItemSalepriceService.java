package com.el.b2b.service;

import com.el.b2b.api.PbItemSalepriceQuery;
import com.el.b2b.domain.PbItemSaleprice;
import com.el.core.domain.PagingResult;

import java.math.BigDecimal;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品价格表 Service
 */
public interface PbItemSalepriceService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<PbItemSaleprice> page(PbItemSalepriceQuery query);

    /**
     * 按ID查询
     *
     * @param id 查询条件
     * @return
     */
    PbItemSaleprice findById(long id);

    /**
     * 保存
     *
     * @param pbItem
     * @return
     */
    int saveOrUpdate(PbItemSaleprice pbItem);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 根据产品获取价格信息
     *
     * @param itemCode
     * @param ouCode
     * @param custCode
     * @return
     */
    BigDecimal getPriceByItemCode(String itemCode, String ouCode, String custCode);


}
