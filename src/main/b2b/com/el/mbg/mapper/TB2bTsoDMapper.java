package com.el.mbg.mapper;

import com.el.mbg.domain.TB2bTsoD;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TB2bTsoDMapper {
    @Delete({
        "delete from B2B_TSO_D",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into B2B_TSO_D (" +
            /*"ID, " +*/
            /*"TENANT_ID, ",*/
        "TSO_ID, LINE_NO, WH_ID, ",
        "ITEM_ID, SKU_ID, LINE_TYPE, ",
        "QTY, UOM, REMARK, ",
        /*"CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, ",*/
        "AUDIT_DATA_VERSION, DELETE_FLAG, ITEM_CODE)",
        "values (" +
            /*"#{id,jdbcType=DECIMAL}, " +*/
            /*"#{tenantId,jdbcType=DECIMAL}, ",*/
        "#{tsoId,jdbcType=DECIMAL}, " +
            "#{lineNo,jdbcType=DECIMAL}, #{whId,jdbcType=DECIMAL}, ",
        "#{itemId,jdbcType=DECIMAL}, #{skuId,jdbcType=DECIMAL}, #{lineType,jdbcType=VARCHAR}, ",
        "#{qty,jdbcType=DECIMAL}, #{uom,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
       /* "#{createUserId,jdbcType=DECIMAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyUserId,jdbcType=DECIMAL}, #{modifyTime,jdbcType=TIMESTAMP}, ",*/
        "#{auditDataVersion,jdbcType=DECIMAL}, #{deleteFlag,jdbcType=DECIMAL}, #{itemCode,jdbcType=VARCHAR})"
    })
//    @SelectKey(statement="SELECT SEQ_B2B_TSO_D.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id FROM DUAL", keyProperty="id", before=false, resultType=Long.class)
    int insert(TB2bTsoD record);

    @Select({
        "select",
        "ID, TENANT_ID, TSO_ID, LINE_NO, WH_ID, ITEM_ID, SKU_ID, LINE_TYPE, QTY, UOM, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG",
        "from B2B_TSO_D",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="TSO_ID", property="tsoId", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="WH_ID", property="whId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.DECIMAL),
        @Result(column="SKU_ID", property="skuId", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_TYPE", property="lineType", jdbcType=JdbcType.VARCHAR),
        @Result(column="QTY", property="qty", jdbcType=JdbcType.DECIMAL),
        @Result(column="UOM", property="uom", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    TB2bTsoD selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, TSO_ID, LINE_NO, WH_ID, ITEM_ID, SKU_ID, LINE_TYPE, QTY, UOM, ",
        "REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG",
        "from B2B_TSO_D"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="TSO_ID", property="tsoId", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="WH_ID", property="whId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.DECIMAL),
        @Result(column="SKU_ID", property="skuId", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_TYPE", property="lineType", jdbcType=JdbcType.VARCHAR),
        @Result(column="QTY", property="qty", jdbcType=JdbcType.DECIMAL),
        @Result(column="UOM", property="uom", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL)
    })
    List<TB2bTsoD> selectAll();

    @Update({
        "update B2B_TSO_D",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "TSO_ID = #{tsoId,jdbcType=DECIMAL},",
          "LINE_NO = #{lineNo,jdbcType=DECIMAL},",
          "WH_ID = #{whId,jdbcType=DECIMAL},",
          "ITEM_ID = #{itemId,jdbcType=DECIMAL},",
          "SKU_ID = #{skuId,jdbcType=DECIMAL},",
          "LINE_TYPE = #{lineType,jdbcType=VARCHAR},",
          "QTY = #{qty,jdbcType=DECIMAL},",
          "UOM = #{uom,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TB2bTsoD record);
}
