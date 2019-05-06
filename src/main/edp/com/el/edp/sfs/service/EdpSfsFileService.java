package com.el.edp.sfs.service;

import com.el.edp.sfs.domain.EdpSfsRepo;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.sfs.domain.EdpSfsRepoItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * SFS 文件处理服务
 *
 * @author neo.pan
 * @since 2018/04/18
 */
public interface EdpSfsFileService {

    /**
     * 产生文件资料项(文件以内容的md5散列命名)
     *
     * @param repoDir 文件处理上下文
     * @param file    上传文件信息
     * @return 资料项
     */
    EdpSfsRepoItem createFileItem(EdpSfsRepoDir repoDir, MultipartFile file) throws IOException;

    /**
     * 产生嵌入资料项
     *
     * @param repoDir 文件处理上下文
     * @param file    上传文件信息
     * @return 资料项
     */
    EdpSfsRepoItem createEmbedItem(EdpSfsRepoDir repoDir, MultipartFile file) throws IOException;

    /**
     * @param repo  资料库信息
     * @param files 上传文件
     * @throws IOException IO异常发生
     */
    void checkFiles(EdpSfsRepo repo, MultipartFile[] files) throws IOException;

    /**
     * @param repo    资料库信息
     * @param repoDir 文件处理上下文
     * @param files   上传文件
     * @return 生成资料项(数量等于上传文件数)
     * @throws IOException IO异常发生
     */
    List<EdpSfsRepoItem> createItems(EdpSfsRepo repo, EdpSfsRepoDir repoDir, MultipartFile[] files) throws IOException;

}
