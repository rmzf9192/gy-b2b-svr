package com.el.edp.bpm.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程参数
 *
 * @author neo.pan
 * @since 17/6/8
 */
@Data
@EqualsAndHashCode(of = "name")
public class BpmParam {

    /**
     * 元信息
     */
    private BpmParamMeta meta = BpmParamMetas.VAR;

    /**
     * 参数名
     */
    private String name;

    /**
     * 参数值
     */
    private Object value;

    public static BpmParam of(String name) {
        BpmParam param = new BpmParam();
        param.name = name;
        param.meta = BpmParamMetas.of(name);
        return param;
    }

    public static BpmParam of(String name, Object value) {
        BpmParam param = BpmParam.of(name);
        param.value = value;
        return param;
    }

}
