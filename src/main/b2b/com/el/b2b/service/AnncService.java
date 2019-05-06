package com.el.b2b.service;

import com.el.b2b.api.AnncQuery;
import com.el.b2b.domain.Annc;
import com.el.core.domain.PagingResult;
import com.el.edp.util.ResultUtil;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:公告 Service
 */
public interface AnncService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<Annc> page(AnncQuery query);

    /**
     * 查询全部公告
     *
     * @param query
     * @return
     */
    List<Annc> findAll(AnncQuery query);

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    Annc findById(long id);

    /**
     * 保存或修改公告信息
     *
     * @param annc
     * @return
     */
    ResultUtil save(Annc annc);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int remove(Long[] ids);
}
