package com.el.eds.dev.service;

import com.el.edi.EdpExports;
import com.el.edi.dev.payload.DevDictionaryDefinition;
import com.el.edi.dev.payload.DevDictionaryWord;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
public interface DevDictionaryService extends EdpExports {

    /**
     * @return 所有定义
     */
    List<DevDictionaryDefinition> all();

    /**
     * @param word 词汇
     * @return 定义
     */
    DevDictionaryDefinition one(DevDictionaryWord word);

}
