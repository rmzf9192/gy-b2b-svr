package com.el.b2b.sys.service;

import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.mapper.TableBathMapper;
import com.el.edp.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表公共操作
 * Created by jerry.feng
 * 2018/05/17
 */
@Profile("b2b")
@Service
public class TableBathServiceImpl implements TableBathService {
    @Autowired
    private TableBathMapper tableBathMapper;

    //批量逻辑删除
    @Transactional
    @Override
    public int deleteBath(TableMeta tableMeta, List<Long> idList) {
        if (StringUtils.isEmpty(tableMeta) || StringUtils.isEmpty(idList) || idList.size() <= 0) {
            return 0;
        }
        int result = tableBathMapper.deleteBath(tableMeta, idList);
        return result;
    }

    //批量逻辑删除
    @Transactional
    @Override
   public int deleteBathStatus(TableMeta tableMeta, List<Long> idList, String status) {
        if (StringUtils.isEmpty(tableMeta) || StringUtils.isEmpty(idList) || idList.size() <= 0) {
            return 0;
        }
        int result = tableBathMapper.deleteBathStatus(tableMeta, idList,status, LocalDateTime.now());
        return result;
    }
}
