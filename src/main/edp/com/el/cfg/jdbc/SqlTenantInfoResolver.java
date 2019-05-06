package com.el.cfg.jdbc;

/**
 * 解决租户信息的获取
 *
 * @author neo.pan
 * @since 2018/03/26
 */
public interface SqlTenantInfoResolver {

    /**
     * @return 租客ID字段名
     */
    default String tenantIdColumnName() {
        return "TENANT_ID";
    }

    /**
     * @return 租客ID
     */
    default long tenantId() {
        return 1L;
    }

    /**
     * @return 是否进行租客ID区分？
     */
    boolean isTenantTable(String tableName);

}
