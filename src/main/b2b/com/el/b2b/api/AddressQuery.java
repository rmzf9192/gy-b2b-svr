package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddressQuery extends PagingQuery {

    /**
     * 客户Code
     */
    private String custCode;
    /**
     * 公司Code
     */
    private String ouCode;
//    /**
//     * 公司名称
//     */
//    private String  ouName;
    /**
     * 地址名称
     */
    private String addrName;
    /**
     * 地址信息
     */
    private String detailaddr;
}
