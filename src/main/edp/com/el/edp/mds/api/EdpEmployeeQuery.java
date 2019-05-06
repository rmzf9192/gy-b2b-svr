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
public class EdpEmployeeQuery extends PagingQuery {
    /**
     *  编号 - PB_EMP.EMP_CODE
     */
    private String empCode;

    /**
     *  姓名 - PB_EMP.EMP_NAME
     */
    private String empName;
}
