package com.el.edp.bpm.api;

import com.el.core.web.WebUtil;
import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import com.el.edp.bpm.domain.model.EdpBpmViewSts;
import com.el.edp.bpm.service.EdpBpmFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Profile("bpm")
@Slf4j
@RestController
@RequestMapping("/edp/bpm/form")
@RequiredArgsConstructor
public class EdpBpmFormApi {

    private final EdpBpmFormService formService;

    @GetMapping(path = "/config", produces = "application/json; charset=UTF-8")
    public String viewConfOf(
        @RequestParam("defId") String procDefId, @RequestParam("nodeId") String taskDefId,
        @RequestParam("mode") EdpBpmViewMode viewMode) {
        return formService.viewConfOf(procDefId, taskDefId, viewMode);
    }

    @PostMapping("/config")
    public ResponseEntity updateViewConfOf(@Validated @RequestBody EdpBpmConfPayload payload) {
        formService.updateViewConfOf(payload);
        return WebUtil.toResponseEntity();
    }

    @GetMapping("/config/status")
    public EdpBpmViewSts viewConfStatusOf(@RequestParam("defId") String procDefId) throws IOException {
        return formService.viewConfStatusOf(procDefId);
    }

}
