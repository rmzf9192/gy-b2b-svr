package com.el.cfg;

import com.el.edp.sfs.domain.EdpSfsProperties;
import com.el.edp.sfs.mapper.EdpSfsMapper;
import com.el.edp.sfs.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author neo.pan
 * @since 17/10/8
 */
@Profile("edp")
@Slf4j
@Configuration
@EnableConfigurationProperties({EdpSfsProperties.class, MultipartProperties.class})
public class EdpSfsConfig {

    /**
     * {@link CommonsMultipartResolver#resolveMultipart(HttpServletRequest)}
     *
     * @return multipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(MultipartProperties multipartProperties) throws IOException {
        log.info("[EDP-SFS] multipartResolver: {}", multipartProperties);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource(multipartProperties.getLocation()));
        multipartResolver.setResolveLazily(true);
        return multipartResolver;
    }

    @Bean
    public EdpSfsRepoService edpSfsRepoService(final EdpSfsMapper sfsMapper) {
        log.info("[EDP-SFS] edpSfsRepoService");
        return new EdpSfsRepoServiceImpl(sfsMapper);
    }

    @Bean
    public EdpSfsFileService edpSfsFileService() {
        log.info("[EDP-SFS] edpSfsFileService");
        return new EdpSfsFileServiceImpl();
    }

    @Bean
    public EdpSfsTempDirCleaner edpSfsTempDirCleaner(final EdpSfsMapper sfsMapper) {
        log.info("[EDP-SFS] edpSfsTempDirCleaner");
        return new EdpSfsTempDirCleaner(sfsMapper);
    }

}
