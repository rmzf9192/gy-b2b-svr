package com.el.edp.sec.service;

import com.el.core.domain.PagingResult;
import com.el.edp.sec.api.EdpAuditLogQuery;
import com.el.edp.sec.domain.EdpAuditLog;

/**
 * @author neo.pan
 * @since 2018/03/28
 */
public interface EdpLogService {

    /**
     * @param query 查询条件
     * @return 分页审计日志
     */
    PagingResult<EdpAuditLog> auditsBy(EdpAuditLogQuery query);
}
