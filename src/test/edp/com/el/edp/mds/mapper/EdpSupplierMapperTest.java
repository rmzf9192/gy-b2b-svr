package com.el.edp.mds.mapper;

import com.el.edp.EdpTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/05/03
 */
@Slf4j
public class EdpSupplierMapperTest extends EdpTest {

    @Autowired
    private EdpSupplierMapper supplierMapper;

    @Test
    public void suppliers() {
        log.info("[TEST] suppliers={}", supplierMapper.suppliers("1"));
    }
}
