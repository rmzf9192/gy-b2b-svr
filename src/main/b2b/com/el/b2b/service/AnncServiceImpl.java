package com.el.b2b.service;

import com.el.b2b.api.AnncQuery;
import com.el.b2b.domain.Annc;
import com.el.b2b.mapper.AnncMapper;
import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.service.TableBathService;
import com.el.core.domain.PagingResult;
import com.el.edp.util.ResultUtil;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class AnncServiceImpl implements AnncService {
    private final AnncMapper anncMapper;

    private final TableBathService tableBathService;

    @Override
    public PagingResult<Annc> page(AnncQuery query) {
        int total = anncMapper.findCount(query);
        return total > 0 ? PagingResult.of(anncMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<Annc> findAll(AnncQuery query) {
        return anncMapper.findAll(query);
    }

    @Override
    public Annc findById(long id) {
        return anncMapper.findById(id);
    }

    @Transactional
    @Override
    public ResultUtil save(Annc annc) {
        if (StringUtils.isEmpty(annc.getId())) {
            anncMapper.insert(annc);
        } else {
            anncMapper.updateByPrimaryKey(annc);
        }
        return ResultUtil.success();
    }

    @Override
    public int remove(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.PB_ANNC, Arrays.asList(ids));
    }


}
