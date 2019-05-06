package com.el.edp.sfs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SFS 图片缩放设置
 *
 * @author neo.pan
 * @since 2018/04/18
 */
@Data
@EqualsAndHashCode(of = {"repoCode", "suffix"})
public class EdpSfsImgScale {

    private String repoCode;
    private String suffix;
    private int scaleW;
    private int scaleH;

    /**
     * 检查
     *
     * @param w 宽度
     * @param h 高度
     * @return true if OK
     */
    public boolean check(int w, int h) {
        // TODO
        return true;
    }

}
