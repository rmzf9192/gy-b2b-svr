package com.el.edi.dev.exports;

import com.el.edi.EdpExports;
import com.el.edi.dev.payload.DevDictionaryDefinition;
import com.el.edi.dev.payload.DevDictionaryWord;
import retrofit2.Call;

import java.util.List;

/**
 * 字典输出服务
 *
 * @author neo.pan
 * @since 2018/04/09
 */
public interface DevDictionaryExports extends EdpExports {

    /**
     * @param word 词汇
     * @return 定义
     */
    Call<DevDictionaryDefinition> one(DevDictionaryWord word);

    /**
     * @return 所有定义
     */
    Call<List<DevDictionaryDefinition>> all();
}
