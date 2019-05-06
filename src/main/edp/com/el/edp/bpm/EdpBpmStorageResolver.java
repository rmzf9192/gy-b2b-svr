package com.el.edp.bpm;

import com.el.core.util.HashUtil;
import com.el.edp.bpm.config.BpmStorageResolver;

/**
 * @author neo.pan
 * @since 17/6/5
 */
public class EdpBpmStorageResolver implements BpmStorageResolver {

    private static final String REPO_FLOW_ROOT = "_tmp__flow_";

    @Override
    public String repoNameOf(String depName) {
        return REPO_FLOW_ROOT + HashUtil.md5(depName.getBytes());
    }

}
