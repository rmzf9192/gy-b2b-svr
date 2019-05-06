package com.el.b2b.domain;

import com.el.mbg.domain.TPbOu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 公司信息相关业务
 * Created by jerry.feng
 * on 2018/5/10.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OuDomain extends TPbOu {

    /**
     * 公司地址
     */
    private String addrName;
    /**
     * 详细地址
     */
    private String detailaddr;
}
