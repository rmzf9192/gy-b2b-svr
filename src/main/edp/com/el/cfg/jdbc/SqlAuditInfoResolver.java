package com.el.cfg.jdbc;

/**
 * 解决审计信息的获取
 *
 * @author neo.pan
 * @since 2018/03/26
 */
public interface SqlAuditInfoResolver {

    /**
     * @return 是否包含审计数据？
     */
    boolean isAuditTable(String tableName);

    /**
     * @return 用户ID
     */
    long userId();

}
