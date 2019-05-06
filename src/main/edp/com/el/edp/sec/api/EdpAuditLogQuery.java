package com.el.edp.sec.api;

import com.el.core.domain.PagingQuery;
import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author danfeng
 * @since 2018/03/21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EdpAuditLogQuery extends PagingQuery {

    /**
     * 操作者ID
     */
    private Long opUserId;

    /**
     * 操作者名称
     */
    private String opUserName;

    /**
     * 操作时间开始
     */
    @TimeUtil.TIME
    private LocalDateTime opTimeFrom;
    /**
     * 操作时间结束
     */
    @TimeUtil.TIME
    private LocalDateTime opTimeTo;
}
