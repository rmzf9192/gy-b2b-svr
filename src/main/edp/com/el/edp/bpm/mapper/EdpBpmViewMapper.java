package com.el.edp.bpm.mapper;

import com.el.edp.bpm.domain.model.EdpBpmViewKey;
import com.el.edp.bpm.domain.model.EdpBpmViewMode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
public interface EdpBpmViewMapper {

    /**
     * @param procDefId 流程定义ID
     * @return 视图配置(JSON)
     */
    @Select({
        "select PROC_DEF_ID defId, TASK_DEF_ID nodeId, VIEW_MODE \"mode\" from PS_BPM_VIEW",
        " where PROC_DEF_ID=#{procDefId}",
    })
    List<EdpBpmViewKey> viewKeysOf(@Param("procDefId") String procDefId);

    /**
     * @param procDefId 流程定义ID
     * @param taskDefId 任务定义ID
     * @param viewMode  视图模式
     * @return 视图配置(JSON)
     */
    @Select({
        "select VIEW_CONF from PS_BPM_VIEW",
        " where PROC_DEF_ID=#{procDefId} and TASK_DEF_ID=#{taskDefId}",
        " and VIEW_MODE=#{viewMode}",
    })
    String viewConfOf(@Param("procDefId") String procDefId, @Param("taskDefId") String taskDefId,
                      @Param("viewMode") EdpBpmViewMode viewMode);

    /**
     * @param procDefId 流程定义ID
     * @param taskDefId 任务定义ID
     * @param viewMode  视图模式
     * @return 视图配置(JSON)
     */
    @Select({
        "select 1 from PS_BPM_VIEW",
        " where PROC_DEF_ID=#{procDefId} and TASK_DEF_ID=#{taskDefId}",
        " and VIEW_MODE=#{viewMode}",
    })
    Integer hasViewConf(@Param("procDefId") String procDefId, @Param("taskDefId") String taskDefId,
                        @Param("viewMode") EdpBpmViewMode viewMode);

    /**
     * @param procDefId 流程定义ID
     * @param taskDefId 任务定义ID
     * @param viewMode  视图模式
     * @param viewConf  视图配置(JSON)
     * @return 更新记录数
     */
    @Update({
        "update PS_BPM_VIEW set VIEW_CONF=#{viewConf}",
        " where PROC_DEF_ID=#{procDefId} and TASK_DEF_ID=#{taskDefId}",
        " and VIEW_MODE=#{viewMode}",
    })
    int updateViewConf(@Param("procDefId") String procDefId, @Param("taskDefId") String taskDefId,
                       @Param("viewMode") EdpBpmViewMode viewMode, @Param("viewConf") String viewConf);

    /**
     * @param procDefId 流程定义ID
     * @param taskDefId 任务定义ID
     * @param viewMode  视图模式
     * @param viewConf  视图配置(JSON)
     * @return 新增记录数
     */
    @Insert({
        "insert into PS_BPM_VIEW ( PROC_DEF_ID, TASK_DEF_ID, VIEW_MODE, VIEW_CONF )",
        "values ( #{procDefId}, #{taskDefId}, #{viewMode}, #{viewConf} )",
    })
    int addNewViewConf(@Param("procDefId") String procDefId, @Param("taskDefId") String taskDefId,
                       @Param("viewMode") EdpBpmViewMode viewMode, @Param("viewConf") String viewConf);

}
