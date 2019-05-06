package com.el.edp;

import com.el.edp.util.EdpIOUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;

/**
 * @author neo.pan
 * @since 2018/3/22
 */
@ActiveProfiles({"edp", "test"})
@RunWith(SpringRunner.class)
@ContextConfiguration(
    initializers = {ConfigFileApplicationContextInitializer.class}
)
public abstract class EdpSpringTest {

    protected static Path toTestPath(String pathUnderDevRoot) {
        return EdpIOUtil.classpathToPath("dev")
            .resolve(pathUnderDevRoot);
    }

}
