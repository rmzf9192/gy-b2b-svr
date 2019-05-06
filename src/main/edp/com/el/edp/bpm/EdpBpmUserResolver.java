package com.el.edp.bpm;

import com.el.cfg.security.EdpPrincipalService;
import com.el.edp.bpm.config.BpmUserResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class EdpBpmUserResolver implements BpmUserResolver {

    private final EdpPrincipalService principalService;

    @Override
    public String user() {
        return userOf(principalService.userId());
    }

    @Override
    public Stream<String> groups() {
        return null;
    }
}
