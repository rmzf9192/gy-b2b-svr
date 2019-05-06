package com.el.mbg.mapper;

import com.el.mbg.domain.TB2bTso;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TB2bTsoMapper {
    @Delete({
        "delete from B2B_TSO",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into B2B_TSO (" +
           /* "ID," +*/
           /* " TENANT_ID, ",*/
        "OU_ID, ORG_ID, DOC_NO, ",
        "DOC_TYPE, DOC_STATUS, ",
        "DOC_TIME, CUST_ID, ",
        "PAYMENT_TERM, CURR_CODE, ",
        "TAX_CODE, DELIVER_WAY, ",
        "DELIVER_CONTACT, DELIVER_ADDRESS_ID, ",
        "REMARK, " +
            /*"CREATE_USER_ID, ", "CREATE_TIME, MODIFY_USER_ID, ", "MODIFY_TIME," +*/
            " AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, OU_CODE, CUST_CODE)",
        "values (" +
            /*"#{id,jdbcType=DECIMAL}, " +*/
            /*"#{tenantId,jdbcType=DECIMAL}, ",*/
        "#{ouId,jdbcType=DECIMAL}, #{orgId,jdbcType=DECIMAL}, #{docNo,jdbcType=VARCHAR}, ",
        "#{docType,jdbcType=VARCHAR}, #{docStatus,jdbcType=VARCHAR}, ",
        "#{docTime,jdbcType=TIMESTAMP}, #{custId,jdbcType=DECIMAL}, ",
        "#{paymentTerm,jdbcType=VARCHAR}, #{currCode,jdbcType=VARCHAR}, ",
        "#{taxCode,jdbcType=VARCHAR}, #{deliverWay,jdbcType=VARCHAR}, ",
        "#{deliverContact,jdbcType=VARCHAR}, #{deliverAddressId,jdbcType=DECIMAL}, ",
        "#{remark,jdbcType=VARCHAR}, " +
           /* " #{createUserId,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, " +*/
            "#{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=DECIMAL}, #{ouCode,jdbcType=VARCHAR}, #{custCode,jdbcType=VARCHAR})"
    })
   // @SelectKey(statement="SELECT SEQ_B2B_TSO.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty="id", before=false, resultType=Long.class)
    int insert(TB2bTso record);

    @Select({
        "select",
        "ID, TENANT_ID, OU_ID, ORG_ID, DOC_NO, DOC_TYPE, DOC_STATUS, DOC_TIME, CUST_ID, ",
        "PAYMENT_TERM, CURR_CODE, TAX_CODE, DELIVER_WAY, DELIVER_CONTACT, DELIVER_ADDRESS_ID, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG",
        "from B2B_TSO",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_ID", property="ouId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.DECIMAL),
        @Result(column="DOC_NO", property="docNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_TYPE", property="docType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_STATUS", property="docStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_TIME", property="docTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CUST_ID", property="custId", jdbcType=JdbcType.DECIMAL),
        @Result(column="PAYMENT_TERM", property="paymentTerm", jdbcType=JdbcType.VARCHAR),
        @Result(column="CURR_CODE", property="currCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAX_CODE", property="taxCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_WAY", property="deliverWay", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_CONTACT", property="deliverContact", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_ADDRESS_ID", property="deliverAddressId", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    TB2bTso selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, OU_ID, ORG_ID, DOC_NO, DOC_TYPE, DOC_STATUS, DOC_TIME, CUST_ID, ",
        "PAYMENT_TERM, CURR_CODE, TAX_CODE, DELIVER_WAY, DELIVER_CONTACT, DELIVER_ADDRESS_ID, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG",
        "from B2B_TSO"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_ID", property="ouId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.DECIMAL),
        @Result(column="DOC_NO", property="docNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_TYPE", property="docType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_STATUS", property="docStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOC_TIME", property="docTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CUST_ID", property="custId", jdbcType=JdbcType.DECIMAL),
        @Result(column="PAYMENT_TERM", property="paymentTerm", jdbcType=JdbcType.VARCHAR),
        @Result(column="CURR_CODE", property="currCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAX_CODE", property="taxCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_WAY", property="deliverWay", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_CONTACT", property="deliverContact", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELIVER_ADDRESS_ID", property="deliverAddressId", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    List<TB2bTso> selectAll();

    @Update({
        "update B2B_TSO",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "OU_ID = #{ouId,jdbcType=DECIMAL},",
          "ORG_ID = #{orgId,jdbcType=DECIMAL},",
          "DOC_NO = #{docNo,jdbcType=VARCHAR},",
          "DOC_TYPE = #{docType,jdbcType=VARCHAR},",
          "DOC_STATUS = #{docStatus,jdbcType=VARCHAR},",
          "DOC_TIME = #{docTime,jdbcType=TIMESTAMP},",
          "CUST_ID = #{custId,jdbcType=DECIMAL},",
          "PAYMENT_TERM = #{paymentTerm,jdbcType=VARCHAR},",
          "CURR_CODE = #{currCode,jdbcType=VARCHAR},",
          "TAX_CODE = #{taxCode,jdbcType=VARCHAR},",
          "DELIVER_WAY = #{deliverWay,jdbcType=VARCHAR},",
          "DELIVER_CONTACT = #{deliverContact,jdbcType=VARCHAR},",
          "DELIVER_ADDRESS_ID = #{deliverAddressId,jdbcType=DECIMAL},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TB2bTso record);
}
