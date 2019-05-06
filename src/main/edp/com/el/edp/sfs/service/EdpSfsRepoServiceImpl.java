package com.el.edp.sfs.service;

import com.el.edp.sfs.domain.EdpSfsRepo;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.sfs.domain.EdpSfsRepoItem;
import com.el.edp.sfs.domain.EdpSfsRepoItemKey;
import com.el.edp.sfs.mapper.EdpSfsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/18
 */
@Slf4j
@RequiredArgsConstructor
public class EdpSfsRepoServiceImpl implements EdpSfsRepoService {

    private final EdpSfsMapper sfsMapper;

    @Override
    public EdpSfsRepo repoOf(String repoCode) {
        EdpSfsRepo repo = sfsMapper.repoOf(repoCode);
        repo.setImgCheck(sfsMapper.imgCheckOf(repoCode));
        repo.setImgScales(sfsMapper.imgScalesOf(repoCode));
        return repo;
    }

    @Override
    public EdpSfsRepoItem itemOf(EdpSfsRepoItemKey itemKey) {
        return sfsMapper.itemOf(itemKey);
    }

    @Override
    public List<EdpSfsRepoItem> itemsOf(EdpSfsRepoDir repoDir) {
        return sfsMapper.itemsOf(repoDir);
    }

    @Override
    public void saveItem(EdpSfsRepoItem item) {
        val itemKey = item.getItemKey();
        if (sfsMapper.hasItem(itemKey) != null) {
            log.info("[EDP-SFS] item with same content found: {}", item);
            return;
        }
        sfsMapper.addItem(item);
    }

    @Override
    public void saveItems(List<EdpSfsRepoItem> items) {
        items.forEach(this::saveItem);
    }

    @Override
    public void commitTempItems(EdpSfsRepoDir tempDir, String dataKey) {
        sfsMapper.commitTempItems(tempDir, dataKey);
    }

    @Override
    public void cancelTempItems(String sessionId) {
        sfsMapper.cancelTempItems(sessionId);
    }

}
