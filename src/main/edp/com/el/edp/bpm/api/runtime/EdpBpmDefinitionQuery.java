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
public class EdpBpmDefinitionQuery extends PagingQuery {
    /**
     * 部署名称
     */
    private String depName;

    /**
     * 资源名称
     */
    private String srcName;

    /**
     * 流程定义名称
     */
    private String defName;
}
