package com.el.dev;

import com.el.core.security.CaptchaUtil;
import com.el.core.security.SecurityApi;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author neo.pan
 * @since 2018/04/09
 */
@Conditional(DevEdsCondition.class)
@Slf4j
@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class DevSecurityApi {

    private static final String API_P_TYPE = "query";
    private static final String P_ENT_CODE = "ent_code";
    private static final String P_LOGIN_NO = "login_no";
    private static final String P_PASSWORD = "password";
    private static final String P_CAPTCHA = "captcha";

    private final SecurityApi api;

    @GetMapping("/captcha")
    public String captchaOf(HttpServletRequest request) {
        return CaptchaUtil.captchaOf(request.getSession());
    }

    @ApiImplicitParams({
        @ApiImplicitParam(
            name = P_ENT_CODE, paramType = API_P_TYPE, required = true, defaultValue = "E001"
        ),
        @ApiImplicitParam(
            name = P_LOGIN_NO, paramType = API_P_TYPE, required = true, defaultValue = "E001"
        ),
        @ApiImplicitParam(
            name = P_PASSWORD, paramType = API_P_TYPE, required = true, defaultValue = "password"
        ),
        @ApiImplicitParam(
            name = P_CAPTCHA, paramType = API_P_TYPE, required = true, defaultValue = "1234"
        ),
    })
    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request) {
        log.trace("[DEV] auth params: {}:{}/{}/{}",
            request.getParameter(P_ENT_CODE), request.getParameter(P_LOGIN_NO),
            request.getParameter(P_PASSWORD), request.getParameter(P_CAPTCHA));
        return api.login(request);
    }

}
