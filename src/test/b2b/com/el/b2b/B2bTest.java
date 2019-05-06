package com.el.b2b;

import com.el.cfg.EdpJdbcConfig;
import com.el.cfg.B2bJdbcConfig;
import com.el.core.jdbc.JdbcConfig;
import com.el.edp.EdpTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neo.pan
 * @since 2018/3/22
 */
@ActiveProfiles({"edp", "b2b", "test"})
@RunWith(SpringRunner.class)
@ContextConfiguration(
    initializers = {ConfigFileApplicationContextInitializer.class},
    classes = {B2bJdbcConfig.class, EdpTest.Config.class, EdpJdbcConfig.class, JdbcConfig.class}
)
@Transactional
@Rollback
public abstract class B2bTest {
}
