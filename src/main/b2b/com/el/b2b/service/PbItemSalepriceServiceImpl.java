package com.el.b2b.service;

import com.el.b2b.api.PbItemSalepriceQuery;
import com.el.b2b.domain.PbItemSaleprice;
import com.el.b2b.mapper.PbItemSalepriceMapper;
import com.el.core.domain.PagingResult;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class PbItemSalepriceServiceImpl implements PbItemSalepriceService {

    private final PbItemSalepriceMapper pbItemSalepriceMapper;

    @Override
    public PagingResult<PbItemSaleprice> page(PbItemSalepriceQuery query) {
        int total = pbItemSalepriceMapper.findCountForCust(query);
        return total > 0 ? PagingResult.of(pbItemSalepriceMapper.findPageForCust(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public PbItemSaleprice findById(long id) {
        return pbItemSalepriceMapper.findById(id);
    }

    @Transactional
    @Override
    public int saveOrUpdate(PbItemSaleprice pbItemSaleprice) {
        int result = 0;
        if (StringUtils.isEmpty(pbItemSaleprice.getId())) {
            result = pbItemSalepriceMapper.insert(pbItemSaleprice);
        } else {
            result = pbItemSalepriceMapper.updateByPrimaryKey(pbItemSaleprice);
        }
        return result;
    }

    @Transactional
    @Override
    public int delete(long id) {
        PbItemSaleprice pbItemSaleprice = pbItemSalepriceMapper.findById(id);
        return pbItemSalepriceMapper.updateByPrimaryKey(pbItemSaleprice);
    }

    @Override
    public BigDecimal getPriceByItemCode(String itemCode, String ouCode, String custCode) {

        return pbItemSalepriceMapper.getPriceByItemCode(itemCode, ouCode, custCode);
    }

}
