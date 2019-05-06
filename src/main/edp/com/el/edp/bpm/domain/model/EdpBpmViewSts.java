package com.el.edp.bpm.domain.model;

import lombok.Value;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/26
 */
@Value(staticConstructor = "of")
public class EdpBpmViewSts {

    /**
     * 流程表单视图配置文件
     */
    private final List<String> files;
    /**
     * 流程表单视图配置数据
     */
    private final List<EdpBpmViewKey> confs;

}
