package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 客户证照信息相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddrQualfyQuery extends PagingQuery {
    /**
     * 资质名称 - PB_ADDR_QUALIFY.QUALIFY_NAME
     */
    private String qualifyName;
    /**
     * 资质类型 - PB_ADDR_QUALIFY.QUALIFY_TYPE
     */
    private String qualifyType;
}
