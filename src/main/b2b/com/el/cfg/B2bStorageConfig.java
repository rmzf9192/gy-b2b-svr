package com.el.cfg;

import com.el.core.storage.FileSystemStorageService;
import com.el.core.storage.StorageProperties;
import com.el.core.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author neo.pan
 * @since 17/10/8
 */
@Profile("b2b")
@Slf4j
@Configuration
@EnableConfigurationProperties({StorageProperties.class})
public class B2bStorageConfig {

    @Bean
    public StorageService storageService(StorageProperties storageProperties) {
        log.info("[CORE-STOR] storageService");
        return new FileSystemStorageService(storageProperties);
    }

}
