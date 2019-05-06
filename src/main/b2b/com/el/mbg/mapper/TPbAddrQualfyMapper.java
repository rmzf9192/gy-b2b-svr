package com.el.mbg.mapper;

import com.el.mbg.domain.TPbAddrQualfy;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbAddrQualfyMapper {
    @Delete({
        "delete from PB_ADDR_QUALIFY",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into PB_ADDR_QUALIFY (ID, TENANT_ID, ",
        "ADDR_NO, LINE_NO, ",
        "QUALIFY_TYPE, QUALIFY_NAME, ",
        "QUALIFY_NO, QUALIFY_ADDRESS, ",
        "VALID_FROM, VALID_TO, ",
        "NEXT_CHECK_DATE, REMARK, ",
        "CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, ",
        "AUDIT_DATA_VERSION, DELETE_FLAG, ",
        "ATTACH_REPO, VALID_LV1, ",
        "VALID_LV2, VALID_LV3, ",
        "CERT_STATUS)",
        "values (#{id,jdbcType=DECIMAL}, #{tenantId,jdbcType=DECIMAL}, ",
        "#{addrNo,jdbcType=NVARCHAR}, #{lineNo,jdbcType=DECIMAL}, ",
        "#{qualifyType,jdbcType=NVARCHAR}, #{qualifyName,jdbcType=NVARCHAR}, ",
        "#{qualifyNo,jdbcType=NVARCHAR}, #{qualifyAddress,jdbcType=NVARCHAR}, ",
        "#{validFrom,jdbcType=TIMESTAMP}, #{validTo,jdbcType=TIMESTAMP}, ",
        "#{nextCheckDate,jdbcType=TIMESTAMP}, #{remark,jdbcType=NVARCHAR}, ",
        "#{createUserId,jdbcType=DECIMAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyUserId,jdbcType=DECIMAL}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{auditDataVersion,jdbcType=DECIMAL}, #{deleteFlag,jdbcType=DECIMAL}, ",
        "#{attachRepo,jdbcType=NVARCHAR}, #{validLv1,jdbcType=NVARCHAR}, ",
        "#{validLv2,jdbcType=NVARCHAR}, #{validLv3,jdbcType=NVARCHAR}, ",
        "#{certStatus,jdbcType=NVARCHAR})"
    })
    @SelectKey(statement="SELECT SEQ_PB_ADDR_QUALIFY.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insert(TPbAddrQualfy record);

    @Select({
        "select",
        "ID, TENANT_ID, ADDR_NO, LINE_NO, QUALIFY_TYPE, QUALIFY_NAME, QUALIFY_NO, QUALIFY_ADDRESS, ",
        "VALID_FROM, VALID_TO, NEXT_CHECK_DATE, REMARK, CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, ATTACH_REPO, VALID_LV1, ",
        "VALID_LV2, VALID_LV3, CERT_STATUS",
        "from PB_ADDR_QUALIFY",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="QUALIFY_TYPE", property="qualifyType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_NAME", property="qualifyName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_NO", property="qualifyNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_ADDRESS", property="qualifyAddress", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_FROM", property="validFrom", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="VALID_TO", property="validTo", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="NEXT_CHECK_DATE", property="nextCheckDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="ATTACH_REPO", property="attachRepo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV1", property="validLv1", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV2", property="validLv2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV3", property="validLv3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CERT_STATUS", property="certStatus", jdbcType=JdbcType.NVARCHAR)
    })
    TPbAddrQualfy selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, ADDR_NO, LINE_NO, QUALIFY_TYPE, QUALIFY_NAME, QUALIFY_NO, QUALIFY_ADDRESS, ",
        "VALID_FROM, VALID_TO, NEXT_CHECK_DATE, REMARK, CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, ATTACH_REPO, VALID_LV1, ",
        "VALID_LV2, VALID_LV3, CERT_STATUS",
        "from PB_ADDR_QUALIFY"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="QUALIFY_TYPE", property="qualifyType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_NAME", property="qualifyName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_NO", property="qualifyNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="QUALIFY_ADDRESS", property="qualifyAddress", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_FROM", property="validFrom", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="VALID_TO", property="validTo", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="NEXT_CHECK_DATE", property="nextCheckDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="ATTACH_REPO", property="attachRepo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV1", property="validLv1", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV2", property="validLv2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_LV3", property="validLv3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CERT_STATUS", property="certStatus", jdbcType=JdbcType.NVARCHAR)
    })
    List<TPbAddrQualfy> selectAll();

    @Update({
        "update PB_ADDR_QUALIFY",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "ADDR_NO = #{addrNo,jdbcType=NVARCHAR},",
          "LINE_NO = #{lineNo,jdbcType=DECIMAL},",
          "QUALIFY_TYPE = #{qualifyType,jdbcType=NVARCHAR},",
          "QUALIFY_NAME = #{qualifyName,jdbcType=NVARCHAR},",
          "QUALIFY_NO = #{qualifyNo,jdbcType=NVARCHAR},",
          "QUALIFY_ADDRESS = #{qualifyAddress,jdbcType=NVARCHAR},",
          "VALID_FROM = #{validFrom,jdbcType=TIMESTAMP},",
          "VALID_TO = #{validTo,jdbcType=TIMESTAMP},",
          "NEXT_CHECK_DATE = #{nextCheckDate,jdbcType=TIMESTAMP},",
          "REMARK = #{remark,jdbcType=NVARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
          "ATTACH_REPO = #{attachRepo,jdbcType=NVARCHAR},",
          "VALID_LV1 = #{validLv1,jdbcType=NVARCHAR},",
          "VALID_LV2 = #{validLv2,jdbcType=NVARCHAR},",
          "VALID_LV3 = #{validLv3,jdbcType=NVARCHAR},",
          "CERT_STATUS = #{certStatus,jdbcType=NVARCHAR}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TPbAddrQualfy record);
}