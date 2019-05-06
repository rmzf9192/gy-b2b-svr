package com.el.mbg.mapper;

import com.el.mbg.domain.TPbAnnc;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbAnncMapper {
    @Delete({
        "delete from PB_ANNC",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into PB_ANNC (" +
//            "TENANT_ID, " +
            "ANNC_SUBJECT, ",
        "ANNC_CONTENT, ANNC_TARGET, ",
        "PUBLISH_TIME," +
//            " CREATE_USER_ID, ",
//        "CREATE_TIME, MODIFY_USER_ID, ",
//        "MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, OU_CODE, ",
        "ANNC_STATUS)",
        "values (" +
//            "#{tenantId,jdbcType=INTEGER}, " +
            "#{anncSubject,jdbcType=VARCHAR}, ",
        "#{anncContent,jdbcType=VARCHAR}, #{anncTarget,jdbcType=VARCHAR}, ",
        "#{publishTime,jdbcType=TIMESTAMP}, " +
//            "#{createUserId,jdbcType=INTEGER}, ",
//        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=INTEGER}, ",
//        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=INTEGER}, ",
        "#{deleteFlag,jdbcType=DECIMAL}, #{ouCode,jdbcType=VARCHAR}, ",
        "#{anncStatus,jdbcType=INTEGER})"
    })
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TPbAnnc record);

    @Select({
        "select",
        "ID, TENANT_ID, ANNC_SUBJECT, ANNC_CONTENT, ANNC_TARGET, PUBLISH_TIME, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, OU_CODE, ",
        "ANNC_STATUS",
        "from PB_ANNC",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="ANNC_SUBJECT", property="anncSubject", jdbcType=JdbcType.VARCHAR),
        @Result(column="ANNC_CONTENT", property="anncContent", jdbcType=JdbcType.BLOB),
        @Result(column="ANNC_TARGET", property="anncTarget", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUBLISH_TIME", property="publishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ANNC_STATUS", property="anncStatus", jdbcType=JdbcType.INTEGER)
    })
    TPbAnnc selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, TENANT_ID, ANNC_SUBJECT, ANNC_CONTENT, ANNC_TARGET, PUBLISH_TIME, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, OU_CODE, ",
        "ANNC_STATUS",
        "from PB_ANNC"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="ANNC_SUBJECT", property="anncSubject", jdbcType=JdbcType.VARCHAR),
        @Result(column="ANNC_CONTENT", property="anncContent", jdbcType=JdbcType.BLOB),
        @Result(column="ANNC_TARGET", property="anncTarget", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUBLISH_TIME", property="publishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ANNC_STATUS", property="anncStatus", jdbcType=JdbcType.INTEGER)
    })
    List<TPbAnnc> selectAll();

    @Update({
        "update PB_ANNC",
        "set " +
//            "TENANT_ID = #{tenantId,jdbcType=INTEGER},",
          "ANNC_SUBJECT = #{anncSubject,jdbcType=VARCHAR},",
          "ANNC_CONTENT = #{anncContent,jdbcType=BLOB},",
          "ANNC_TARGET = #{anncTarget,jdbcType=VARCHAR},",
          "PUBLISH_TIME = #{publishTime,jdbcType=TIMESTAMP},",
//          "CREATE_USER_ID = #{createUserId,jdbcType=INTEGER},",
//          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
//          "MODIFY_USER_ID = #{modifyUserId,jdbcType=INTEGER},",
//          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
//          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=INTEGER},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
          "OU_CODE = #{ouCode,jdbcType=VARCHAR},",
          "ANNC_STATUS = #{anncStatus,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TPbAnnc record);
}
