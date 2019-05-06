package com.el.edp.bpm.config;

import lombok.Getter;

/**
 * 流程参数（即流程可配置变量）
 *
 * @author neo.pan
 * @since 17/6/8
 */
public interface BpmParamMeta {

    @Getter
    enum Usage {
        VAR(""), USER("u"), GROUP("g"), PARAM("p"),;

        private final String abbr;

        Usage(String abbr) {
            this.abbr = abbr;
        }
    }

    @Getter
    enum DataType {
        VAR(""), STR("s"), INT("i"), FLT("f"), BOOL("b"), DATE("d"), TIME("t"),;

        private final String abbr;

        DataType(String abbr) {
            this.abbr = abbr;
        }
    }

    /**
     * @return 用途
     */
    Usage getUsage();

    /**
     * @return 数据类型
     */
    DataType getType();

    /**
     * @return 复数的？
     */
    boolean isPlural();
}
