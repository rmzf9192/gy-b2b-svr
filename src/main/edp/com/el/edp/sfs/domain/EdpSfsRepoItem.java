package com.el.edp.sfs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * 资料项
 *
 * @author neo.pan
 * @since 2018/04/17
 */
@Data
@ToString(exclude = {"itemBlob", "filePath"})
@EqualsAndHashCode(of = "itemKey")
public class EdpSfsRepoItem {

    /**
     * 资料的内容
     */
    @JsonIgnore
    private transient byte[] itemBlob;

    /**
     * 资料的绝对路径
     */
    @JsonIgnore
    private transient Path filePath;

    /**
     * 资料库代码
     */
    private EdpSfsRepoItemKey itemKey;

    /**
     * 资料原名(即资料文件原名)
     */
    private String itemName;

    /**
     * 字节数
     */
    private Long itemSize;

    /**
     * 资料类型({@link java.nio.file.Files#probeContentType(java.nio.file.Path)})
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types">MIME types</a>
     * @see <a href="https://www.sitepoint.com/mime-types-complete-list/">MIME Types - The Complete List</a>
     */
    private String itemMime;

    public static EdpSfsRepoItem of(EdpSfsRepoItemKey itemKey, MultipartFile file) {
        EdpSfsRepoItem item = new EdpSfsRepoItem();
        item.itemKey = itemKey;
        item.itemName = file.getOriginalFilename();
        item.itemMime = file.getContentType();
        item.itemSize = file.getSize();
        return item;
    }

}
