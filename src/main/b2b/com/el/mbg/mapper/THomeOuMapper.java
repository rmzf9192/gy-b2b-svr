package com.el.mbg.mapper;

import com.el.mbg.domain.THomeOu;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface THomeOuMapper {
    @Delete({
        "delete from HOME_OU",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into HOME_OU (" +
//            "TENANT_ID, " +
            "OU_CODE, ",
        "OU_NAME, BANK, BANK_ACC, ",
        "TAX_NO, OU_ADDR, OU_ADDR2, ",
        "OU_ADDR3, WAREHOUSE_ADDR, ",
        "WAREHOUSE_ADDR2, WAREHOUSE_ADDR3, ",
        "image_url, image_url2, ",
        "image_url3, image_url4, ",
        "image_url5, REMARK, ",
        "DELETE_FLAG " +
//            "CREATE_USER_ID, ",
//        "MODIFY_USER_ID, CREATE_TIME, ",
//        "MODIFY_TIME, AUDIT_DATA_VERSION" +
            ")",
        "values (" +
//            "#{tenantId,jdbcType=INTEGER}, " +
            "#{ouCode,jdbcType=VARCHAR}, ",
        "#{ouName,jdbcType=VARCHAR}, #{bank,jdbcType=VARCHAR}, #{bankAcc,jdbcType=VARCHAR}, ",
        "#{taxNo,jdbcType=VARCHAR}, #{ouAddr,jdbcType=VARCHAR}, #{ouAddr2,jdbcType=VARCHAR}, ",
        "#{ouAddr3,jdbcType=VARCHAR}, #{warehouseAddr,jdbcType=VARCHAR}, ",
        "#{warehouseAddr2,jdbcType=VARCHAR}, #{warehouseAddr3,jdbcType=VARCHAR}, ",
        "#{imageUrl,jdbcType=VARCHAR}, #{imageUrl2,jdbcType=VARCHAR}, ",
        "#{imageUrl3,jdbcType=VARCHAR}, #{imageUrl4,jdbcType=VARCHAR}, ",
        "#{imageUrl5,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{deleteFlag,jdbcType=INTEGER}" +
//            " #{createUserId,jdbcType=INTEGER}, ",
//        "#{modifyUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
//        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=INTEGER}" +
            ")"
    })
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(THomeOu record);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, OU_NAME, BANK, BANK_ACC, TAX_NO, OU_ADDR, OU_ADDR2, ",
        "OU_ADDR3, WAREHOUSE_ADDR, WAREHOUSE_ADDR2, WAREHOUSE_ADDR3, image_url, image_url2, ",
        "image_url3, image_url4, image_url5, REMARK, DELETE_FLAG, CREATE_USER_ID, MODIFY_USER_ID, ",
        "CREATE_TIME, MODIFY_TIME, AUDIT_DATA_VERSION",
        "from HOME_OU",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_NAME", property="ouName", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK", property="bank", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_ACC", property="bankAcc", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAX_NO", property="taxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR", property="ouAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR2", property="ouAddr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR3", property="ouAddr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR", property="warehouseAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR2", property="warehouseAddr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR3", property="warehouseAddr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url2", property="imageUrl2", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url3", property="imageUrl3", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url4", property="imageUrl4", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url5", property="imageUrl5", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER)
    })
    THomeOu selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, OU_NAME, BANK, BANK_ACC, TAX_NO, OU_ADDR, OU_ADDR2, ",
        "OU_ADDR3, WAREHOUSE_ADDR, WAREHOUSE_ADDR2, WAREHOUSE_ADDR3, image_url, image_url2, ",
        "image_url3, image_url4, image_url5, REMARK, DELETE_FLAG, CREATE_USER_ID, MODIFY_USER_ID, ",
        "CREATE_TIME, MODIFY_TIME, AUDIT_DATA_VERSION",
        "from HOME_OU"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.INTEGER),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_NAME", property="ouName", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK", property="bank", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_ACC", property="bankAcc", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAX_NO", property="taxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR", property="ouAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR2", property="ouAddr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_ADDR3", property="ouAddr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR", property="warehouseAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR2", property="warehouseAddr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="WAREHOUSE_ADDR3", property="warehouseAddr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url2", property="imageUrl2", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url3", property="imageUrl3", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url4", property="imageUrl4", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url5", property="imageUrl5", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER)
    })
    List<THomeOu> selectAll();

    @Update({
        "update HOME_OU",
        "set " +
//            "TENANT_ID = #{tenantId,jdbcType=INTEGER},",
          "OU_CODE = #{ouCode,jdbcType=VARCHAR},",
          "OU_NAME = #{ouName,jdbcType=VARCHAR},",
          "BANK = #{bank,jdbcType=VARCHAR},",
          "BANK_ACC = #{bankAcc,jdbcType=VARCHAR},",
          "TAX_NO = #{taxNo,jdbcType=VARCHAR},",
          "OU_ADDR = #{ouAddr,jdbcType=VARCHAR},",
          "OU_ADDR2 = #{ouAddr2,jdbcType=VARCHAR},",
          "OU_ADDR3 = #{ouAddr3,jdbcType=VARCHAR},",
          "WAREHOUSE_ADDR = #{warehouseAddr,jdbcType=VARCHAR},",
          "WAREHOUSE_ADDR2 = #{warehouseAddr2,jdbcType=VARCHAR},",
          "WAREHOUSE_ADDR3 = #{warehouseAddr3,jdbcType=VARCHAR},",
          "image_url = #{imageUrl,jdbcType=VARCHAR},",
          "image_url2 = #{imageUrl2,jdbcType=VARCHAR},",
          "image_url3 = #{imageUrl3,jdbcType=VARCHAR},",
          "image_url4 = #{imageUrl4,jdbcType=VARCHAR},",
          "image_url5 = #{imageUrl5,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=INTEGER}",
//          "CREATE_USER_ID = #{createUserId,jdbcType=INTEGER},",
//          "MODIFY_USER_ID = #{modifyUserId,jdbcType=INTEGER},",
//          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
//          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
//          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(THomeOu record);
}
