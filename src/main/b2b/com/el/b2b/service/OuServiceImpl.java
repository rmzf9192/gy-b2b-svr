package com.el.b2b.service;

import com.el.b2b.api.OuQuery;
import com.el.b2b.domain.OuDomain;
import com.el.b2b.mapper.OuMapper;
import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.service.TableBathService;
import com.el.core.domain.PagingResult;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 公司信息相关业务
 * Created by jerry.feng
 * on 2018/5/10.
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class OuServiceImpl implements OuService {
    private final OuMapper ouMapper;
    private final TableBathService tableBathService;

    @Override
    public PagingResult<OuDomain> findPage(OuQuery query) {
        int total = ouMapper.findCount(query);
        return total > 0 ? PagingResult.of(ouMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<OuDomain> findAll() {
        return ouMapper.findAll();
    }

    @Override
    public OuDomain findById(Long id) {
        return ouMapper.findById(id);
    }

    @Override
    public OuDomain findByOuCode(String ouCode) {
        return ouMapper.findByOuCode(ouCode);
    }

    @Override
    public int delOu(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.PB_OU, Arrays.asList(ids));
    }


}
