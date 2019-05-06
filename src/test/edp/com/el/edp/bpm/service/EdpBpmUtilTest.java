package com.el.edp.bpm.service;

import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Slf4j
public class EdpBpmUtilTest {

    private static final String PROC_DEF_KEY = "SuppliperItem";
    private static final String PROC_DEF_ID = "SuppliperItem:1:87323";
    private static final String TASK_DEF_ID = "examineByManager";

    @Test
    public void loadProcDefViewConf() {
        int confSize = EdpBpmUtil.loadProcDefViewConf(
            PROC_DEF_ID, TASK_DEF_ID, EdpBpmViewMode.create).length();
        log.info("loadProcDefViewConf.size={}", confSize);
    }

    @Test
    public void toProcDefKey() {
        val expected = "SuppliperItem";
        val actual = EdpBpmUtil.toProcDefKey(PROC_DEF_ID);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getViewConfFiles() throws IOException {
        log.info("getViewConfFiles={}", EdpBpmUtil.getViewConfFiles(PROC_DEF_KEY));
    }

}
