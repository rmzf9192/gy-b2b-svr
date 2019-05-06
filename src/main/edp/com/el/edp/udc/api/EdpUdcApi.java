package com.el.edp.udc.api;

import com.el.core.udc.UdcManager;
import com.el.core.udc.UdcService;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 18/3/16
 */
@Profile("edp")
@Slf4j
@RestController
@RequestMapping("/edp/udcs")
@RequiredArgsConstructor
public class EdpUdcApi {

    private final UdcManager<EdpUdc> udcManager;
    private final UdcService<EdpUdc, EdpUdcItem> udcService;

    @GetMapping("/{udc}/items")
    public List<EdpUdcItem> udcItems(@PathVariable String udc) {
        return udcService.udcItems(udcManager.resolve(udc));
    }
}
