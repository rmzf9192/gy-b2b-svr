package com.el.edp.gen.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
@Setter
@Getter
@RequiredArgsConstructor
public class EdpGenContext {

    public static String lpad(int padSize, char padChar, Object val) {
        return StringUtils.leftPad(val.toString(), padSize, padChar);
    }

    public static String rpad(int padSize, char padChar, Object val) {
        return StringUtils.rightPad(val.toString(), padSize, padChar);
    }

    public static String pad(int padSize, char padChar) {
        return StringUtils.repeat(padChar, padSize);
    }

    /**
     * @param ta 时间
     * @param tp 格式
     * @return 格式化后的时间
     */
    public static String t(TemporalAccessor ta, String tp) {
        return DateTimeFormatter.ofPattern(tp).format(ta);
    }

    /**
     * 当前时间
     */
    private final LocalDateTime now = LocalDateTime.now();

    /**
     * @param tp 格式
     * @return 格式化后的当前时间
     */
    public String t(String tp) {
        return t(now, tp);
    }

    /**
     * 业务上下文数据
     */
    private final Object ctx;

}
