package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HomeContactInfoQuery extends PagingQuery {

    /**
     * 公司Code
     */
    private String ouCode;

    /**
     * 标题 - HOME_CONTACT_INFO.TITLE
     */
    private String title;

    /**
     * 联系人 - HOME_CONTACT_INFO.CONTACT
     */
    private String contact;
}
