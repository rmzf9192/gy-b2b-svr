package com.el.edp.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
@Slf4j
public class EdpIOUtilTest {

    @Test
    public void extensionOf() {
        val actual = EdpIOUtil.extensionOf("dev/Hello.TXT");
        val expected = "txt";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void appendSuffix() {
        val path = Paths.get("dev/Hello");
        val actual = EdpIOUtil.appendSuffix(path, "_suffix");
        val expected = Paths.get("dev/Hello_suffix");
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void classpathToPath() {
        log.info(EdpIOUtil.classpathToPath("dev/Hello.txt").toString());
    }

    @Test
    public void classpathToPath_not_found() {
        val isPresent = Files.exists(EdpIOUtil.classpathToPath("dev/not_found.txt"));
        assertThat(isPresent, equalTo(false));
    }

    @Test
    public void copyAndGetMd5() throws IOException {
        val inFile = EdpIOUtil.classpathToPath("dev/Hello.txt");
        val outFile = inFile.resolveSibling("World.txt");
        try (val is = Files.newInputStream(inFile);
             val os = Files.newOutputStream(outFile)) {
            log.info(EdpIOUtil.copyAndGetMd5(is, os));
        }
    }

    @Test
    public void ensureTempDirectory() throws IOException {
        val actual = EdpIOUtil.ensureDirectory(Files.createTempDirectory("dev01").resolve("a/b"));
        log.info("ensureTempDirectory={}", actual);
    }

}
