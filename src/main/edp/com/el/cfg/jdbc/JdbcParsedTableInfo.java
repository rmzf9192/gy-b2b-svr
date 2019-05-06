package com.el.cfg.jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 解析后的数据表信息
 *
 * @author neo.pan
 * @since 2018/04/08
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class JdbcParsedTableInfo {

    /**
     * 表名
     */
    private final String tableName;
    /**
     * 解析走到的位置
     */
    private final int parseAt;
    /**
     * 是否具有租户区分？
     */
    @Setter
    private Long tenantId;
    /**
     * 是否具有审计信息？
     */
    @Setter
    private Long userId;

    /**
     * @return 无需注入
     */
    public boolean selfServiced() {
        return tenantId == null && userId == null;
    }

}
