package com.el.edp.bpm.mapper;

import com.el.edp.EdpTest;
import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
@Slf4j
public class EdpBpmViewMapperTest extends EdpTest {

    private static final String PROC_DEF_ID = "SuppliperItem:1:87323";
    private static final String TASK_DEF_ID = "examineByManager";

    @Autowired
    private EdpBpmViewMapper viewMapper;

    @Test
    public void viewKeysOf() {
        log.info("viewKeysOf={}", viewMapper.viewKeysOf(PROC_DEF_ID));
    }

    @Test
    public void viewConfCrud() {
        int rowsAffected = viewMapper.addNewViewConf(PROC_DEF_ID, TASK_DEF_ID, EdpBpmViewMode.create,
            "{\"name\":\"I'm a JSON.\"}");
        assertThat(rowsAffected, equalTo(1));
        Integer hasViewConf = viewMapper.hasViewConf(PROC_DEF_ID, TASK_DEF_ID, EdpBpmViewMode.create);
        assertThat(hasViewConf, equalTo(1));
        String viewConf = viewMapper.viewConfOf(PROC_DEF_ID, TASK_DEF_ID, EdpBpmViewMode.create);
        assertThat(viewConf, equalTo("{\"name\":\"I'm a JSON.\"}"));
        rowsAffected = viewMapper.updateViewConf(PROC_DEF_ID, TASK_DEF_ID, EdpBpmViewMode.create,
            "{\"name\":\"I'm a JSON, too.\"}");
        assertThat(rowsAffected, equalTo(1));
    }

}
