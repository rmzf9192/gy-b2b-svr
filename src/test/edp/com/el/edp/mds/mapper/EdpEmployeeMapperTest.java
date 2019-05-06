package com.el.edp.mds.mapper;

import com.el.edp.EdpTest;
import com.el.edp.mds.api.EdpEmployeeQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Slf4j
public class EdpEmployeeMapperTest extends EdpTest {

    @Autowired
    private EdpEmployeeMapper employeeMapper;

    private EdpEmployeeQuery query = new EdpEmployeeQuery() {{
       setEmpCode("test");
       setEmpName("test");
    }};

    @Test
    public void employeesBy() {
        log.info("[TEST] employeesBy={}", employeeMapper.employeesBy(query));
    }

    @Test
    public void employeeCountBy() {
        log.info("[TEST] employeeCountBy={}", employeeMapper.employeeCountBy(query));
    }

    @Test
    public void employeeBy() {
        log.info("[TEST] employeeBy={}", employeeMapper.employeeBy(1));
    }
}
