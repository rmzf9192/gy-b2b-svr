package com.el.edp.sec.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.sec.api.EdpAuditLogQuery;
import com.el.edp.sec.domain.EdpAuditLog;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author danfeng
 * @since 2018/3/21
 */
public interface EdpAuditMapper {
    class SqlBuilder extends Sql {
        private void SELECT_ALL() {
            SELECT("T.ID id");
            SELECT("T.OP_USER opUserId");
            SELECT("U.NAME opUserName");
            SELECT("T.OP_TYPE opType");
            SELECT("T.OP_BEGIN opBegin");
            SELECT("T.OP_END opEnd");
            SELECT("T.OP_API opApi");
            SELECT("T.OP_DESC opDesc");
            SELECT("T.OP_ARGS opArgs");
            SELECT("T.STATUS status");
            SELECT("T.RESULT result");
        }

        private void FROM_SQL(EdpAuditLogQuery query) {
            FROM("PS_AUDIT_LOG T");
            JOIN("PS_AUTH_USER U ON U.ID = T.OP_USER");
        }

        private void FILTER_BY(EdpAuditLogQuery query) {

            if (!StringUtils.isEmpty(query.getOpUserId())) {
                WHERE("T.OP_USER = #{opUserId}");
            }

            if (!StringUtils.isEmpty(query.getOpUserName())) {
                WHERE("U.NAME LIKE " + SqlUtil.toSqlLikeString(query.getOpUserName()));
            }

            if (!StringUtils.isEmpty(query.getOpTimeFrom()) && !StringUtils.isEmpty(query.getOpTimeTo())) {
                WHERE("T.OP_BEGIN < #{opTimeFrom}");
                WHERE("T.OP_END > #{opTimeTo}");
            } else {
                // 默认获取前一个月内的日志
                WHERE("T.OP_BEGIN < SYSDATE");
                WHERE("T.OP_END > add_months(SYSDATE,-1)");
            }
        }

        static final String FIND_COUNT_SQL = "findCountSql";

        public String findCountSql(EdpAuditLogQuery query) {
            SELECT("COUNT(1) n ");
            FROM_SQL(query);
            FILTER_BY(query);
            return toString();
        }

        static final String FIND_PAGE_SQL = "findPageSql";

        public String findPageSql(EdpAuditLogQuery query) {
            SELECT_ALL();
            FROM_SQL(query);
            FILTER_BY(query);
            return SqlUtil.paging(toString(), query);
        }
    }

    @Insert({
        "INSERT INTO PS_AUDIT_LOG (",
        "ID,OP_USER,",
        "OP_TYPE,OP_BEGIN,",
        "OP_END,OP_API,",
        "OP_DESC,OP_ARGS,",
        "STATUS,RESULT",
        ") VALUES (",
        "#{id},#{opUserId},",
        "#{opType},#{opBegin},",
        "#{opEnd},#{opApi},",
        "#{opDesc},#{opArgs},",
        "#{status},#{result}",
        ")"
    })
    @SelectKey(statement = "SELECT SEQ_PS_AUDIT_LOG.NEXTVAL FROM DUAL", keyProperty = "id", before = true, resultType = Long.class)
    int insertAudit(EdpAuditLog edpAuditLog);

    /**
     * 物理删除 审计记录
     *
     * @param id 记录编号
     * @return 删除记录数
     */
    @Delete({
        "DELETE FROM PS_AUDIT_LOG WHERE ID = #{id}"
    })
    int deleteAudit(@Param("id") Long id);

    /**
     * 更新审计信息
     *
     * @param edpAuditLog 审计实体
     * @return 更新记录数
     */
    @Update({
        "UPDATE PS_AUDIT_LOG ",
        "SET OP_USER = #{opUserId},",
        "OP_TYPE = #{opType}, OP_BEGIN = #{opBegin},",
        "OP_END = #{opEnd}, OP_API = #{opApi},",
        "OP_DESC = #{opDesc}, OP_ARGS = #{opArgs},",
        "STATUS = #{status}, RESULT = #{result}",
        "WHERE ID = #{id}"
    })
    int updateAudit(EdpAuditLog edpAuditLog);

    /**
     * 查询分页条目
     *
     * @param query 查询条件
     * @return 返回结果条数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_COUNT_SQL)
    int findCount(EdpAuditLogQuery query);

    /**
     * 分页查询审计日志
     *
     * @param query 查询条件
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_PAGE_SQL)
    List<EdpAuditLog> findPage(EdpAuditLogQuery query);
}
