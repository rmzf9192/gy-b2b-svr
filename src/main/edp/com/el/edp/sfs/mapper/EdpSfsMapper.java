package com.el.edp.sfs.mapper;

import com.el.edp.sfs.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/18
 */
public interface EdpSfsMapper {

    /**
     * @param repoCode 资料库代码
     * @return 资料库信息
     */
    @Select({
        "select CODE, NAME, TYPE, FILE_EXTS fileExts, EMBEDDED embedded, WEB_PATH webPath, MAX_SIZE maxSize",
        "from PS_SFS_REPO",
        "where CODE = #{repoCode}",
    })
    EdpSfsRepo repoOf(String repoCode);

    /**
     * @param repoCode 资料库代码
     * @return 资料库图片检查设置
     */
    @Select({
        "select REPO_CODE repoCode, RES_W_MIN minResW, RES_W_MAX maxResW",
        ", RES_H_MIN minResH, RES_H_MAX maxResH, RES_R_MIN_D2 minResR_D2, RES_R_MAX_D2 maxResR_D2",
        "from PS_SFS_IMG_CHECK where REPO_CODE = #{repoCode}"
    })
    EdpSfsImgCheck imgCheckOf(String repoCode);

    /**
     * @param repoCode 资料库代码
     * @return 资料库图片缩放设置
     */
    @Select({
        "select REPO_CODE, RES_W_MIN, RES_W_MAX, RES_H_MIN, RES_H_MAX, RES_R_MIN_D2, RES_R_MAX_D2",
        "from PS_SFS_IMG_CHECK where REPO_CODE = #{repoCode}"
    })
    List<EdpSfsImgScale> imgScalesOf(String repoCode);

    /**
     * @param itemDir 资料目录路径
     * @return 一批资料项信息
     */
    @Select({
        "select REPO_CODE \"itemKey.repoCode\", CLS_PATH \"itemKey.clsPath\"",
        ", DATA_KEY \"itemKey.dataKey\", TEMP_ITEM \"itemKey.tempItem\", ITEM_HASH \"itemKey.itemHash\"",
        ", ITEM_NAME itemName, ITEM_MIME itemMime, ITEM_SIZE itemSize, ITEM_BLOB itemBlob",
        "from PS_SFS_ITEM",
        "where REPO_CODE = #{repoCode} and CLS_PATH = #{clsPath} and DATA_KEY = #{dataKey}",
    })
    List<EdpSfsRepoItem> itemsOf(EdpSfsRepoDir itemDir);

    /**
     * @param itemKey 资料项索引
     * @return 资料项信息
     */
    @Select({
        "select REPO_CODE \"itemKey.repoCode\", CLS_PATH \"itemKey.clsPath\"",
        ", DATA_KEY \"itemKey.dataKey\", TEMP_ITEM \"itemKey.tempItem\", ITEM_HASH \"itemKey.itemHash\"",
        ", ITEM_NAME itemName, ITEM_MIME itemMime, ITEM_SIZE itemSize, ITEM_BLOB itemBlob",
        "from PS_SFS_ITEM",
        "where REPO_CODE = #{repoCode} and CLS_PATH = #{clsPath}",
        " and DATA_KEY = #{dataKey} and ITEM_HASH = #{itemHash}"
    })
    EdpSfsRepoItem itemOf(EdpSfsRepoItemKey itemKey);

    /**
     * @param itemKey 索引信息
     * @return 删除记录数
     */
    @Delete({
        "select 1 from PS_SFS_ITEM",
        "where REPO_CODE = #{repoCode} and CLS_PATH = #{clsPath}",
        " and DATA_KEY = #{dataKey} and ITEM_HASH = #{itemHash}"
    })
    Integer hasItem(EdpSfsRepoItemKey itemKey);

    /**
     * @param itemKey 索引信息
     * @return 删除记录数
     */
    @Delete({
        "delete from PS_SFS_ITEM",
        "where REPO_CODE = #{repoCode} and CLS_PATH = #{clsPath}",
        " and DATA_KEY = #{dataKey} and ITEM_HASH = #{itemHash}"
    })
    int delItem(EdpSfsRepoItemKey itemKey);

    /**
     * @param item 资料项信息
     * @return 新增记录数
     */
    @Insert({
        "insert into PS_SFS_ITEM (",
        "  REPO_CODE, CLS_PATH, DATA_KEY, TEMP_ITEM, ITEM_HASH",
        ", ITEM_NAME, ITEM_MIME, ITEM_SIZE, ITEM_BLOB",
        ") values (",
        "  #{itemKey.repoCode}, #{itemKey.clsPath}, #{itemKey.dataKey}, #{itemKey.tempItem}",
        ", #{itemKey.itemHash}, #{itemName}, #{itemMime}, #{itemSize}, #{itemBlob,jdbcType=BLOB}",
        ")",
    })
    int addItem(EdpSfsRepoItem item);

    /**
     * @param tempDir 临时仓库目录信息
     * @param dataKey 数据键
     */
    @Update({
        "update PS_SFS_ITEM set TEMP_ITEM = 'N' and DATA_KEY = #{dataKey}",
        "where REPO_CODE = #{tempDir.repoCode} and CLS_PATH = #{tempDir.clsPath}",
        " and DATA_KEY = #{tempDir.dataKey}"
    })
    int commitTempItems(@Param("tempDir") EdpSfsRepoDir tempDir, @Param("dataKey") String dataKey);

    /**
     * @param sessionId 会话ID
     */
    @Delete({
        "delete from PS_SFS_ITEM",
        "where TEMP_ITEM = 'Y' and DATA_KEY = #{sessionId}"
    })
    int cancelTempItems(String sessionId);

}
