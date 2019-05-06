package com.el.edp.sec.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author neo.pan
 * @since 17/8/17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpAuthUserQuery extends PagingQuery {

//    /**
//     * 模糊匹配：员工号、名称、手机、邮箱
//     */
//    private String userLike;

    /**
     * 组件搜索关键字
     */
    private String key;

    private String loginName;
    private String name;
    private String empCode;


    //当前登陆人的公司编码
    private String entCode;
}
