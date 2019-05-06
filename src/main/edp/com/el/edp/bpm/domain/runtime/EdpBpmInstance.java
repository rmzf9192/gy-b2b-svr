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
@EqualsAndHashCode(of = "prcId")
public class EdpBpmInstance {
    /**
     * 流程实例编号
     */
    private String prcId;

    /**
     * 流程定义编号
     */
    private String defId;

    /**
     * 流程定义名称
     */
    private String defName;

    /**
     * 流程状态名称
     */
    private String stateName;

    /**
     * 流程开始时间
     */
    @TimeUtil.TIME
    private LocalDateTime startTime;

    /**
     * 流程结束时间
     */
    @TimeUtil.TIME
    private LocalDateTime endTime;
}
