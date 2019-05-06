package com.el.edi.dev.payload;

import com.el.edi.EdpPayload;
import lombok.Value;

/**
 * 词汇定义
 *
 * @author neo.pan
 * @since 2018/04/09
 */
@Value(staticConstructor = "of")
public class DevDictionaryDefinition implements EdpPayload {
    /**
     * 词汇
     */
    private final DevDictionaryWord word;
    /**
     * 描述
     */
    private final String desc;
}
