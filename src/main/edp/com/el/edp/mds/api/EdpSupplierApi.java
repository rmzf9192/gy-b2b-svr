package com.el.edp.mds.api;

import com.el.edp.mds.mapper.EdpSupplierMapper;
import com.el.edp.util.EdpCodeName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@RestController
@RequestMapping("/edp")
@RequiredArgsConstructor
public class EdpSupplierApi {

    private final EdpSupplierMapper supplierMapper;

    @GetMapping("/supps")
    public List<EdpCodeName> suppliers(EdpSupplierQuery query) {
        return supplierMapper.suppliers(query.getKey());
    }
}
