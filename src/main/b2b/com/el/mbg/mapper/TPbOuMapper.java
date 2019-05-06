package com.el.mbg.mapper;

import com.el.mbg.domain.TPbOu;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbOuMapper {
    @Delete({
        "delete from PB_OU",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into PB_OU (ID, TENANT_ID, ",
        "OU_CODE, OU_NAME, ",
        "OU_ABBR, OU_TYPE, ",
        "OU_STATUS, PID, ",
        "OU_CURR, ADDR_NO, ",
        "REGION, REMARK, ",
        "OUTER_CODE, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG)",
        "values (#{id,jdbcType=DECIMAL}, #{tenantId,jdbcType=DECIMAL}, ",
        "#{ouCode,jdbcType=NVARCHAR}, #{ouName,jdbcType=NVARCHAR}, ",
        "#{ouAbbr,jdbcType=NVARCHAR}, #{ouType,jdbcType=NVARCHAR}, ",
        "#{ouStatus,jdbcType=NVARCHAR}, #{pid,jdbcType=DECIMAL}, ",
        "#{ouCurr,jdbcType=NVARCHAR}, #{addrNo,jdbcType=DECIMAL}, ",
        "#{region,jdbcType=NVARCHAR}, #{remark,jdbcType=NVARCHAR}, ",
        "#{outerCode,jdbcType=NVARCHAR}, #{createUserId,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT SEQ_PB_OU.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insert(TPbOu record);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, OU_NAME, OU_ABBR, OU_TYPE, OU_STATUS, PID, OU_CURR, ",
        "ADDR_NO, REGION, REMARK, OUTER_CODE, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG",
        "from PB_OU",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_NAME", property="ouName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_ABBR", property="ouAbbr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_TYPE", property="ouType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_STATUS", property="ouStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PID", property="pid", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CURR", property="ouCurr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="REGION", property="region", jdbcType=JdbcType.NVARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OUTER_CODE", property="outerCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    TPbOu selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, OU_NAME, OU_ABBR, OU_TYPE, OU_STATUS, PID, OU_CURR, ",
        "ADDR_NO, REGION, REMARK, OUTER_CODE, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG",
        "from PB_OU"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_NAME", property="ouName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_ABBR", property="ouAbbr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_TYPE", property="ouType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_STATUS", property="ouStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PID", property="pid", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_CURR", property="ouCurr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="REGION", property="region", jdbcType=JdbcType.NVARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OUTER_CODE", property="outerCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    List<TPbOu> selectAll();

    @Update({
        "update PB_OU",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "OU_CODE = #{ouCode,jdbcType=NVARCHAR},",
          "OU_NAME = #{ouName,jdbcType=NVARCHAR},",
          "OU_ABBR = #{ouAbbr,jdbcType=NVARCHAR},",
          "OU_TYPE = #{ouType,jdbcType=NVARCHAR},",
          "OU_STATUS = #{ouStatus,jdbcType=NVARCHAR},",
          "PID = #{pid,jdbcType=DECIMAL},",
          "OU_CURR = #{ouCurr,jdbcType=NVARCHAR},",
          "ADDR_NO = #{addrNo,jdbcType=DECIMAL},",
          "REGION = #{region,jdbcType=NVARCHAR},",
          "REMARK = #{remark,jdbcType=NVARCHAR},",
          "OUTER_CODE = #{outerCode,jdbcType=NVARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TPbOu record);
}