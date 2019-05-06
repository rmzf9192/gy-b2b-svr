package com.el.edp.bpm.service;

import com.el.core.cache.CacheName;
import com.el.edp.bpm.api.EdpBpmConfPayload;
import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import com.el.edp.bpm.domain.model.EdpBpmViewSts;
import com.el.edp.bpm.mapper.EdpBpmViewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Profile("bpm")
@Slf4j
@Service
@CacheName("EDP:BPM:model")
@RequiredArgsConstructor
public class EdpBpmFormServiceImpl implements EdpBpmFormService {

    private final EdpBpmViewMapper viewMapper;

    @Override
    public String viewConfOf(String procDefId, String taskDefId, EdpBpmViewMode viewMode) {
        String viewConf = viewMapper.viewConfOf(procDefId, taskDefId, viewMode);
        if (viewConf == null) {
            log.trace("[EDP-BPM] use default view config of p-{}", procDefId);
            viewConf = EdpBpmUtil.loadProcDefViewConf(procDefId, taskDefId, viewMode);
        }
        return viewConf;
    }

    @Override
    public void updateViewConfOf(EdpBpmConfPayload payload) {
        if (null == viewMapper.hasViewConf(payload.getDefId(), payload.getNodeId(), payload.getMode())) {
            viewMapper.addNewViewConf(payload.getDefId(), payload.getNodeId(), payload.getMode(), payload.getConf());
        } else {
            viewMapper.updateViewConf(payload.getDefId(), payload.getNodeId(), payload.getMode(), payload.getConf());
        }
    }

    @Override
    public EdpBpmViewSts viewConfStatusOf(String procDefId) throws IOException {
        val procDefKey = EdpBpmUtil.toProcDefKey(procDefId);
        return EdpBpmViewSts.of(EdpBpmUtil.getViewConfFiles(procDefKey), viewMapper.viewKeysOf(procDefId));
    }

}
