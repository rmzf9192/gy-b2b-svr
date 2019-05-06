package com.el.b2b.service;

import com.el.b2b.api.PbItemQuery;
import com.el.b2b.domain.PbItem;
import com.el.b2b.mapper.PbItemMapper;
import com.el.core.domain.PagingResult;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class PbItemServiceImpl implements PbItemService {

    private final PbItemMapper pbItemMapper;

    @Override
    public PagingResult<PbItem> page(PbItemQuery query) {
        int total = pbItemMapper.findCount(query);
        return total > 0 ? PagingResult.of(pbItemMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public PbItem findById(long id) {
        return pbItemMapper.findById(id);
    }


    @Override
    public PbItem findByOuterCode(String outerCode) {
        return pbItemMapper.findByOuterCode(outerCode);
    }

    @Override
    public PbItem findByItemCodeAndOuCode(String itemCode, String ouCode) {
        return pbItemMapper.findByItemCodeAndOuCode(itemCode, ouCode);
    }

    @Transactional
    @Override
    public int saveOrUpdate(PbItem pbItem) {
        int result = 0;
        if (StringUtils.isEmpty(pbItem.getId())) {
            result = pbItemMapper.insert(pbItem);
        } else {
            result = pbItemMapper.updateByPrimaryKey(pbItem);
        }
        return result;
    }

    @Transactional
    @Override
    public int delete(long id) {
        PbItem pbItem = pbItemMapper.findById(id);
        return pbItemMapper.updateByPrimaryKey(pbItem);
    }

}
