package com.el.b2b.service;

import com.el.b2b.domain.HomeOu;
import com.el.edp.util.ResultUtil;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:公司 Service
 */
public interface HomeOuService {

    /**
     * 按ouCode查询首页-公司信息
     *
     * @param ouCode
     * @return
     */
    HomeOu findByOuCode(String ouCode);

    /**
     * 保存或修改公司信息
     *
     * @param homeOu
     * @return
     */
    ResultUtil save(HomeOu homeOu);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int remove(Long[] ids);
}
