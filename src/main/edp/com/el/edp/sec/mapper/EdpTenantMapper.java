package com.el.edp.sec.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author neo.pan
 * @since 2018/04/03
 */
public interface EdpTenantMapper {

    @Select("select ID from ")
    long tenentIdOf(String tenantCode);
}
