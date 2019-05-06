package com.el.mbg.mapper;

import com.el.mbg.domain.TPbAttach;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbAttachMapper {
    @Delete({
        "delete from PB_ATTACH",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into PB_ATTACH (TENANT_ID, ENTRY_CLS, ",
        "ENTRY_ID, ATTACH_NAME, ",
        "ATTACH_TYPE1, ATTACH_TYPE2, ",
        "ATTACH_TYPE3, ATTACH_DATA_TYPE, ",
        "ATTACH_PATH, UPLOAD_TIME, ",
        "UPLOAD_USER_ID, UPLOAD_USER_IP, ",
        "REMARK, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, OU_CODE)",
        "values (#{tenantId,jdbcType=INTEGER}, #{entryCls,jdbcType=VARCHAR}, ",
        "#{entryId,jdbcType=INTEGER}, #{attachName,jdbcType=VARCHAR}, ",
        "#{attachType1,jdbcType=VARCHAR}, #{attachType2,jdbcType=VARCHAR}, ",
        "#{attachType3,jdbcType=VARCHAR}, #{attachDataType,jdbcType=VARCHAR}, ",
        "#{attachPath,jdbcType=VARCHAR}, #{uploadTime,jdbcType=TIMESTAMP}, ",
        "#{uploadUserId,jdbcType=INTEGER}, #{uploadUserIp,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{createUserId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=INTEGER}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=INTEGER}, ",
        "#{deleteFlag,jdbcType=DECIMAL}, #{ouCode,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TPbAttach record);

    @Select({
        "select",
        "ID, TENANT_ID, ENTRY_CLS, ENTRY_ID, ATTACH_NAME, ATTACH_TYPE1, ATTACH_TYPE2, ",
        "ATTACH_TYPE3, ATTACH_DATA_TYPE, ATTACH_PATH, UPLOAD_TIME, UPLOAD_USER_ID, UPLOAD_USER_IP, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, OU_CODE",
        "from PB_ATTACH",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="ENTRY_CLS", property="entryCls", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENTRY_ID", property="entryId", jdbcType=JdbcType.INTEGER),
        @Result(column="ATTACH_NAME", property="attachName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE1", property="attachType1", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE2", property="attachType2", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE3", property="attachType3", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_DATA_TYPE", property="attachDataType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_PATH", property="attachPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPLOAD_TIME", property="uploadTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPLOAD_USER_ID", property="uploadUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="UPLOAD_USER_IP", property="uploadUserIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR)
    })
    TPbAttach selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, TENANT_ID, ENTRY_CLS, ENTRY_ID, ATTACH_NAME, ATTACH_TYPE1, ATTACH_TYPE2, ",
        "ATTACH_TYPE3, ATTACH_DATA_TYPE, ATTACH_PATH, UPLOAD_TIME, UPLOAD_USER_ID, UPLOAD_USER_IP, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, OU_CODE",
        "from PB_ATTACH"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="ENTRY_CLS", property="entryCls", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENTRY_ID", property="entryId", jdbcType=JdbcType.INTEGER),
        @Result(column="ATTACH_NAME", property="attachName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE1", property="attachType1", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE2", property="attachType2", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_TYPE3", property="attachType3", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_DATA_TYPE", property="attachDataType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATTACH_PATH", property="attachPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPLOAD_TIME", property="uploadTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPLOAD_USER_ID", property="uploadUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="UPLOAD_USER_IP", property="uploadUserIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR)
    })
    List<TPbAttach> selectAll();

    @Update({
        "update PB_ATTACH",
        "set TENANT_ID = #{tenantId,jdbcType=INTEGER},",
          "ENTRY_CLS = #{entryCls,jdbcType=VARCHAR},",
          "ENTRY_ID = #{entryId,jdbcType=INTEGER},",
          "ATTACH_NAME = #{attachName,jdbcType=VARCHAR},",
          "ATTACH_TYPE1 = #{attachType1,jdbcType=VARCHAR},",
          "ATTACH_TYPE2 = #{attachType2,jdbcType=VARCHAR},",
          "ATTACH_TYPE3 = #{attachType3,jdbcType=VARCHAR},",
          "ATTACH_DATA_TYPE = #{attachDataType,jdbcType=VARCHAR},",
          "ATTACH_PATH = #{attachPath,jdbcType=VARCHAR},",
          "UPLOAD_TIME = #{uploadTime,jdbcType=TIMESTAMP},",
          "UPLOAD_USER_ID = #{uploadUserId,jdbcType=INTEGER},",
          "UPLOAD_USER_IP = #{uploadUserIp,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=INTEGER},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=INTEGER},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
          "OU_CODE = #{ouCode,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TPbAttach record);
}