package com.el.edp.bpm.config;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * 变量类型以前缀进行区分:
 *
 * + 流程用户相关
 *   - u_  : 用户
 *   - us_ : 用户列表（逗号分隔）
 *   - g_  : 用户组（角色）
 *   - gs_ : 用户组（角色）列表（逗号分隔）
 *
 * + 计算逻辑相关
 *   - pi_ : 整数参数
 *   - pf_ : 浮点参数
 *   - pb_ : Bool参数
 *   - ps_ : 文本参数
 *   - pd_ : 日期参数(格式: yyyy-MM-dd)
 *   - pt_ : 时间参数(格式: HH:mm:ss)
 *
 * @author neo.pan
 * @since 17/6/8
 */
@Getter
public enum BpmParamMetas implements BpmParamMeta {

    /**
     * 非可配变量(一般指流程逻辑中间变量)
     */
    VAR(Usage.VAR, DataType.VAR, false),

    U_USR(Usage.USER, DataType.STR, false),
    U_GRP(Usage.GROUP, DataType.STR, false),
    U_USRS(Usage.USER, DataType.STR, true),
    U_GRPS(Usage.GROUP, DataType.STR, true),

    P_STR(Usage.PARAM, DataType.STR, false),
    P_INT(Usage.PARAM, DataType.INT, false),
    P_FLT(Usage.PARAM, DataType.FLT, false),
    P_BOOL(Usage.PARAM, DataType.BOOL, false),
    P_DATE(Usage.PARAM, DataType.DATE, false),
    P_TIME(Usage.PARAM, DataType.TIME, false),;

    private final String prefix;
    private final Usage usage;
    private final DataType type;
    private final boolean plural;

    BpmParamMetas(Usage usage, DataType type, boolean plural) {
        this.usage = usage;
        this.type = type;
        this.plural = plural;
        this.prefix = toPrefix();
    }

    private String toPrefix() {
        return usage == Usage.PARAM
            ? usage.getAbbr() + type.getAbbr() + "_"
            : usage.getAbbr() + (plural ? "s" : "") + "_";
    }

    public static BpmParamMeta of(String variableName) {
        int i = variableName.indexOf('_');
        if (i == -1) return VAR;
        return Stream.of(values())
            .filter(v -> variableName.startsWith(v.prefix))
            .findAny().orElse(VAR);
    }

}
