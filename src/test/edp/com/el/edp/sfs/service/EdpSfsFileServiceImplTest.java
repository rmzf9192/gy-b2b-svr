package com.el.edp.sfs.service;

import com.el.edp.EdpSpringTest;
import com.el.edp.sfs.domain.EdpSfsProperties;
import com.el.edp.sfs.domain.EdpSfsRepo;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.util.EdpIOUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
@Slf4j
@ContextConfiguration(
    classes = {EdpSfsFileServiceImplTest.Config.class, EdpSfsFileServiceImpl.class}
)
public class EdpSfsFileServiceImplTest extends EdpSpringTest {

    @Autowired
    private EdpSfsFileService fileService;

    private static final String mockRepoCode = "dev01";
    private static final EdpSfsRepo mockRepo = new EdpSfsRepo() {{
        setCode(mockRepoCode);
    }};
    private static final EdpSfsRepoDir mockRepoDir =
        EdpSfsRepoDir.of(toTestPath(""), mockRepoCode, "hello/world", "1");
    private static final MultipartFile mockUploadFile = new MockUploadFile();

    @Test
    public void createFileItem() throws IOException {
        val expected = "EdpSfsRepoItem(itemKey=EdpSfsRepoItemKey(" +
            "super=EdpSfsRepoDir(repoCode=dev01, clsPath=hello/world, dataKey=1, tempItem=N), itemHash=52f83ff6877e42f613bcd2444c22528c" +
            "), itemName=Hello.txt, itemSize=6, itemMime=text/plain)";
        val actual = fileService.createFileItem(mockRepoDir, mockUploadFile);
        assertThat(actual.toString(), equalTo(expected));
        val actualFile = Paths.get("build/dev01/hello/world/" + actual.getItemKey().getItemHash());
        assertThat(Files.size(actualFile), equalTo(actual.getItemSize()));
    }

    @Test
    public void createEmbedItem() throws IOException {
        val expected = "EdpSfsRepoItem(itemKey=EdpSfsRepoItemKey(" +
            "super=EdpSfsRepoDir(repoCode=dev01, clsPath=hello/world, dataKey=1, tempItem=N), itemHash=52f83ff6877e42f613bcd2444c22528c" +
            "), itemName=Hello.txt, itemSize=6, itemMime=text/plain)";
        val actual = fileService.createEmbedItem(mockRepoDir, mockUploadFile);
        assertThat(actual.toString(), equalTo(expected));
        assertThat((long) actual.getItemBlob().length, equalTo(actual.getItemSize()));
    }

    @Configuration
    public static class Config {
        @Bean
        public EdpSfsProperties sfsProperties() {
            EdpSfsProperties sfsProperties = new EdpSfsProperties();
            sfsProperties.setRoot("build");
            return sfsProperties;
        }
    }

    private static class MockUploadFile implements MultipartFile {

        private final static Path file = EdpIOUtil.classpathToPath("dev/Hello.txt");

        @Override
        public String getName() {
            return "file";
        }

        @Override
        public String getOriginalFilename() {
            return file.getFileName().toString();
        }

        @Override
        public String getContentType() {
            return "text/plain";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 6;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return Files.readAllBytes(file);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return Files.newInputStream(file);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            try (val outputStream = new FileOutputStream(dest)) {
                Files.copy(file, outputStream);
            }
        }
    }

}
