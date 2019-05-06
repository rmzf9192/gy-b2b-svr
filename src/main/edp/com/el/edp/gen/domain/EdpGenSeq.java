package com.el.edp.gen.domain;

import com.el.edp.gen.service.EdpGenOverflowException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 一次发号的上下文
 *
 * @author neo.pan
 * @since 2018/04/13
 */
@Slf4j
@EqualsAndHashCode(of = {"genDef", "seqKey", "seqCnt"})
public class EdpGenSeq {

    /**
     * 定义
     */
    @Getter
    private final EdpGenDef genDef;

    /**
     * 发放数量
     */
    private final int seqCnt;

    /**
     * 发号键
     */
    private String seqKey;

    /**
     * 计数键
     */
    private String cntKey;

    /**
     * @param genDef 定义
     * @param seqCnt 发放数量
     * @param genCtx 上下文数据（可为null）
     */
    public EdpGenSeq(EdpGenDef genDef, int seqCnt, Object genCtx) {
        this.genDef = genDef;
        this.seqCnt = Math.max(seqCnt, 1);
        buildKeys(genDef, genCtx);
        log.trace("[EDP-GEN] REQ is {}, {}, {}", genDef.getCode(), seqCnt, genCtx);
    }

    private void buildKeys(EdpGenDef genDef, Object genCtx) {
        val ctx = new EdpGenContext(genCtx);
        this.seqKey = genDef.genSeqKey(ctx);
        this.cntKey = genDef.genCntKey(ctx);
    }

    @Override
    public String toString() {
        return genDef.getCode() + "/" + seqKey + "/" + cntKey;
    }

    /**
     * 计数值
     */
    private Long cntVal;

    /**
     * @param seqCnt 计数键值
     * @throws EdpGenOverflowException 当`cntVal+cntCnt > cntMax`时抛出
     */
    public void updateAndCheckCntVal(EdpGenSeqCnt seqCnt) {
        if (seqCnt != null && cntKey.equals(seqCnt.getKey())) {
            cntVal = seqCnt.getVal();
        } else {
            cntVal = genDef.getCntMin();
        }
        // check overflow
        final long cntMax = genDef.getCntMax();
        if (cntVal > cntMax) {
            throw new EdpGenOverflowException(this);
        }
        final long cntValNext = getCntValNext();
        if (cntValNext > cntMax + 1) {
            throw new EdpGenOverflowException(this);
        }
        if (cntValNext > cntMax) {
            log.warn("[EDP-GEN] NEXT counter value of generator ({}) will OVERFLOW: {} > {}", this, cntValNext, cntMax);
        }
        log.trace("[EDP-GEN] generator new value: {}", cntVal);
    }

    /**
     * @return 计数值的下一跳
     */
    public long getCntValNext() {
        return cntVal + seqCnt;
    }

    /**
     * 生成一个流水号
     *
     * @return 流水号
     */
    public String buildSeq() {
        return buildSeq(cntVal);
    }

    /**
     * 生成一批流水号
     *
     * @return 流水号
     */
    public Stream<String> buildSeqs() {
        return LongStream.range(cntVal, getCntValNext())
            .mapToObj(this::buildSeq);
    }

    /**
     * 生成一个流水号
     *
     * @param cntVal 计数值
     * @return 流水号
     */
    private String buildSeq(long cntVal) {
        return String.format(genDef.getSeqFmt(),
            genDef.noSeqKey() ? "" : seqKey,
            genDef.noCntKey() ? "" : cntKey,
            genDef.seq(cntVal));
    }

}
