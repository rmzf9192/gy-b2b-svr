package com.el.edp.mds.api;

import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edp.mds.domain.EdpEmployee;
import com.el.edp.mds.mapper.EdpEmployeeMapper;
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
@RequestMapping("/edp/mds")
@RequiredArgsConstructor
public class EdpEmployeeApi {

    private final EdpEmployeeMapper employeeMapper;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    /**
     * @param query 搜索条件
     * @return 员工分页列表
     */
    @GetMapping("/emps")
    public PagingResult<EdpEmployee> employees(EdpEmployeeQuery query) {
        val rows = employeeMapper.employeesBy(query);
        val total = employeeMapper.employeeCountBy(query);
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }

    /**
     * @param id 员工主键
     * @return 员工详情
     */
    @GetMapping("/emps/{id}")
    public EdpEmployee employee(@PathVariable long id) {
        return employeeMapper.employeeBy(id);
    }
}
