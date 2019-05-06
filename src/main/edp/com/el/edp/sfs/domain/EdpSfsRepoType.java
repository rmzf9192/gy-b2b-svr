package com.el.edp.sfs.domain;

import com.el.core.domain.CodeName;
import lombok.Getter;

/**
 * @author neo.pan
 * @since 2018/04/18
 */
@Getter
public enum EdpSfsRepoType implements CodeName, EdpSfsMimeMatcher {

    any("任意") {
        @Override
        public boolean match(String mimeType) {
            return true;
        }
    }, img("图片") {
        @Override
        public boolean match(String mimeType) {
            return img.match(mimeType);
        }
    }, aud("音频") {
        @Override
        public boolean match(String mimeType) {
            return aud.match(mimeType);
        }
    }, vid("视频") {
        @Override
        public boolean match(String mimeType) {
            return vid.match(mimeType);
        }
    },;

    @Override
    public String getCode() {
        return name();
    }

    private String name;

    EdpSfsRepoType(String name) {
        this.name = name;
    }

}
