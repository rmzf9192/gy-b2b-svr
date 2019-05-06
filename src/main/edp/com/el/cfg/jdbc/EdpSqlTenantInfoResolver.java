package com.el.cfg.jdbc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author neo.pan
 * @since 2018/04/03
 */
public class EdpSqlTenantInfoResolver implements SqlTenantInfoResolver {

    /**
     * 没有租客区分的数据表
     */
    private static final Set<String> tablesWithoutTenant =
        new HashSet<>(Arrays.asList(
            "DUAL", "PS_AUTH_PERM", "PS_GEN_SEQ",
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
    public boolean isTenantTable(String tableName) {
        return !tablesWithoutTenant.contains(tableName) && !tableName.startsWith(BPM_TABLE_PREFIX);
    }

}
