package com.el.edp.sfs.domain;

import lombok.Setter;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
public class EdpSfsWebItem {

    private static final String WORK_PATH_PREFIX = "work:";

    /**
     * 资料库路径(例如: dev01、work:dev01)
     */
    private final String repoPath;

    public boolean isWorkRepo() {
        return repoPath.startsWith(WORK_PATH_PREFIX);
    }

    /**
     * 资料目录路径
     */
    private final String itemPath;

    private EdpSfsWebItem(String repoPath, String itemPath) {
        this.repoPath = repoPath;
        this.itemPath = itemPath;
    }

    public static EdpSfsWebItem of(String repoCode, String itemPath) {
        return new EdpSfsWebItem(repoCode, itemPath);
    }

    public static EdpSfsWebItem workOf(String repoCode, String itemPath) {
        return new EdpSfsWebItem(WORK_PATH_PREFIX + repoCode, itemPath);
    }

    @Setter
    private String webPath;

    public String toWebPath() {
        return webPath + "/" + repoPath + "/" + itemPath;
    }

}
