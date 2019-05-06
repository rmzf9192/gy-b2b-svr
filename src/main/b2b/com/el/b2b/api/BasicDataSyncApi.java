package com.el.b2b.api;

import com.el.b2b.domain.BasicDataSyncDomain;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author hongtai.qin
 * @since 2018/08/29
 */
@Slf4j
@Profile("b2b")
@RequestMapping("/api/basicDataSync")
@AllArgsConstructor
@RestController
public class BasicDataSyncApi {

    @Autowired
    private BasicDataSyncDomain basicDataSyncDomain;

    @PostMapping("/sync/{item}")
    public ResultUtil basicDataSync(@PathVariable("item") String item) {
        log.debug("[BASICDATASYNC-API] param item: {}", item);
        // 拼接请求URL
        String api = basicDataSyncDomain.getApi();
        String prefix = basicDataSyncDomain.getPrefix();
        String suffix = basicDataSyncDomain.getSuffix();
        String reqURL = api + prefix + item + suffix;
        // 发送请求
        RestTemplate template = new RestTemplate();
        ResponseEntity responseEntity = template.postForEntity(reqURL, null, Object.class);
        if(HttpStatus.OK == responseEntity.getStatusCode()){
            return ResultUtil.success();
        }
        return  ResultUtil.fail();
    }
}
