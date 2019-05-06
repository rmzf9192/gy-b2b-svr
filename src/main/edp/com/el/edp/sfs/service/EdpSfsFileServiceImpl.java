package com.el.edp.sfs.service;

import com.el.core.util.HashUtil;
import com.el.edp.sfs.domain.*;
import com.el.edp.util.EdpIOUtil;
import com.el.edp.util.EdpImageUtil;
import com.el.edp.util.EdpOpException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author neo.pan
 * @since 2018/04/18
 */
@Slf4j
public class EdpSfsFileServiceImpl implements EdpSfsFileService {

    @Override
    public EdpSfsRepoItem createFileItem(EdpSfsRepoDir repoDir, MultipartFile file) throws IOException {
        val tempPath = repoDir.getRepoDirPath().resolve(UUID.randomUUID().toString());
        String fileHash;
        try (val outputStream = Files.newOutputStream(tempPath)) {
            fileHash = EdpIOUtil.copyAndGetMd5(file.getInputStream(), outputStream);
        }
        val filePath = tempPath.resolveSibling(fileHash);
        Files.move(tempPath, filePath, StandardCopyOption.REPLACE_EXISTING);

        val item = EdpSfsRepoItem.of(repoDir.toItemKey(fileHash), file);
        item.setFilePath(filePath);
        return item;
    }

    @Override
    public EdpSfsRepoItem createEmbedItem(EdpSfsRepoDir repoDir, MultipartFile file) throws IOException {
        val fileBytes = file.getBytes();
        val fileHash = HashUtil.md5(fileBytes);
        val item = EdpSfsRepoItem.of(repoDir.toItemKey(fileHash), file);
        item.setItemBlob(fileBytes);
        return item;
    }

    @Override
    public void checkFiles(EdpSfsRepo repo, MultipartFile[] files) throws IOException {
        val imgCheck = repo.getImgCheck();
        if (imgCheck != null) {
            for (val file : files) {
                if (repo.getMaxSize() < file.getSize()) {
                    throw new EdpOpException(EdpSfsOp.NG_SIZE);
                }
                if (!repo.getType().match(file.getContentType())) {
                    throw new EdpOpException(EdpSfsOp.NG_MIME);
                }
                if (!repo.matchExt(EdpIOUtil.extensionOf(file.getOriginalFilename()))) {
                    throw new EdpOpException(EdpSfsOp.NG_EXT);
                }
                if (repo.getType() == EdpSfsRepoType.img) {
                    try (val inputStream = file.getInputStream()) {
                        val image = ImageIO.read(inputStream);
                        val result = imgCheck.check(image.getWidth(), image.getHeight());
                        if (result != null) {
                            throw new EdpOpException(result);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<EdpSfsRepoItem> createItems(EdpSfsRepo repo, EdpSfsRepoDir repoDir, MultipartFile[] files) throws IOException {
        val items = new ArrayList<EdpSfsRepoItem>(files.length);
        val imgScales = repo.getImgScales();
        for (val file : files) {
            val item = repo.embedded()
                ? createEmbedItem(repoDir, file)
                : createFileItem(repoDir, file);
            items.add(scaleImageItem(item, imgScales));
            log.trace("[EDP-SFS] item READY: {}", item);
        }
        return items;
    }

    private EdpSfsRepoItem scaleImageItem(EdpSfsRepoItem item, List<EdpSfsImgScale> imgScales) throws IOException {
        for (val scale : imgScales) {
            final Path scaledFilePath = EdpIOUtil.appendSuffix(item.getFilePath(), scale.getSuffix());
            try (val input = Files.newInputStream(item.getFilePath());
                 val output = Files.newOutputStream(scaledFilePath)) {
                EdpImageUtil.scale(ImageIO.read(input), scale, output);
                log.trace("[EDP-SFS] scaled: {} -> {}", item.getFilePath(), scaledFilePath);
            }
        }
        return item;
    }

}
