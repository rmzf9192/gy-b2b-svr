package com.el.b2b.service;

import com.el.b2b.api.SoQuery;
import com.el.b2b.domain.*;
import com.el.core.domain.PagingResult;
import com.el.edp.util.ResultUtil;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description:商品主文件 Service
 */
public interface SoService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<So> page(SoQuery query);

    /**
     * 按ID查询
     *
     * @param id 查询条件
     * @return
     */
    So findById(long id);

    /**
     * 保存
     *
     * @param so
     * @return
     */
    int saveOrUpdate(So so);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(long id);

    int deleteOrdered(long id);

    /**
     * 按query查询总数
     *
     * @param query
     * @return
     */
    int findCount(SoQuery query);

    /**
     * 按query查询订单信息
     *
     * @param query
     * @return
     */
    List<So> findPage(SoQuery query);

    ResultUtil saveForm(SoObject soObject);

    List<SoD> saveSoAndSod(So so, List<SoD> soDList, String ouCode, String custCode,boolean saveFlag);

    /**
     * 批量删除订单明细
     *
     * @param ids
     * @return
     */
    ResultUtil delByIds(Long[] ids,boolean flag);

    /**
     * 数据同步
     * @return
     */
    ResultUtil sync(String strCode);

    /**
     * 订单导出，展示主、表明细表
     *
     * @param query
     * @return 查询结果
     */
    PagingResult<SoExport> exportDomain(SoQuery query);

    /**
     * 按QUERY分页查询 订单收货信息
     *
     * @param query 查询条件
     * @return
     */
    List<SoDo> findSoDoPage(SoQuery query);

    /**
     * 按query查询 单收货信息 总数
     *
     * @param query
     * @return
     */
    int findSoDoCount(SoQuery query);

    /**
     * 查询未收货的订单数量
     *
     * @param query
     * @return
     */
    int findUnReceivedSoCount(SoQuery query);

    /**
     * 根据订单号查询订单明细
     *
     * @param id
     * @param shipStatus
     * @return
     */
    List<SoDo> findSoDoById(String id, String shipStatus);

    /**
     * 批量修改订单明细
     *
     * @param ids
     * @return
     */
    int updateShipStatus(String[] ids);

    /**
     * 已发货订单导出查询
     * @param query
     * @return
     */
    PagingResult<SoDelivered> deliveredDomain(SoQuery query);

    public int deleteIds(Long[] ids);

}
