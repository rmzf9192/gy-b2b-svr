package com.el.edp.sfs.domain;

import com.el.core.domain.YesNoFlag;
import com.el.edp.util.EdpIOUtil;
import lombok.Data;
import lombok.ToString;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 资料项目录
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Data
@ToString(exclude = "rootPath")
public class EdpSfsRepoDir {

    /**
     * 所有资料库的根路径
     */
    private Path rootPath;
    /**
     * 资料库代码
     */
    private String repoCode;
    /**
     * 资料分类路径
     */
    private String clsPath;
    /**
     * 数据键或会话ID
     */
    private String dataKey;
    /**
     * 是否为临时目录
     */
    private YesNoFlag tempItem = YesNoFlag.N;

    /**
     * @return 资料项目录
     */
    public String getRepoDir() {
        return repoCode + "/" + clsPath + "/" + dataKey;
    }

    /**
     * @param rootPath 根路径
     * @param repoCode 资料库代码
     * @param clsPath  分类路径
     * @param dataKey  数据键
     * @return 资料目录
     */
    public static EdpSfsRepoDir of(Path rootPath, String repoCode, String clsPath, String dataKey) {
        EdpSfsRepoDir repoDir = new EdpSfsRepoDir();
        repoDir.rootPath = rootPath;
        repoDir.repoCode = repoCode;
        repoDir.clsPath = clsPath;
        repoDir.dataKey = dataKey;
        return repoDir;
    }

    /**
     * @param session  会话
     * @param repoCode 资料库代码
     * @param clsPath  分类路径
     * @return 临时资料目录
     * @throws IOException IO异常发生
     */
    public static EdpSfsRepoDir tempDirOf(HttpSession session, String repoCode, String clsPath) throws IOException {
        EdpSfsRepoDir repoDir = EdpSfsRepoDir.of(
            EdpIOUtil.ensureTempDirectory(session), repoCode, clsPath, session.getId());
        repoDir.tempItem = YesNoFlag.Y;
        return repoDir;
    }

    /**
     * @return 资料目录路径
     */
    public Path getRepoDirPath() {
        return EdpIOUtil.ensureDirectory(rootPath.resolve(getRepoDir()));
    }

    /**
     * @param fileHash 文件散列
     * @return 资料项索引
     */
    public EdpSfsRepoItemKey toItemKey(String fileHash) {
        return EdpSfsRepoItemKey.of(this, fileHash);
    }

}
