package com.el.edp.bpm.domain.runtime;

import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author neo.pan
 * @since 17/5/26
 */
@Data
@EqualsAndHashCode(of = "defId")
public class EdpBpmDepDefinition {
    /**
     * 部署编号
     */
    private String depId;

    /**
     * 部署名称
     */
    private String depName;

    /**
     * 部署时间
     */
    @TimeUtil.TIME
    private LocalDateTime depTime;

    /**
     * 部署源
     */
    private String depSrc;

    /**
     * 资源名称
     */
    private String srcName;

    /**
     * 流程定义编号
     */
    private String defId;

    /**
     * 流程定义名称
     */
    private String defName;
}
