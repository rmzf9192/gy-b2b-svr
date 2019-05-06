package com.el.edp.gen.domain;

import com.el.core.domain.CodeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 发号配置
 *
 * @author neo.pan
 * @since 2018/04/12
 */
@Data
@EqualsAndHashCode(of = "code")
public class EdpGenDef implements CodeName {

    private static final String NO_KEY = "-";

    /**
     * 发号器代码
     */
    private String code;
    /**
     * 发号器名称
     */
    private String name;

    /**
     * 流水号模版(printf-style)、含三个%s、分别代表发号键、计数键和计数值
     */
    private String seqFmt;
    /**
     * 发号键模版(SpEL)、变化时新建实例
     */
    private String seqKey;

    public boolean noSeqKey() {
        return NO_KEY.equals(seqKey);
    }

    /**
     * 计数键模版(SpEL)、变化时重新计数
     */
    private String cntKey;

    public boolean noCntKey() {
        return NO_KEY.equals(cntKey);
    }

    private final static ExpressionParser parser = new SpelExpressionParser();

    /**
     * @param ctx 上下文数据
     * @return 发号键
     */
    public String genSeqKey(Object ctx) {
        return noSeqKey() ? seqKey : parser.parseExpression(seqKey)
            .getValue(ctx, String.class);
    }

    /**
     * @param ctx 上下文数据
     * @return 计数键
     */
    public String genCntKey(Object ctx) {
        return noCntKey() ? cntKey : parser.parseExpression(cntKey)
            .getValue(ctx, String.class);
    }

    /**
     * 发号值长度
     */
    private int cntLen;
    /**
     * 发号值补零
     */
    private char cntPad = '0';
    /**
     * 最小发号值
     */
    private Long cntMin;
    /**
     * 最大发号值
     */
    private Long cntMax;

    /**
     * @param seqCnt 计数值
     * @return 格式后的计数值
     */
    public String seq(long seqCnt) {
        return EdpGenContext.lpad(cntLen, cntPad, seqCnt);
    }

}
