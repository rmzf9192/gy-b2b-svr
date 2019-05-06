package com.el.edp.sec.api;

import com.el.core.domain.PagingResult;
import com.el.core.udc.UdcNameResolver;
import com.el.edp.sec.domain.EdpOrganization;
import com.el.edp.sec.mapper.EdpOrganizationMapper;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Profile("edp")
@RestController
@RequestMapping("/edp/sec")
@RequiredArgsConstructor
public class EdpOrganizationApi {

    private final EdpOrganizationMapper organizationMapper;

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    /**
     * @param query 搜索条件
     * @return 组织分页列表
     */
    @GetMapping("/orgs")
    public PagingResult<EdpOrganization> organizations(EdpOrganizationQuery query) {
        val rows = organizationMapper.organizationsBy(query);
        val total = organizationMapper.organizationCountBy(query);
        return PagingResult.of(udcNameResolver.resolveUdcNames(rows), total);
    }

    /**
     * @param id 组织主键
     * @return 组织详情
     */
    @GetMapping("/orgs/{id}")
    public EdpOrganization organization(@PathVariable long id) {
        return organizationMapper.organizationBy(id);
    }
}
