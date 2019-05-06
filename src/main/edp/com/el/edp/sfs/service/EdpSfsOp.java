package com.el.edp.sfs.service;

import com.el.core.web.OpResult;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
public enum EdpSfsOp implements OpResult {

    NG_SIZE("文件太大"),
    NG_MIME("无效文件类型"),
    NG_EXT("无效文件扩展名"),
    NG_IMG_W("图片宽度不符合要求"),
    NG_IMG_H("图片高度不符合要求"),
    NG_IMG_R("图片比率不符合要求"),
    NG_TMP_DIR("临时目录不存在了");

    private String desc;

    EdpSfsOp(String desc) {
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
