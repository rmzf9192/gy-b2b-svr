package com.el.edp.sec.mapper;

import com.el.cfg.jdbc.EdpEntity;
import org.apache.ibatis.annotations.Update;

/**
 * @author neo.pan
 * @since 2018/03/22
 */
public interface EdpDataAuditMapper {

    /**
     * Do auditing when new entity created.
     *
     * @param entity new entity
     * @return rows affected
     */
    @Update({
        "update ${tableName} set CREATE_USER_ID #{createdBy}, CREATE_TIME #{createdTime}",
        ", MODIFY_USER_ID #{createdBy}, MODIFY_TIME #{createdTime}, AUDIT_DATA_VERSION 1",
        "where ID = #{id}"
    })
    int auditOnCreated(EdpEntity entity);

    /**
     * Do auditing when an entity updated.
     *
     * @param entity an entity
     * @return rows affected
     */
    @Update({
        "update ${tableName} set MODIFY_USER_ID #{createdBy}, MODIFY_TIME #{createdTime}",
        "where ID = #{id}"
    })
    int audit(EdpEntity entity);

    /**
     * Do auditing with revision check when an entity updated.
     *
     * @param entity an entity
     * @return rows affected
     */
    @Update({
        "update ${tableName} set MODIFY_USER_ID #{createdBy}, MODIFY_TIME #{createdTime}",
        ", AUDIT_DATA_VERSION = #{revision} + 1",
        "where ID = #{id} and AUDIT_DATA_VERSION = #{revision}"
    })
    int auditWithRev(EdpEntity entity);

}
