package com.el.edp.sec.mapper;

import com.el.edp.EdpTest;
import com.el.edp.sec.api.EdpOrganizationQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Slf4j
public class EdpOrganizationMapperTest extends EdpTest {

    @Autowired
    private EdpOrganizationMapper organizationMapper;

    private EdpOrganizationQuery query = new EdpOrganizationQuery() {{
        setOrgCode("test");
        setOrgName("test");
    }};

    @Test
    public void organizationsBy() {
        log.info("[TEST] organizationsBy={}", organizationMapper.organizationsBy(query));
    }

    @Test
    public void organizationCountBy() {
        log.info("[TEST] organizationCountBy={}", organizationMapper.organizationCountBy(query));
    }

    @Test
    public void organizationBy() {
        log.info("[TEST] organizationBy={}", organizationMapper.organizationBy(1));
    }
}
