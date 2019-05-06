package com.el.edp.bpm.config;

/**
 * @author neo.pan
 * @since 17/6/5
 */
public interface BpmStorageResolver {

    /**
     * @param depName 部署名
     * @return 流程仓库名
     */
    String repoNameOf(String depName);
}
