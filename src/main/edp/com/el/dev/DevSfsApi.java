package com.el.dev;

import com.el.core.storage.StorageUtil;
import com.el.core.web.WebUtil;
import com.el.edp.sfs.domain.EdpSfsProperties;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.sfs.service.EdpSfsFileService;
import com.el.edp.sfs.service.EdpSfsRepoService;
import com.el.edp.util.EdpIOUtil;
import com.el.edp.util.EdpOpException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 文件服务管理
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Conditional(DevEdsCondition.class)
@Slf4j
@Controller
@RequestMapping("/dev/sfs")
@RequiredArgsConstructor
public class DevSfsApi {

    /**
     * 业务处理中文件处理的上下文信息
     *
     * @author neo.pan
     * @since 2018/05/02
     */
    @Value(staticConstructor = "of")
    private static class Context {
        /**
         * HTTP会话
         */
        private HttpSession session;
        /**
         * 资料库代码
         */
        private String repoCode;
        /**
         * 资料目录路径
         */
        private String clsPath;
    }

    private final EdpSfsProperties sfsProperties;
    private final EdpSfsRepoService repoService;
    private final EdpSfsFileService fileService;

    @PostMapping("/upload")
    public void upload(
        HttpSession session, @RequestParam("file") MultipartFile[] files,
        @RequestParam(name = "key", required = false) String dataKey,
        @RequestParam(name = "r", required = false, defaultValue = "dev01") String repoCode,
        @RequestParam(name = "p", required = false, defaultValue = "item/c3/") String clsPath) throws IOException {

        val ctx = Context.of(session, repoCode, clsPath);
        upload(ctx, dataKey, files);
    }

    private void upload(Context ctx, String dataKey, MultipartFile[] files) throws IOException {
        val repo = repoService.repoOf(ctx.repoCode);
        fileService.checkFiles(repo, files);
        val repoDir = StringUtils.hasText(dataKey)
            ? EdpSfsRepoDir.of(sfsProperties.getRootPath(), ctx.repoCode, ctx.clsPath, dataKey)
            : EdpSfsRepoDir.of(EdpIOUtil.ensureTempDirectory(ctx.session), ctx.repoCode, ctx.clsPath, dataKey);
        val items = fileService.createItems(repo, repoDir, files);
        repoService.saveItems(items);
        log.trace("[EDP-SFS] {} items SAVED into {}", items.size(), repoDir.getRepoDirPath());
    }

    @PostMapping("/commit")
    public void uploadCommit(
        HttpSession session, @RequestParam("key") String dataKey,
        @RequestParam(name = "r", required = false, defaultValue = "dev01") String repoCode,
        @RequestParam(name = "p", required = false, defaultValue = "item/c3/") String clsPath) {

        val ctx = Context.of(session, repoCode, clsPath);
        uploadCommit(ctx, dataKey);
    }

    private void uploadCommit(Context ctx, String dataKey) {
        EdpIOUtil.getTempDirectory(ctx.session).ifPresent(tempRootPath -> {
            val tempDir = EdpSfsRepoDir.of(tempRootPath, ctx.repoCode, ctx.clsPath, "");
            val repoDir = EdpSfsRepoDir.of(sfsProperties.getRootPath(), ctx.repoCode, ctx.clsPath, dataKey);
            val tempDirPath = tempDir.getRepoDirPath();
            val repoDirPath = repoDir.getRepoDirPath();
            try {
                Files.move(tempDirPath, repoDirPath);
            } catch (IOException e) {
                log.warn("[EDP-SFS] commit directory FAILED: {} -> {}", tempDirPath, repoDirPath);
                return;
            }
            try {
                repoService.commitTempItems(tempDir, dataKey);
            } catch (Throwable e) {
                log.warn("[EDP-SFS] commit INFO of directory FAILED: {} -> {}", tempDir, dataKey);
                try {
                    EdpIOUtil.deleteDirectory(repoDirPath);
                } catch (IOException ee) {
                    log.warn("[EDP-SFS] CANNOT clean directory, please remove it manually: {}", repoDirPath);
                }
            }
        });
    }

    @GetMapping("/download/{key}/{hash}")
    public ResponseEntity<Resource> download(
        @PathVariable("key") String dataKey, @PathVariable("hash") String itemHash,
        @RequestParam(name = "s", required = false) String suffix,
        @RequestParam(name = "r", required = false, defaultValue = "dev01") String repoCode,
        @RequestParam(name = "p", required = false, defaultValue = "item/c3/") String clsPath) {
        val repoDir = EdpSfsRepoDir.of(sfsProperties.getRootPath(), repoCode, clsPath, dataKey);
        Path itemPath = repoDir.getRepoDirPath();
        if (StringUtils.hasText(suffix)) {
            itemPath = itemPath.resolve(itemPath + suffix);
        } else {
            itemPath = itemPath.resolve(itemHash);
        }
        return ResponseEntity.ok(StorageUtil.loadResource(itemPath));
    }

    @ExceptionHandler(EdpOpException.class)
    public ResponseEntity<?> handleOpException(EdpOpException e) {
        return WebUtil.toResponseEntity(e.getResult());
    }

}
