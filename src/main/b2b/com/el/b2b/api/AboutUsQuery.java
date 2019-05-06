package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AboutUsQuery extends PagingQuery {

    /**
     * 客户Code
     */
    private String custCode;
    /**
     * 公司Code
     */
    private String ouCode;
}
