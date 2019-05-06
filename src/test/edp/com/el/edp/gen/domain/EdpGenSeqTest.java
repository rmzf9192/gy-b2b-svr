package com.el.edp.gen.domain;

import com.el.edp.gen.service.EdpGenOverflowException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
@Slf4j
public class EdpGenSeqTest {

    private EdpGenDef genDef1 = new EdpGenDef() {{
        setCode("DEF1");
        setSeqFmt("TT_%s_%s_%s");
        setSeqKey("lpad(4, '$', ctx.entCode)");
        setCntKey("t(ctx.buyTime, 'yMMdd')");
        setCntLen(2);
        setCntPad('0');
        setCntMin(1L);
        setCntMax(99L);
    }};

    private EdpGenDef genDef2 = new EdpGenDef() {{
        setCode("DEF2");
        setSeqFmt("TT_%s_%s_%s");
        setSeqKey("-");
        setCntKey("t(ctx.buyTime, 'yMMdd.HHmmss')");
        setCntLen(2);
        setCntPad('0');
        setCntMin(1L);
        setCntMax(99L);
    }};

    private EdpGenDef genDef3 = new EdpGenDef() {{
        setCode("DEF3");
        setSeqFmt("TT_%s_%s_%s");
        setSeqKey("-");
        setCntKey("-");
        setCntLen(2);
        setCntPad('0');
        setCntMin(1L);
        setCntMax(99L);
    }};

    private static class MyGenContext {
        @Getter
        private String entCode = "EL";
        @Getter
        private LocalDateTime buyTime = LocalDateTime.of(
            2018, 5, 1, 2, 3, 4);
    }

    private static final MyGenContext genCtx = new MyGenContext();

    @Test
    public void buildKeys() {
        String actual = new EdpGenSeq(genDef1, 1, genCtx).toString();
        Assert.assertThat(actual, equalTo("DEF1/$$EL/20180501"));
    }

    @Test
    public void buildKeys_seq_key_undefined() {
        String actual = new EdpGenSeq(genDef2, 1, genCtx).toString();
        Assert.assertThat(actual, equalTo("DEF2/-/20180501.020304"));
    }

    @Test
    public void buildKeys_keys_undefined() {
        String actual = new EdpGenSeq(genDef3, 1, genCtx).toString();
        Assert.assertThat(actual, equalTo("DEF3/-/-"));
    }

    @Test
    public void updateAndCheckCntVal() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.getCntValNext(), equalTo(99L));
    }

    @Test
    public void updateAndCheckCntVal_will_overflow() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 2, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.getCntValNext(), equalTo(100L));
    }

    @Test(expected = EdpGenOverflowException.class)
    public void updateAndCheckCntVal_overflow1() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(100L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
    }

    @Test(expected = EdpGenOverflowException.class)
    public void updateAndCheckCntVal_overflow2() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 3, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
    }

    @Test
    public void buildSeq() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeq(), equalTo("TT_$$EL_20180501_98"));
    }

    @Test
    public void buildSes() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 2, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeqs().collect(Collectors.toList()),
            equalTo(Arrays.asList("TT_$$EL_20180501_98", "TT_$$EL_20180501_99")));
    }

    @Test
    public void buildSeq_cnt_reset() {
        EdpGenSeq seq = new EdpGenSeq(genDef1, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180430");
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeq(), equalTo("TT_$$EL_20180501_01"));
    }

    @Test
    public void buildSeq_seq_key_undefined() {
        EdpGenSeq seq = new EdpGenSeq(genDef2, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501.020304");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeq(), equalTo("TT__20180501.020304_98"));
    }

    @Test
    public void buildSeq_seq_key_undefined_cnt_reset() {
        EdpGenSeq seq = new EdpGenSeq(genDef2, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("20180501.020305");
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeq(), equalTo("TT__20180501.020304_01"));
    }

    @Test
    public void buildSeq_keys_undefined() {
        EdpGenSeq seq = new EdpGenSeq(genDef3, 1, genCtx);
        EdpGenSeqCnt seqCnt = new EdpGenSeqCnt() {{
            setKey("-");
            setVal(98L);
        }};
        seq.updateAndCheckCntVal(seqCnt);
        Assert.assertThat(seq.buildSeq(), equalTo("TT___98"));
    }

}
