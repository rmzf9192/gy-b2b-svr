package com.el.b2b.service;

import com.el.b2b.domain.HomeOu;
import com.el.b2b.mapper.HomeOuMapper;
import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.service.TableBathService;
import com.el.edp.util.ResultUtil;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/21
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class HomeOuServiceImpl implements HomeOuService {
    private final HomeOuMapper homeOuMapper;
    private final TableBathService tableBathService;

    @Override
    public HomeOu findByOuCode(String ouCode) {
        return homeOuMapper.findByOuCode(ouCode);
    }

    @Transactional
    @Override
    public ResultUtil save(HomeOu homeOu) {
        if (StringUtils.isEmpty(homeOu.getId())) {
            homeOuMapper.insert(homeOu);
        } else {
            homeOuMapper.updateByPrimaryKey(homeOu);
        }
        return ResultUtil.success();
    }

    @Override
    public int remove(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.HOME_OU, Arrays.asList(ids));
    }

}
