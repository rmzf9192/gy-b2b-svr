package com.el.edp.sec.api;

import com.el.core.domain.PagingQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Getter
@Setter
public class EdpOrganizationQuery extends PagingQuery {
    /**
     *  组织编号 - PB_ORG.ORG_CODE
     */
    private String orgCode;

    /**
     *  组织名称 - PB_ORG.ORG_NAME
     */
    private String orgName;
}
