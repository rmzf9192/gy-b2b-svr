package com.el.cfg.jdbc;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.LongSupplier;

/**
 * @author neo.pan
 * @since 2018/04/08
 */
@RequiredArgsConstructor(staticName = "of")
public class EdpSqlAuditInfoResolver implements SqlAuditInfoResolver {

    private final LongSupplier userIdSupplier;

    /**
     * 没有审计信息的数据表
     */
    private static final Set<String> tablesWithoutAudit =
        new HashSet<>(Arrays.asList(
            "DUAL", "PS_AUDIT_LOG", "PS_GEN_DEF", "PS_GEN_SEQ",
            "PS_AUTH_PERM", "PS_AUTH_ROLE", "PS_AUTH_ROLE_PERM",
            "PS_AUTH_USER_ROLE", "PS_FLOW_USER_ROLE", "PS_FLOW_ROLE",
            "PS_FLOW_TASK", "PS_FLOW_ROLE_TASK", "PS_FLOW_TRACE",
            "VE84211W"
        ));

    /**
     * BPM 数据表前缀
     */
    private static final String BPM_TABLE_PREFIX = "ACT_";

    @Override
    public boolean isAuditTable(String tableName) {
        return !tablesWithoutAudit.contains(tableName) && !tableName.startsWith(BPM_TABLE_PREFIX);
    }

    @Override
    public long userId() {
        return userIdSupplier.getAsLong();
    }
}
