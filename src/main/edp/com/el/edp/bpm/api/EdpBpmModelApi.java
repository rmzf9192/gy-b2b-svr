package com.el.edp.bpm.api;

import com.el.core.web.WebUtil;
import com.el.edp.bpm.domain.model.EdpBpmServiceTaskDef;
import com.el.edp.bpm.domain.model.EdpBpmUserTaskDef;
import com.el.edp.bpm.service.EdpBpmModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Simon.Hu
 * @since 17/8/8
 */
@Profile("bpm")
@Slf4j
@RestController
@RequestMapping("/edp/bpm/model")
@RequiredArgsConstructor
public class EdpBpmModelApi {

    private final EdpBpmModelService modelService;

    @GetMapping("/xml")
    public void loadModelXml(@RequestParam("defId") String procDefId,
                             HttpServletResponse response) throws IOException {
        response.setContentType("application/xml; charset=UTF-8");
        try (val outputStream = response.getOutputStream()) {
            modelService.loadModelXml(procDefId, outputStream);
        }
    }

    @PostMapping("/xml")
    public ResponseEntity saveModelXml(@Validated @RequestBody EdpBpmModelPayload payload) throws IOException {
        modelService.saveModelXml(payload);
        return WebUtil.toResponseEntity();
    }

    @GetMapping("/user-tasks")
    public List<EdpBpmUserTaskDef> userTaskDefsOf(@RequestParam("defId") String procDefId) throws IOException {
        return modelService.userTaskDefsOf(procDefId);
    }

    @GetMapping("/service-tasks")
    public List<EdpBpmServiceTaskDef> serviceTaskDefsOf(@RequestParam("defId") String procDefId) throws IOException {
        return modelService.serviceTaskDefsOf(procDefId);
    }

}
