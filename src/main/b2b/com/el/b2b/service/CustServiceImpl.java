package com.el.b2b.service;

import com.el.b2b.api.CustQuery;
import com.el.b2b.domain.CustDomain;
import com.el.b2b.mapper.CustMapper;
import com.el.core.domain.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jerry.feng
 * on 2018/5/11.
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class CustServiceImpl implements CustService {
    private final CustMapper custMapper;

    @Override
    public PagingResult<CustDomain> findPage(CustQuery query) {
        int total = custMapper.findCount(query);
        return total > 0 ? PagingResult.of(custMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<CustDomain> findAll() {
        return custMapper.findAll();
    }

    @Override
    public CustDomain findById(Long id) {
        return custMapper.findById(id);
    }

    @Override
    public CustDomain findByCustCode(String custCode) {
        return custMapper.findByCustCode(custCode);
    }
}
