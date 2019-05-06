package com.el.dev;

import com.el.core.cache.CacheName;
import com.el.core.cache.CachePoint;
import com.el.core.web.WebUtil;
import com.el.edi.dev.exports.DevDictionaryExports;
import com.el.edi.dev.payload.DevDictionaryDefinition;
import com.el.edi.dev.payload.DevDictionaryWord;
import com.el.eds.dev.service.DevRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/09
 */
@Conditional(DevEdsCondition.class)
@Slf4j
@CacheName("DEV")
@RestController
@RequestMapping("/dev/eds")
@RequiredArgsConstructor
public class DevEdsApi {

    private final DevDictionaryExports devDictionaryExports;

    @CachePoint("'dict:' + name()")
    @GetMapping("/dict/item/{word}")
    public DevDictionaryDefinition dictionaryDefinitionOf(@PathVariable DevDictionaryWord word) throws IOException {
        val definition = devDictionaryExports.one(word).execute().body();
        log.info("[EDP-DEV] dictionary[{}]={}", word, definition);
        return definition;
    }

    @CachePoint(value = "dicts", spel = false)
    @GetMapping("/dict/items")
    public List<DevDictionaryDefinition> dictionaryDefinitions() throws IOException {
        val definitions = devDictionaryExports.all().execute().body();
        log.info("[EDP-DEV] dictionary.size={}", definitions.size());
        return definitions;
    }

    private final DevRedisService devRedisService;

    @PostMapping("/redis/flush")
    public ResponseEntity devRedisFlushAll() {
        devRedisService.flushAll();
        log.info("[EDP-DEV] redis flush all");
        return WebUtil.toResponseEntity();
    }

}
