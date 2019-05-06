package com.el.edp.sec.domain;

import com.el.cfg.jdbc.EdpEntity;
import com.el.core.util.TimeUtil;
import com.el.edi.EdpPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author danfeng
 * @since 18/03/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EdpAuditLog extends EdpEntity implements EdpPayload {

    /**
     * 操作者ID
     */
    private Long opUserId;

    /**
     * 操作者名称
     */
    private String opUserName;

    /**
     * 操作类型(A=用户认证|B=业务处理)
     */
    private String opType;

    /**
     * 操作类型名称
     */
    private String opTypeName;

    /**
     * 操作开始
     */
    @TimeUtil.TIME
    private LocalDateTime opBegin;

    /**
     * 操作结束
     */
    @TimeUtil.TIME
    private LocalDateTime opEnd;

    /**
     * 操作API
     */
    private String opApi;

    /**
     * 操作描述
     */
    private String opDesc;

    /**
     * API参数
     */
    private String opArgs;

    /**
     * 处理结果HTTP状态码
     */
    private Integer status;

    /**
     * 处理结果代码或错误信息
     */
    private String result;

}
