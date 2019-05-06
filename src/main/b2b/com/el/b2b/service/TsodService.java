package com.el.b2b.service;

import com.el.b2b.domain.TsodDomain;

/**
 * Created by jerry.feng
 * on 2018/5/16.
 */
public interface TsodService {


    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    TsodDomain findById(long id);

    /**
     * 增加订单模板信息
     *
     * @param domain
     * @return 新增条数
     */
    int saveOrUpdate(TsodDomain domain);

    /**
     * 逻辑批量删除订单模板信息
     *
     * @param ids
     * @return 执行结果
     */
    int delByIds(Long[] ids);
}
