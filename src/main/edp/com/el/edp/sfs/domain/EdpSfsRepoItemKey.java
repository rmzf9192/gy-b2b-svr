package com.el.edp.sfs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 资料项索引
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, of = {"itemHash"})
public class EdpSfsRepoItemKey extends EdpSfsRepoDir {

    /**
     * 资料内容散列(md5)
     */
    private String itemHash;

    /**
     * @return 库内资料路径
     */
    public String getRepoItemFile() {
        return getRepoDir() + "/" + itemHash;
    }

    public static EdpSfsRepoItemKey of(EdpSfsRepoDir repoDir, String itemHash) {
        EdpSfsRepoItemKey itemKey = new EdpSfsRepoItemKey();
        itemKey.setRootPath(repoDir.getRootPath());
        itemKey.setRepoCode(repoDir.getRepoCode());
        itemKey.setClsPath(repoDir.getClsPath());
        itemKey.setDataKey(repoDir.getDataKey());
        itemKey.setTempItem(repoDir.getTempItem());
        itemKey.itemHash = itemHash;
        return itemKey;
    }

}
