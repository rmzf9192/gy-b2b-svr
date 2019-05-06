package com.el.b2b.service;

import com.el.b2b.api.AddrQualfyQuery;
import com.el.b2b.domain.AddrQualfyDomain;
import com.el.b2b.mapper.AddrQualfyMapper;
import com.el.core.domain.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户证照信息相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class AddrQualfyServiceImpl implements AddrQualfyService {
    private final AddrQualfyMapper addrQualfyMapper;

    @Override
    public PagingResult<AddrQualfyDomain> findPage(AddrQualfyQuery query) {
        int total = addrQualfyMapper.findCount(query);
        return total > 0 ? PagingResult.of(addrQualfyMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<AddrQualfyDomain> findAll() {
        return addrQualfyMapper.findAll();
    }

    @Override
    public AddrQualfyDomain findById(long id) {
        return addrQualfyMapper.findById(id);
    }

    @Override
    public List<AddrQualfyDomain> findByAddrNo(String addrNo) {
        return addrQualfyMapper.findByAddrNo(addrNo);
    }
}
