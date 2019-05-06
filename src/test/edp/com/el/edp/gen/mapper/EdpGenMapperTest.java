package com.el.edp.gen.mapper;

import com.el.edp.EdpTest;
import com.el.edp.gen.domain.EdpGenDef;
import com.el.edp.gen.domain.EdpGenSeq;
import com.el.edp.gen.domain.EdpGenSeqCnt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/12
 */
@Slf4j
public class EdpGenMapperTest extends EdpTest {

    private static EdpGenDef genDef = new EdpGenDef() {{
        setCode("DEV_PO1");
        setSeqKey("'X001'");
        setCntKey("t('yMMdd')");
        setCntMin(1L);
        setCntMax(999L);
    }};

    private static EdpGenSeq genSeq() {
        return new EdpGenSeq(genDef, 10, null);
    }

    @Autowired
    private EdpGenMapper genMapper;

    @Test
    public void genDefs() {
        log.info("gens={}", genMapper.genDefs(1, "SRM_SVR"));
    }

    @Test
    public void getCntAndLockSeq() {
        assertThat(genMapper.getCntAndLockSeq(genSeq()), nullValue());
    }

    @Test
    public void updateSeq() {
        genSeq().updateAndCheckCntVal(EdpGenSeqCnt.of("20180413", 1L));
        assertThat(genMapper.updateCnt(genSeq()), equalTo(0));
    }

    @Test
    public void lockDef() {
        assertThat(genMapper.lockDef(genDef.getCode()), equalTo(1));
    }

    @Test
    public void addNewSeq() {
        assertThat(genMapper.addNewSeq(genSeq()), equalTo(1));
    }

}
