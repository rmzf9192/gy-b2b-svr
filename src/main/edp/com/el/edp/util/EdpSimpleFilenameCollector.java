package com.el.edp.util;

import lombok.RequiredArgsConstructor;

/**
 * @author neo.pan
 * @since 2018/04/26
 */
@RequiredArgsConstructor(staticName = "of")
public class EdpSimpleFilenameCollector extends EdpFilenameCollector {

    /**
     * 文件名前缀
     */
    private final String filenamePrefix;

    /**
     * 文件名前缀
     */
    private final String filenameSuffix;

    @Override
    protected boolean match(String filename) {
        return filename.startsWith(filenamePrefix) && filename.endsWith(filenameSuffix);
    }

}
