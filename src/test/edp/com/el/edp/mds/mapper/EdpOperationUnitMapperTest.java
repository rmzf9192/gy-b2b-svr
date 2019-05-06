package com.el.edp.mds.mapper;

import com.el.edp.EdpTest;
import com.el.edp.mds.api.EdpOperationUnitQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Slf4j
public class EdpOperationUnitMapperTest extends EdpTest {

    @Autowired
    private EdpOperationUnitMapper operationUnitMapper;

    private EdpOperationUnitQuery query = new EdpOperationUnitQuery() {{
        setOuCode("test");
        setOuName("test");
    }};

    @Test
    public void operationUnitsBy() {
        log.info("[TEST] operationUnitsBy={}", operationUnitMapper.operationUnitsBy(query));
    }

    @Test
    public void operationUnitCountBy() {
        log.info("[TEST] operationUnitCountBy={}", operationUnitMapper.operationUnitCountBy(query));
    }

    @Test
    public void operationUnitBy() {
        log.info("[TEST] operationUnitBy={}", operationUnitMapper.operationUnitBy(1));
    }

    @Test
    public void operationUnits() {
        log.info("[TEST] operationUnits={}", operationUnitMapper.operationUnits("1"));
    }
}
