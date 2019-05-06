package com.el.edp.sfs.domain;

/**
 * @author neo.pan
 * @see <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types">完整的MIME类型列表</a>
 * @since 2018/04/19
 */
@FunctionalInterface
public interface EdpSfsMimeMatcher {

    /**
     * @param mimeType MIME类型(例: text/plain)
     * @return true if matched
     */
    boolean match(String mimeType);

    EdpSfsMimeMatcher txt = mimeType -> mimeType.equals("text/plain");
    EdpSfsMimeMatcher htm = mimeType -> mimeType.equals("text/html");
    EdpSfsMimeMatcher img = mimeType -> mimeType.startsWith("image/");
    EdpSfsMimeMatcher aud = mimeType -> mimeType.startsWith("audio/");
    EdpSfsMimeMatcher vid = mimeType -> mimeType.startsWith("video/");
    EdpSfsMimeMatcher xml = mimeType -> mimeType.endsWith("xml");
    EdpSfsMimeMatcher pdf = mimeType -> mimeType.endsWith("pdf");
    EdpSfsMimeMatcher xls = mimeType -> mimeType.endsWith("excel");
    EdpSfsMimeMatcher doc = mimeType -> mimeType.endsWith("word");
    EdpSfsMimeMatcher ppt = mimeType -> mimeType.endsWith("powerpoint");

}
