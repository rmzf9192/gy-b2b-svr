package com.el.edp.bpm.mapper;

import org.apache.ibatis.annotations.*;

import java.io.Reader;

/**
 * @author neo.pan
 * @since 2018/04/23
 */
public interface EdpBpmModelMapper {

    /**
     * @param procDefKey 流程定义KEY
     * @return 流程模型(XML)
     */
    @Select({
        "select PROC_MODEL from PS_BPM_MODEL",
        " where PROC_KEY=#{key}",
    })
    String modelOf(@Param("key") String procDefKey);

    /**
     * @param procDefKey 流程定义KEY
     * @return 流程模型(Stream)
     */
    @Select({
        "select PROC_MODEL from PS_BPM_MODEL",
        " where PROC_KEY=#{key}",
    })
    Reader modelReaderOf(@Param("key") String procDefKey);

    /**
     * @param procDefKey 流程定义KEY
     * @return 1 is true
     */
    @Select({
        "select 1 from PS_BPM_MODEL",
        " where PROC_KEY=#{key}",
    })
    Integer hasModel(@Param("key") String procDefKey);

    /**
     * @param procDefKey 流程定义KEY
     * @param procModel  流程定义(XML)
     * @return 更新记录数
     */
    @Update({
        "update PS_BPM_MODEL set PROC_MODEL=#{model}",
        " where PROC_KEY=#{key}",
    })
    int updateModel(@Param("key") String procDefKey, @Param("model") String procModel);

    /**
     * @param procDefKey 流程定义KEY
     * @param procModel  流程定义(XML)
     * @return 新增记录数
     */
    @Insert({
        "insert into PS_BPM_MODEL ( PROC_KEY, PROC_MODEL )",
        "values ( #{key}, #{model} )",
    })
    int addNewModel(@Param("key") String procDefKey, @Param("model") String procModel);

    /**
     * @param procDefKey 流程定义KEY
     * @return 删除记录数
     */
    @Delete({
        "delete from PS_BPM_MODEL",
        " where PROC_KEY=#{key}",
    })
    int deleteModel(@Param("key") String procDefKey);

}
