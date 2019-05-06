package com.el.edp.sec.service;

import com.el.core.domain.PagingResult;
import com.el.edp.sec.api.EdpAuditLogQuery;
import com.el.edp.sec.domain.EdpAuditLog;
import com.el.edp.sec.mapper.EdpAuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author neo.pan
 * @since 2018/03/28
 */
@Profile("edp")
@Service
@RequiredArgsConstructor
public class EdpLogServiceImpl implements EdpLogService {

    private final EdpAuditMapper edpAuditMapper;

    @Override
    public PagingResult<EdpAuditLog> auditsBy(EdpAuditLogQuery query) {
        return PagingResult.of(edpAuditMapper.findPage(query), edpAuditMapper.findCount(query));
    }

}
