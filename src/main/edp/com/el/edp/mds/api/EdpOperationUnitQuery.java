package com.el.edp.mds.api;

import com.el.core.domain.PagingQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Getter
@Setter
public class EdpOperationUnitQuery extends PagingQuery {
    /**
     *  公司编号 - PB_OU.OU_CODE
     */
    private String ouCode;

    /**
     *  公司名称 - PB_OU.OU_NAME
     */
    private String ouName;

    /**
     *  是否删除 - PB_OU.DELETE_FLAG
     */
    private Short deleteFlag;
}
