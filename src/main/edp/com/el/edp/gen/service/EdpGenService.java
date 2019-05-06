package com.el.edp.gen.service;

import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.domain.EdpGenSeq;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发号处理服务
 *
 * @author neo.pan
 * @since 2018/04/12
 */
public interface EdpGenService {

    /**
     * @param tenantId 租户ID
     * @param appCode  应用代码
     * @return 指定租客应用的发号器定义列表
     */
    List<EdpGenDef> genDefs(long tenantId, String appCode);

    /**
     * 更新发号器实例的计数值
     *
     * @param genSeq 发号上下文(计数值属性会被更新)
     * @return 是否成功更新(若为false则说明需要新的发号器实例)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    boolean updateCnt(EdpGenSeq genSeq);

    /**
     * 创建一个新的发号器实例
     *
     * @param genSeq 发号上下文(计数值属性会被更新)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addNewSeq(EdpGenSeq genSeq);

}
