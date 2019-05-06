package com.el.edp.sfs.service;

import com.el.core.cache.CacheName;
import com.el.core.cache.CachePoint;
import com.el.edp.sfs.domain.EdpSfsRepo;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.sfs.domain.EdpSfsRepoItem;
import com.el.edp.sfs.domain.EdpSfsRepoItemKey;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SFS 资料库访问服务
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@CacheName("SFS")
public interface EdpSfsRepoService {

    /**
     * @param repoCode 资料库代码
     * @return 资料库信息
     */
    @CachePoint("'repo:' + [0]")
    EdpSfsRepo repoOf(String repoCode);

    /**
     * @param itemKey 资料键
     * @return 资料项信息
     */
    EdpSfsRepoItem itemOf(EdpSfsRepoItemKey itemKey);

    /**
     * @param repoDir 库内资料目录
     * @return 指定目录下的资料项信息列表
     */
    List<EdpSfsRepoItem> itemsOf(EdpSfsRepoDir repoDir);

    /**
     * 保存资料项
     *
     * @param item 资料项信息
     */
    @Transactional
    void saveItem(EdpSfsRepoItem item);

    /**
     * 记录资料项信息
     *
     * @param items 资料项信息
     */
    @Transactional
    void saveItems(List<EdpSfsRepoItem> items);

    /**
     * 提交临时资料项信息
     *
     * @param tempDir 临时资料目录
     * @param dataKey 数据键
     */
    @Transactional
    void commitTempItems(EdpSfsRepoDir tempDir, String dataKey);

    /**
     * 取消临时资料项信息
     *
     * @param sessionId 会话ID
     */
    @Transactional
    void cancelTempItems(String sessionId);

}
