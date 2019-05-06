package com.el.edp.ews.mapper;

import com.el.core.jdbc.Sql;
import com.el.core.util.SqlUtil;
import com.el.edp.ews.api.EdpReminderQuery;
import com.el.edp.ews.domain.EdpReminder;
import com.el.edp.sec.entity.EdpReminderEntity;
import com.el.edp.util.EdpCodeName;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 18/4/20
 */
public interface EdpReminderMapper {

    class SqlBuilder extends Sql {

        private static final String REMINDERS_BY_SQL = "remindersBySql";

        public String remindersBySql(@Param("q") EdpReminderQuery query) {
            SELECT("ID");
            SELECT("REMIND_CLASS remindClass");
            SELECT("REMIND_TYPE remindType");
            SELECT("REMIND_DAYS_1 remindDays1");
            SELECT("REMIND_DAYS_2 remindDays2");
            SELECT("REMIND_DAYS_3 remindDays3");
            SELECT("REMIND_DAYS_4 remindDays4");
            SELECT("REMIND_DAYS_5 remindDays5");
            SELECT("DELETE_FLAG deleteFlag");
            FROM("PB_REMIND_SET");

            filterBy(query);
            ORDER_BY(query);
            return toPagingSql(query);
        }

        private static final String REMINDER_COUNT_BY_SQL = "reminderCountBySql";

        public String reminderCountBySql(@Param("q") EdpReminderQuery query) {
            FROM("PB_REMIND_SET");
            filterBy(query);
            return toCountSql();
        }

        private void filterBy(EdpReminderQuery query) {
            val reminderLike = query.getReminderLike();
            if (StringUtils.hasText(reminderLike)) {
                WHERE("(REMIND_CLASS like " + SqlUtil.toSqlLikeString(reminderLike)
                    + " or REMIND_TYPE like " + SqlUtil.toSqlLikeString(reminderLike)
                    + ")");
            }
        }

        private static final String REMINDER_IF_EXISTS_SQL = "reminderIfExistsSql";

        public String reminderIfExistsSql(EdpReminderEntity payload) {
            SELECT("case when count(0) > 0 then 1 else 0 end");
            FROM("PB_REMIND_SET");
            WHERE("REMIND_TYPE = #{remindType}");

            if (payload.getId() != null) {
                WHERE("ID <> #{id}");
            }

            return toString();
        }
    }

    /**
     * @param query 检索条件（分页支持）
     * @return 预警提醒检索的总数
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.REMINDER_COUNT_BY_SQL)
    int reminderCountBy(@Param("q") EdpReminderQuery query);

    /**
     * @param query 检索条件（分页支持）
     * @return 预警提醒一览
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.REMINDERS_BY_SQL)
    List<EdpReminder> remindersBy(@Param("q") EdpReminderQuery query);

    /**
     * @param id 提醒主键
     * @return 预警提醒
     */
    @Select({
        "select ID, REMIND_CLASS remindClass, REMIND_TYPE remindType,",
        "       REMIND_DAYS_1 remindDays1, REMIND_DAYS_2 remindDays2, REMIND_DAYS_3 remindDays3,",
        "       REMIND_DAYS_4 remindDays4, REMIND_DAYS_5 remindDays5",
        "  from PB_REMIND_SET",
        " where ID = #{id}",
    })
    EdpReminder reminderBy(long id);

    /**
     * @param entity 预警提醒对象
     * @return 是否已存在预警提醒？
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.REMINDER_IF_EXISTS_SQL)
    boolean reminderIfExists(EdpReminderEntity entity);

    /**
     * 新增 预警提醒 记录
     *
     * @param entity 预警提醒对象
     * @return {新增记录数}
     */
    @Insert({
        "insert into PB_REMIND_SET (",
        "ID,REMIND_CLASS,",
        "REMIND_TYPE,REMIND_DAYS_1,",
        "REMIND_DAYS_2,REMIND_DAYS_3,",
        "REMIND_DAYS_4,REMIND_DAYS_5",
        ") values (",
        "#{id,jdbcType=DECIMAL},#{remindClass,jdbcType=NVARCHAR},",
        "#{remindType,jdbcType=NVARCHAR},#{remindDays1,jdbcType=DECIMAL},",
        "#{remindDays2,jdbcType=DECIMAL},#{remindDays3,jdbcType=DECIMAL},",
        "#{remindDays4,jdbcType=DECIMAL},#{remindDays5,jdbcType=DECIMAL}",
        ")"
    })
    @SelectKey(statement="select SEQ_PB_REMIND_SET.NEXTVAL from DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insertReminder(EdpReminderEntity entity);

    /**
     * 更新 预警提醒 记录
     *
     * @param entity 预警提醒对象
     * @return {更新记录数}
     */
    @Update({
        "update PB_REMIND_SET",
        "   set REMIND_CLASS = #{remindClass,jdbcType=NVARCHAR},",
        "       REMIND_DAYS_1 = #{remindDays1,jdbcType=DECIMAL},",
        "       REMIND_DAYS_2 = #{remindDays2,jdbcType=DECIMAL},",
        "       REMIND_DAYS_3 = #{remindDays3,jdbcType=DECIMAL},",
        "       REMIND_DAYS_4 = #{remindDays4,jdbcType=DECIMAL},",
        "       REMIND_DAYS_5 = #{remindDays5,jdbcType=DECIMAL}",
        " where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateReminder(EdpReminderEntity entity);

    /**
     * 逻辑删除 预警提醒 记录
     *
     * @param id 预警提醒主键
     * @return {删除记录数}
     */
    @Update({
        "update PB_REMIND_SET",
        "   set DELETE_FLAG = #{flag,jdbcType=DECIMAL}",
        " where ID = #{id,jdbcType=DECIMAL}"
    })
    int toggleReminder(@Param("id") long id, @Param("flag") String flag);

    /**
     * @param id 预警提醒主键
     * @return 预警提醒是否被流程角色使用中？
     */
    @Select({
        "select case when count(0) > 0 then 1 else 0 end",
        "  from PS_FLOW_ROLE",
        " where REMIND_ID = #{id}",
    })
    boolean reminderInService(long id);

    /**
     * @return 预警提醒列表
     */
    @Select({
        "select ID, REMIND_TYPE code, REMIND_CLASS name ",
        "  from PB_REMIND_SET",
        " where DELETE_FLAG = 0",
        " order by REMIND_TYPE"
    })
    List<EdpCodeName> reminders();
}
