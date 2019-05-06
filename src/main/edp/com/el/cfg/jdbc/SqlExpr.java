package com.el.cfg.jdbc;

import com.el.core.util.SqlUtil;

import java.util.function.Supplier;

/**
 * SQL 表达式
 *
 * @author neo.pan
 * @since 2018/03/30
 */
@FunctionalInterface
public interface SqlExpr extends Supplier<String> {

    SqlExpr nil = () -> "NULL";

    SqlExpr now = () -> "CURRENT_TIMESTAMP";

    static SqlExpr num(Number num) {
        return num::toString;
    }

    static SqlExpr str(CharSequence str) {
        return () -> SqlUtil.toSqlString(str);
    }

    static SqlExpr sql(String sql) {
        return () -> sql;
    }

}
