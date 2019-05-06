package com.el.b2b.service;

import com.el.b2b.api.HomeContactInfoQuery;
import com.el.b2b.domain.HomeContactInfo;
import com.el.b2b.mapper.HomeContactInfoMapper;
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
public class HomeContactInfoServiceImpl implements HomeContactInfoService {
    private final HomeContactInfoMapper homeContactInfoMapper;

    private final TableBathService tableBathService;

    @Override
    public PagingResult<HomeContactInfo> page(HomeContactInfoQuery query) {
        int total = homeContactInfoMapper.findCount(query);
        return total > 0 ? PagingResult.of(homeContactInfoMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public List<HomeContactInfo> findAll(HomeContactInfoQuery query) {
        return homeContactInfoMapper.findAll(query);
    }

    @Override
    public HomeContactInfo findById(long id) {
        return homeContactInfoMapper.findById(id);
    }

    @Transactional
    @Override
    public ResultUtil save(HomeContactInfo homeContactInfo) {
        if (StringUtils.isEmpty(homeContactInfo.getId())) {
            homeContactInfoMapper.insert(homeContactInfo);
        } else {
            homeContactInfoMapper.updateByPrimaryKey(homeContactInfo);
        }
        return ResultUtil.success();
    }

    @Override
    public int removeContact(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.HOME_CONTACT_INFO, Arrays.asList(ids));
    }
}
