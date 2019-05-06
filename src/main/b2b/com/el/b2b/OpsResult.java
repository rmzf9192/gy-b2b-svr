package com.el.b2b;

import com.el.core.DevError;
import com.el.core.web.OpResult;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author neo.pan
 * @since 17/4/28
 */
public enum OpsResult implements OpResult {

    OK("操作成功"),
    NG_DATA_NOT_FOUND("数据未找到"),
    NG_NOT_NULL_CONSTRAINT("非空约束"),

    NG_SUPP_EMAIL_EXISTS("联系人邮箱已存在"),
    NG_SUPP_QUALIFY_REQUIRED("至少上传一个供应商资质"),


    NG_HAS_USED("模板已被使用"),
    ;

    @Getter
    private final String desc;

    @Override
    public String getCode() {
        return name();
    }

    OpsResult(String desc) {
        this.desc = desc;
    }

    public static OpsResult of(String opsResult) {
        return Arrays.stream(OpsResult.values())
            .filter(o -> Objects.equals(o.name(), opsResult))
            .findAny().orElseThrow(DevError::unexpected);
    }

}
