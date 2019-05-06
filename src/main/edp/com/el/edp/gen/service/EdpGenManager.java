package com.el.edp.gen.service;

import com.el.edp.gen.domain.EdpGenDef;

import java.util.List;
import java.util.Map;

/**
 * 发号器管理服务
 *
 * @author neo.pan
 * @since 2018/04/13
 */
public interface EdpGenManager {

    /**
     * @return 发号器定义一览
     */
    Map<String, EdpGenDef> genDefs();

    /**
     * 发一个号
     *
     * @param genCode 发号器代码
     * @param genCtx  发号器上下文
     * @return 下一个号
     */
    String nextSeq(String genCode, Object genCtx);

    /**
     * 发一批号
     *
     * @param genCode 发号器代码
     * @param genCnt  一批数量
     * @param genCtx  发号器上下文
     * @return 下一批号
     */
    List<String> nextSeqs(String genCode, int genCnt, Object genCtx);

}
