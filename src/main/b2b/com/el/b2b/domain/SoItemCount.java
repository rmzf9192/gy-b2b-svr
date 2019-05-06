package com.el.b2b.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/28
 * @Description:
 */
@Data
@ToString(callSuper = true)
public class SoItemCount {

    private int all;

    private int ordered;

    private int submitted;

    private int unReceived;
}
