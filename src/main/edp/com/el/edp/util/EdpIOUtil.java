package com.el.edp.util;

import com.el.core.DevError;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
@Slf4j
public abstract class EdpIOUtil {

    /**
     * EDP缺省字符集
     */
    public static final Charset EDP_CHARSET = StandardCharsets.UTF_8;

    /**
     * 文件清理者
     */
    private static final SimpleFileVisitor<Path> fileCleaner = new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult postVisitDirectory(
            Path dir, IOException exc) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(
            Path file, BasicFileAttributes attrs)
            throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
        }
    };

    /**
     * 删除目录及其内容
     *
     * @param directory 目录路径
     * @throws IOException IO异常发生
     */
    public static void deleteDirectory(Path directory) throws IOException {
        Files.walkFileTree(directory, fileCleaner);
    }

    /**
     * @param fullName 文件名
     * @return 扩展名(小写)
     */
    public static String extensionOf(String fullName) {
        int dotAt = fullName.lastIndexOf('.');
        return dotAt == -1 ? "" : fullName.substring(dotAt + 1).toLowerCase();
    }

    /**
     * @param path   路径
     * @param suffix 后缀
     * @return 给路径添加后缀
     */
    public static Path appendSuffix(Path path, String suffix) {
        return path.resolveSibling(path.getFileName().toString() + suffix);
    }

    /**
     * The classpath root URL of of application's class loader.
     */
    private static final Path CLASSPATH_ROOT;

    static {
        val classLoader = EdpIOUtil.class.getClassLoader();
        val classpathURL = Optional.ofNullable(classLoader.getResource("."))
            .orElseThrow(DevError::unexpected);
        try {
            CLASSPATH_ROOT = Paths.get(classpathURL.toURI());
        } catch (URISyntaxException e) {
            throw DevError.unexpected("[EDP-IO] classpath resource's URI parsed error: " + classpathURL);
        }
    }

    /**
     * @param path classpath下的路径
     * @return 系统路径
     */
    public static Path classpathToPath(String path) {
        return CLASSPATH_ROOT.resolve(path);
    }

    /**
     * 使指定目录可用(即目录不存在时创建该目录及其父级目录)
     *
     * @param path 目录绝对路径
     * @return input-chain
     */
    public static Path ensureDirectory(Path path) {
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
                log.trace("[EDP-IO] directory CREATED: {}", path);
            } catch (FileAlreadyExistsException e) {
                try {
                    Files.delete(path);
                    Files.createDirectory(path);
                } catch (IOException e1) {
                    log.error("[EDP-IO] create directory FAIL: {} - {}", path, e.getMessage());
                }
            } catch (IOException e) {
                log.error("[EDP-IO] create directory FAIL: {} - {}", path, e.getMessage());
            }
        }
        return path;
    }

    private static final String SA_TMP_DIR = EdpIOUtil.class.getName() + ".tmpdir";

    /**
     * 在HTTP会话上下文中获取或创建临时工作目录
     *
     * @param session HTTP会话
     * @return 临时工作目录路径
     * @throws IOException IO异常发生
     */
    public static Path ensureTempDirectory(HttpSession session) throws IOException {
        Path path;
        val tmpDir = (String) session.getAttribute(SA_TMP_DIR);
        if (tmpDir == null) {
            path = Files.createTempDirectory(null);
            session.setAttribute(SA_TMP_DIR, path.toString());
        } else {
            path = Paths.get(tmpDir);
        }
        return path;
    }

    /**
     * 获取会话上下文中的文件存储路径
     *
     * @param session 会话上下文
     * @return 文件存储路径
     */
    public static Optional<Path> getTempDirectory(HttpSession session) {
        val tmpDir = (String) session.getAttribute(SA_TMP_DIR);
        return tmpDir == null ? Optional.empty() : Optional.of(Paths.get(tmpDir));
    }

    private static final int IO_BUF_SIZE = 8192;

    /**
     * @param inputStream  输入流
     * @param outputStream 输出流
     * @return MD5 散列
     * @throws IOException IO异常发生
     */
    public static String copyAndGetMd5(
        InputStream inputStream, OutputStream outputStream) throws IOException {
        val digest = DigestUtils.getMd5Digest();
        byte[] buffer = new byte[IO_BUF_SIZE];
        int read = inputStream.read(buffer);
        while (read > -1) {
            digest.update(buffer, 0, read);
            outputStream.write(buffer, 0, read);
            read = inputStream.read(buffer);
        }
        return Hex.encodeHexString(digest.digest());
    }

}
