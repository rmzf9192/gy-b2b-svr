package com.el.b2b.domain;

import com.el.mbg.domain.TB2bTsoD;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by jerry.feng
 * on 2018/5/15.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TsodDomain extends TB2bTsoD {
    /**
     * 模板编号
     */
    private String docNo;
    /**
     * 商品itemCode
     */
    private String itemCode;

}
