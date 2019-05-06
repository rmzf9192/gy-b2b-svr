package com.el.b2b.service;

import com.el.b2b.domain.TsodDomain;
import com.el.b2b.mapper.TsodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by jerry.feng
 * on 2018/5/16.
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class TsodServiceImpl implements TsodService {
    private final TsodMapper tsodMapper;

    @Override
    public TsodDomain findById(long id) {
        return null;
    }

    @Override
    public int saveOrUpdate(TsodDomain domain) {
        return 0;
    }

    @Override
    public int delByIds(Long[] ids) {
        return 0;
    }
}
