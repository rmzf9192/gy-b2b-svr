package com.el.b2b.service;

import com.el.b2b.api.PbItemQuery;
import com.el.b2b.domain.PbItem;
import com.el.core.domain.PagingResult;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品主文件 Service
 */
public interface PbItemService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<PbItem> page(PbItemQuery query);

    /**
     * 按ID查询
     *
     * @param id 查询条件
     * @return
     */
    PbItem findById(long id);

    /**
     * 保存
     *
     * @param pbItem
     * @return
     */
    int saveOrUpdate(PbItem pbItem);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(long id);


    /**
     * 按outerCode查询
     *
     * @param outerCode 查询条件
     * @return
     */
    PbItem findByOuterCode(String outerCode);

    /**
     * 按itemCode查询
     *
     * @param itemCode 查询条件
     * @return
     */
    PbItem findByItemCodeAndOuCode(String itemCode, String ouCode);


}
