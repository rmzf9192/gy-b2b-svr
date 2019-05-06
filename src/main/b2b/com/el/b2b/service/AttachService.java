package com.el.b2b.service;

import com.el.b2b.api.AttachQuery;
import com.el.b2b.domain.Attach;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:附件 Service
 */
public interface AttachService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<Attach> page(AttachQuery query);

    /**
     * 查询全部附件
     *
     * @param query
     * @return
     */
    List<Attach> findAll(AttachQuery query);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int remove(Long[] ids);
}
