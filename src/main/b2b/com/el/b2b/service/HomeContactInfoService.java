package com.el.b2b.service;

import com.el.b2b.api.HomeContactInfoQuery;
import com.el.b2b.domain.HomeContactInfo;
import com.el.core.domain.PagingResult;
import com.el.edp.util.ResultUtil;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:联络方式 Service
 */
public interface HomeContactInfoService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<HomeContactInfo> page(HomeContactInfoQuery query);

    /**
     * 查询全部联络方式
     *
     * @param query
     * @return
     */
    List<HomeContactInfo> findAll(HomeContactInfoQuery query);

    /**
     * 按ID查询
     *
     * @param id 查询条件
     * @return
     */
    HomeContactInfo findById(long id);

    /**
     * 保存或修改联系人信息
     *
     * @param homeContactInfo
     * @return
     */
    ResultUtil save(HomeContactInfo homeContactInfo);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int removeContact(Long[] ids);
}
