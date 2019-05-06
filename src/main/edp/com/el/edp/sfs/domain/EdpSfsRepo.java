package com.el.edp.sfs.domain;

import com.el.core.domain.CodeName;
import com.el.core.domain.YesNoFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 资料库
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Data
@EqualsAndHashCode(of = "code")
public class EdpSfsRepo implements CodeName {

    /**
     * 资料库代码
     */
    private String code;
    /**
     * 资料库名称
     */
    private String name;

    /**
     * 资料类型
     */
    private EdpSfsRepoType type;
    /**
     * 扩展名列表(逗号分隔)
     */
    private Set<String> fileExts;

    public void setFileExts(String exts) {
        fileExts = new HashSet<>(Arrays.asList(exts.toLowerCase().split(",")));
    }

    /**
     * @param fileExt 文件扩展名
     * @return true if matched
     */
    public boolean matchExt(String fileExt) {
        return fileExts.contains(fileExt.toUpperCase());
    }

    /**
     * 是否将资料内容存入DB(适用于小型数据文件)
     */
    private YesNoFlag embedded;

    public boolean embedded() {
        return embedded.yes();
    }

    /**
     * @return 是否可缩放
     */
    public boolean scalable() {
        return type == EdpSfsRepoType.img && embedded.no();
    }

    /**
     * 资料获取路径
     */
    private String webPath;

    /**
     * 资料的最大字节数(默认为0、标识不限)
     */
    private Long maxSize;

    /**
     * 图片检查设置
     */
    private EdpSfsImgCheck imgCheck;

    /**
     * 图片缩放设置
     */
    private List<EdpSfsImgScale> imgScales;

    public List<EdpSfsImgScale> getImgScales() {
        return scalable() ? imgScales : Collections.emptyList();
    }

}
