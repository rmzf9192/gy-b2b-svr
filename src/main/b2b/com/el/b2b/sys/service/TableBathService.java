package com.el.b2b.sys.service;


import com.el.b2b.sys.TableMeta;

import java.util.List;

/**
 * 表公共操作
 * Created by jerry.feng
 * 2018/05/17
 */
public interface TableBathService {

    /**
     * 批量业务逻辑删除
     *
     * @param idList 删除表ID集合
     * @return 删除结果
     */
    int deleteBath(TableMeta tableMeta, List<Long> idList);

    /**
     * 批量业务逻辑删除同时修改数据状态
     *
     * @param idList 删除表ID集合
     * @return 删除结果
     */
    int deleteBathStatus(TableMeta tableMeta, List<Long> idList, String status);

}
