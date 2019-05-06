package com.el.edp.mds.api;

import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edp.mds.domain.EdpOperationUnit;
import com.el.edp.mds.mapper.EdpOperationUnitMapper;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@RestController
@RequestMapping("/edp")
@RequiredArgsConstructor
public class EdpOperationUnitApi {

    private final EdpOperationUnitMapper operationUnitMapper;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    /**
     * @param query 搜索条件
     * @return 公司分页列表
     */
    @GetMapping("/mds/ous")
    public PagingResult<EdpOperationUnit> operationUnitsBy(EdpOperationUnitQuery query) {
        val rows = operationUnitMapper.operationUnitsBy(query);
        val total = operationUnitMapper.operationUnitCountBy(query);
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }

    /**
     * @param id 公司主键
     * @return 公司详情
     */
    @GetMapping("/mds/ous/{id}")
    public EdpOperationUnit operationUnit(@PathVariable long id) {
        return operationUnitMapper.operationUnitBy(id);
    }

    /**
     * @param query 搜索条件
     * @return 公司分页列表，用于前端放大镜组件
     */
    @GetMapping("/ous")
    public PagingResult<EdpOperationUnit> operationUnits4Pop(EdpOperationUnitQuery query) {
        query.setDeleteFlag((short) 0);
        val rows = operationUnitMapper.operationUnitsBy(query);
        val total = operationUnitMapper.operationUnitCountBy(query);
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }
}
