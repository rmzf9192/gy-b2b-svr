package com.el.edp.sfs.mapper;

import com.el.edp.EdpTest;
import com.el.edp.sfs.domain.EdpSfsRepoDir;
import com.el.edp.sfs.domain.EdpSfsRepoItem;
import com.el.edp.sfs.domain.EdpSfsRepoItemKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.el.edp.util.EdpIOUtil.EDP_CHARSET;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
@Slf4j
public class EdpSfsMapperTest extends EdpTest {

    @Autowired
    private EdpSfsMapper sfsMapper;

    private static final String mockRepoCode = "dev01";
    private static final String mockClsPath = "hello/world";
    private static final String mockDataKey = "1";
    private static final String mockItemHash = "52f83ff6877e42f613bcd2444c22528c";

    private static final EdpSfsRepoDir mockRepoDir =
        EdpSfsRepoItemKey.of(toTestPath(""), mockRepoCode, mockClsPath, mockDataKey);

    private static final EdpSfsRepoItemKey mockItemKey =
        EdpSfsRepoItemKey.of(mockRepoDir, mockItemHash);

    private static EdpSfsRepoItem buildItem() {
        EdpSfsRepoItem item = new EdpSfsRepoItem();
        item.setItemKey(mockItemKey);
        item.setItemSize(6L);
        item.setItemName("Hello.txt");
        item.setItemMime("text/plain");
        item.setItemBlob("World".getBytes(EDP_CHARSET));
        return item;
    }

    private static EdpSfsRepoItem buildEmbedItem() {
        EdpSfsRepoItem item = buildItem();
        item.setItemBlob("World".getBytes(EDP_CHARSET));
        return item;
    }

    @Test
    public void repoOf() {
        log.info("repoOf={}", sfsMapper.repoOf(mockRepoCode));
    }

    @Test
    public void itemOf() {
        log.info("itemOf={}", sfsMapper.itemOf(mockItemKey));
    }

    @Test
    public void addItem() {
        EdpSfsRepoItem item = buildItem();
        log.info("addItem={}", sfsMapper.addItem(item));
        log.info("itemOf={}", sfsMapper.itemOf(item.getItemKey()));
    }

    @Test
    public void addEmbedItem() {
        EdpSfsRepoItem item = buildEmbedItem();
        log.info("addItem={}", sfsMapper.addItem(item));
        log.info("itemOf={}", sfsMapper.itemOf(item.getItemKey()));
    }

}
