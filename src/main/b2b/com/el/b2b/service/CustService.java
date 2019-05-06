package com.el.b2b.service;

import com.el.b2b.api.CustQuery;
import com.el.b2b.domain.CustDomain;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * Created by jerry.feng
 * on 2018/5/11.
 */
public interface CustService {

    /**
     * 根据条件查询客户信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     */
    PagingResult<CustDomain> findPage(CustQuery query);

    /**
     * 查询所有客户信息
     *
     * @return 查询结果
     */
    List<CustDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    CustDomain findById(Long id);


    /**
     * 根据custCode查询具体数据
     *
     * @param custCode 查询条件
     * @return 返回结果
     */
    CustDomain findByCustCode(String custCode);
}
