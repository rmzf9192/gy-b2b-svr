package com.el.b2b.domain;

import com.el.mbg.domain.TPbCust;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by jerry.feng
 * on 2018/5/11.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustDomain extends TPbCust {
    /**
     * 公司code
     */
    private String ouCode;
    /**
     * 公司Name
     */
    private String ouName;

}
