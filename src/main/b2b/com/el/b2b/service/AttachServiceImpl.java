package com.el.b2b.service;

import com.el.b2b.api.AttachQuery;
import com.el.b2b.domain.Attach;
import com.el.b2b.mapper.AttachMapper;
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
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class AttachServiceImpl implements AttachService {
    private final AttachMapper attachMapper;

    private final TableBathService tableBathService;

    @Override
    public PagingResult<Attach> page(AttachQuery query) {
        int total = attachMapper.findCount(query);
        return total > 0 ? PagingResult.of(attachMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<Attach> findAll(AttachQuery query) {
        return attachMapper.findAll(query);
    }

    @Override
    public int remove(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.PB_ATTACH, Arrays.asList(ids));
    }
}
