package com.el.eds.dev.service;

import com.el.edi.dev.payload.DevDictionaryDefinition;
import com.el.edi.dev.payload.DevDictionaryWord;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
@Profile("eds")
@Service
public class DevDictionaryServiceImpl implements DevDictionaryService {

    private final Map<DevDictionaryWord, String> items;

    public DevDictionaryServiceImpl() {
        items = new HashMap<>();
        // Pet
        items.put(
            DevDictionaryWord.cat,
            "a small animal with four legs.");
        items.put(
            DevDictionaryWord.dog,
            "a common animal with four legs, fur, and a tail.");
        // Store
        items.put(
            DevDictionaryWord.shop,
            "a building or part of a building where you can buy things, food, or services");
        items.put(
            DevDictionaryWord.mall,
            "a large area where there are a lot of shops, usually a covered area where cars are not allowed");
    }

    @Override
    public List<DevDictionaryDefinition> all() {
        return items.entrySet().stream()
            .map(kv -> DevDictionaryDefinition.of(kv.getKey(), kv.getValue()))
            .collect(Collectors.toList());
    }

    @Override
    public DevDictionaryDefinition one(DevDictionaryWord word) {
        return DevDictionaryDefinition.of(word, items.get(word));
    }

}
