package com.el.b2b.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hongtai.qin
 * @since 2018/08/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "basicDataSync")
public class BasicDataSyncDomain {

    private String api;
    private String prefix;
    private String suffix;
}
