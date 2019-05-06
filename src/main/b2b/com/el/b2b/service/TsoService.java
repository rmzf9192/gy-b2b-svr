package com.el.b2b.service;

import com.el.b2b.api.TsoQuery;
import com.el.b2b.domain.TsoCodeName;
import com.el.b2b.domain.TsoDomain;
import com.el.b2b.domain.TsoObject;
import com.el.b2b.domain.TsodDomain;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
public interface TsoService {
    /**
     * 根据条件查询订单模板信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     */
    PagingResult<TsoDomain> findPage(TsoQuery query);

    /**
     * 增加订单模板信息
     *
     * @param domain
     * @return 新增条数
     */
    int saveOrUpdate(TsoObject domain);

    /**
     * 逻辑批量删除订单模板信息
     *
     * @param ids
     * @return 执行结果
     */
    int delTsodByIds(Long[] ids);

    /**
     * 根据模板id，itemId删除指定的商品
     *
     * @param domain
     * @return 执行结果
     */
    int removeTsodByItemCode(TsodDomain domain);

    /**
     * UDC 获取模版信息
     *
     * @param tsoQuery
     * @return
     */
    List<TsoCodeName> getTsoList(TsoQuery tsoQuery);
}
