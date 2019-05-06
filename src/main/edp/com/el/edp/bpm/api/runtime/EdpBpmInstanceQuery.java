package com.el.edp.bpm.api.runtime;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 条件搜索
 *
 * @author Simon.Hu
 * @since 17/07/12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpBpmInstanceQuery extends PagingQuery {
    /**
     * 流程定义编号
     */
    private String defId;

    /**
     * 流程定义名称
     */
    private String defName;

    /**
     * 流程状态
     */
    public String state;
}
