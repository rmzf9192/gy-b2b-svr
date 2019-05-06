package com.el.edp.sfs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author neo.pan
 * @since 2018/04/17
 */
@Slf4j
@Getter
@ConfigurationProperties("sfs")
public class EdpSfsProperties {

    /**
     * 系统存储路径
     */
    private Path rootPath;

    /**
     * @param root 资料库根路径
     */
    public void setRoot(String root) {
        rootPath = Paths.get(root);
    }

    /**
     * 最大上传字节数
     */
    @Setter
    private Long maxPostSize;
    /**
     * 最大上传文件字节数
     */
    @Setter
    private Long maxFileSize;

    /**
     * 文件是否做同名覆盖
     */
    @Setter
    private boolean overwrite;

    /**
     * 加密的密钥(为空时表示不加密)
     */
    @Setter
    private String secret;

}
