package com.el.edp.sfs.domain;

import com.el.core.util.DecUtil;
import com.el.core.web.OpResult;
import com.el.edp.sfs.service.EdpSfsOp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.el.core.util.DecUtil.D2;

/**
 * SFS 图片文件检查
 *
 * @author neo.pan
 * @since 2018/04/18
 */
@Data
@EqualsAndHashCode(of = "repoCode")
public class EdpSfsImgCheck {

    private String repoCode;
    private int minResW;
    private int maxResW;
    private int minResH;
    private int maxResH;
    private int minResR_D2;
    private int maxResR_D2;

    /**
     * 检查
     *
     * @param w 宽度
     * @param h 高度
     * @return true if OK
     */
    public OpResult check(int w, int h) {
        if (minResW > 0 && w < minResW || maxResW > 0 && w > maxResW) {
            return EdpSfsOp.NG_IMG_W;
        }
        if (minResH > 0 && h < minResH || maxResH > 0 && h > maxResH) {
            return EdpSfsOp.NG_IMG_H;
        }
        int r = (int) DecUtil.divideAndRound(w * D2, h);
        if (minResR_D2 > 0 && r < minResR_D2 || maxResR_D2 > 0 && r > maxResR_D2) {
            return EdpSfsOp.NG_IMG_R;
        }
        return null;
    }

}
