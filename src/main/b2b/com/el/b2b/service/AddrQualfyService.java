package com.el.b2b.service;

import com.el.b2b.api.AddrQualfyQuery;
import com.el.b2b.domain.AddrQualfyDomain;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * 客户证照信息相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
public interface AddrQualfyService {

    /**
     * 根据条件查询客户证照信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     */
    PagingResult<AddrQualfyDomain> findPage(AddrQualfyQuery query);

    /**
     * 查询所有客户证照信息
     *
     * @return 查询结果
     */
    List<AddrQualfyDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    AddrQualfyDomain findById(long id);

    /**
     * 根据地址号，获取证照信息列表
     *
     * @param addrNo
     * @return
     */
    List<AddrQualfyDomain> findByAddrNo(String addrNo);
}
