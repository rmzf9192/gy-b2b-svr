package com.el.b2b.service;

import com.el.b2b.api.OuQuery;
import com.el.b2b.domain.OuDomain;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * 公司信息相关业务
 * Created by jerry.feng
 * on 2018/5/10.
 */
public interface OuService {

    /**
     * 根据条件查询公司信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     */
    PagingResult<OuDomain> findPage(OuQuery query);

    /**
     * 查询所有
     *
     * @return 查询结果
     */
    List<OuDomain> findAll();

    /**
     * 根据ID查询具体数据
     *
     * @param id 查询条件
     * @return 返回结果
     */
    OuDomain findById(Long id);

    /**
     * 根据ouCode查询具体数据
     *
     * @param ouCode 查询条件
     * @return 返回结果
     */
    OuDomain findByOuCode(String ouCode);

    /**
     * 删除操作
     */
    int delOu(Long[] ids);

}
