package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author neo.pan
 * @since 2018/04/26
 */
@Slf4j
public class EdpBpmModelMapperTest extends EdpTest {

    private static final String PROC_DEF_KEY = "undefinedProc";

    @Autowired
    private EdpBpmModelMapper modelMapper;

    @Test
    public void modelCrud() throws IOException {
        log.info("addNewModel={}", modelMapper.addNewModel(PROC_DEF_KEY, "<xml><foo></foo></xml>"));
        log.info("hasModel={}", modelMapper.hasModel(PROC_DEF_KEY));
        log.info("modelOf={}", modelMapper.modelOf(PROC_DEF_KEY));
        try (val r = modelMapper.modelReaderOf(PROC_DEF_KEY)) {
            log.info("modelReaderOf={}", r == null ? null : IOUtils.toString(r));
        }
        log.info("updateModel={}", modelMapper.updateModel(PROC_DEF_KEY, "<xml><bar></bar></xml>"));
        log.info("deleteModel={}", modelMapper.deleteModel(PROC_DEF_KEY));
    }

}
