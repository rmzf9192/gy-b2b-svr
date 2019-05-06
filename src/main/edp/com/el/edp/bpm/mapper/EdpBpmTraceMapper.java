package com.el.edp.bpm.mapper;

import com.el.edp.bpm.domain.EdpBpmTrace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Simon.Hu
 * @since 2018/04/23
 */
public interface EdpBpmTraceMapper {

    /**
     * @param trace 任务履历
     * @return 新增结果
     */
    @Insert({
        "insert into PS_FLOW_TRACE(",
        "ID,PROC_INST_ID,",
        "TASK_ID,ASSIGNEE,",
        "RESULT,ADVICE,",
        "END_TIME",
        ") values (",
        "#{id,jdbcType=DECIMAL},#{prcId,jdbcType=NVARCHAR},",
        "#{taskId,jdbcType=NVARCHAR},#{assignee,jdbcType=NVARCHAR},",
        "#{result,jdbcType=NVARCHAR},#{advice,jdbcType=NVARCHAR},",
        "#{endTime,jdbcType=TIMESTAMP}",
        ")",
    })
    @SelectKey(statement="select SEQ_PS_FLOW_TRACE.NEXTVAL from DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insertBpmTrace(EdpBpmTrace trace);

    /**
     * @param taskId 任务编号
     * @return 是否已经插入的履历？
     */
    @Select({
        "select case when count(1) > 0 then 0 else 1 end",
        "  from PS_FLOW_TRACE where TASK_ID = #{taskId}",
    })
    boolean newBpmTrace(String taskId);

    /**
     * @param trace 任务履历
     * @return 修改结果
     */
    @Update({
        "update PS_FLOW_TRACE",
        "   set END_TIME = SYSDATE,",
        "       ASSIGNEE = #{assignee,jdbcType=NVARCHAR},",
        "       RESULT = #{result,jdbcType=NVARCHAR},",
        "       ADVICE = #{advice,jdbcType=NVARCHAR}",
        " where TASK_ID = #{taskId}",
    })
    int updateBpmTrace(EdpBpmTrace trace);

    /**
     * @param prcId 流程实例编号
     * @return 已审批任务履历
     */
    @Select({
        "select t.ID, t.TASK_ID taskId, t.RESULT, t.ADVICE, u.NAME assigneeName, t.END_TIME endTime",
        "  from PS_FLOW_TRACE t",
        "  JOIN PS_AUTH_USER u on u.ID = t.ASSIGNEE",
        " where t.PROC_INST_ID = #{prcId}",
        " order by t.TASK_ID desc"
    })
    List<EdpBpmTrace> tracesOf(String prcId);
}
