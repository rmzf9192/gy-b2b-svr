package com.el.edp.bpm.api;

import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Data
public class EdpBpmConfPayload {
    /**
     * 流程定义ID
     */
    @NotBlank
    private String defId;
    /**
     * 任务定义ID
     */
    @NotBlank
    private String nodeId;
    /**
     * 视图模式
     */
    private EdpBpmViewMode mode;
    /**
     * 视图配置(JSON)
     */
    @NotBlank
    private String conf;
}
