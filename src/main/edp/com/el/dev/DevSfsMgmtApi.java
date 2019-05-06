package com.el.dev;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 文件服务管理
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Conditional(DevEdsCondition.class)
@Controller
@RequestMapping("/dev/sfm")
@RequiredArgsConstructor
public class DevSfsMgmtApi {

    private final CommonsMultipartResolver multipartResolver;

    @PostMapping("/post/{size}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePostSize(@PathVariable("size") int postSize) {
        multipartResolver.setMaxUploadSize(postSize);
    }

    @PostMapping("/file/{size}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFileSize(@PathVariable("size") int fileSize) {
        multipartResolver.setMaxUploadSizePerFile(fileSize);
    }

}
