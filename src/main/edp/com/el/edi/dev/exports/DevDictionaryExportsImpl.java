package com.el.edi.dev.exports;

import com.el.edi.dev.payload.DevDictionaryDefinition;
import com.el.edi.dev.payload.DevDictionaryWord;
import com.el.eds.dev.service.DevDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
@Profile("dev")
@Service
@RequiredArgsConstructor
public class DevDictionaryExportsImpl implements DevDictionaryExports {

    private final DevDictionaryService devDictionaryService;

    @Override
    public Call<DevDictionaryDefinition> one(DevDictionaryWord word) {
        return Calls.response(devDictionaryService.one(word));
    }

    @Override
    public Call<List<DevDictionaryDefinition>> all() {
        return Calls.response(devDictionaryService.all());
    }

}
