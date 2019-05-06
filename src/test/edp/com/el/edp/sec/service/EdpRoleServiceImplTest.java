package com.el.edp.sec.service;

import com.el.edp.EdpTest;
import com.el.edp.sec.mapper.EdpAuthRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author neo.pan
 * @since 2018/04/08
 */
@Slf4j
@ContextConfiguration(classes = EdpRoleServiceImplTest.Config.class)
public class EdpRoleServiceImplTest extends EdpTest {

    @Autowired
    private EdpAuthRoleService roleService;

    @Test
    public void roleBy() {
        log.info("roleBy={}", roleService.roleBy(1L));
    }

    @Test
    public void saveRole() {
    }

    @Test
    public void disableRole() {
    }

    @Test
    public void enableRole() {
    }

    @Configuration
    static class Config {
        @Bean
        public EdpAuthRoleService roleService(EdpAuthRoleMapper roleMapper) {
            return new EdpAuthRoleServiceImpl(roleMapper, null);
        }
    }

}
