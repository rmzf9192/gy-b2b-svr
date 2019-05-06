package com.el.cfg.jdbc;

/**
 * @author neo.pan
 * @since 2018/03/22
 */
public interface JdbcRevisional {

    /**
     * @return 是否进行数据版本管理？使用概率较低，故默认为false。
     */
    default boolean revisional() {
        return false;
    }

    /**
     * @return data revision
     */
    Long getRevision();

    /**
     * @param revision data revision
     */
    void setRevision(final Long revision);

}
