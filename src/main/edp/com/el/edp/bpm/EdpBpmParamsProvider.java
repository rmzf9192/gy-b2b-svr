package com.el.edp.bpm;

import com.el.edp.bpm.config.BpmParam;
import com.el.edp.bpm.config.BpmParamsProvider;
import com.el.edp.bpm.config.BpmUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;

/**
 * @author neo.pan
 * @since 17/6/8
 */
@Slf4j
public class EdpBpmParamsProvider implements BpmParamsProvider {

    private Map<String, List<BpmParam>> profiles = new HashMap<>();

    @Override
    public List<BpmParam> params(String defId) {
        val defKey = BpmUtil.toProcessDefinitionKey(defId);
        log.debug("[EDP-BPM] defIds: {} -> {}", profiles.keySet(), defKey);
        return profiles.getOrDefault(defKey, Collections.emptyList());
    }

    /**
     * MOCK DATA REGISTERATION
     */
    public void registerParam(String defKey, BpmParam param) {
        profiles.computeIfAbsent(defKey, key -> new ArrayList<>()).add(param);
    }

}
