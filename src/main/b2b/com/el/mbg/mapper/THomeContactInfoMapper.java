package com.el.mbg.mapper;

import com.el.mbg.domain.THomeContactInfo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface THomeContactInfoMapper {
    @Delete({
        "delete from HOME_CONTACT_INFO",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into HOME_CONTACT_INFO (" +
//            "TENANT_ID," +
            " OU_CODE, ",
        "CUST_CODE, TITLE, ",
        "CONTACT, TEL, PHONE, ",
        "EMAIL, REMARK, DELETE_FLAG ",
//        "CREATE_USER_ID, MODIFY_USER_ID, ",
//        "CREATE_TIME, MODIFY_TIME, ",
//        "AUDIT_DATA_VERSION" +
            ")",
        "values (" +
//            "#{tenantId,jdbcType=VARCHAR}, " +
            "#{ouCode,jdbcType=VARCHAR}, ",
        "#{custCode,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{contact,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{deleteFlag,jdbcType=INTEGER} ",
//        "#{createUserId,jdbcType=INTEGER}, #{modifyUserId,jdbcType=INTEGER}, ",
//        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
//        "#{auditDataVersion,jdbcType=INTEGER}" +
            ")"
    })
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(THomeContactInfo record);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, CUST_CODE, TITLE, CONTACT, TEL, PHONE, EMAIL, REMARK, ",
        "DELETE_FLAG, CREATE_USER_ID, MODIFY_USER_ID, CREATE_TIME, MODIFY_TIME, AUDIT_DATA_VERSION",
        "from HOME_CONTACT_INFO",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEL", property="tel", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER)
    })
    THomeContactInfo selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, TENANT_ID, OU_CODE, CUST_CODE, TITLE, CONTACT, TEL, PHONE, EMAIL, REMARK, ",
        "DELETE_FLAG, CREATE_USER_ID, MODIFY_USER_ID, CREATE_TIME, MODIFY_TIME, AUDIT_DATA_VERSION",
        "from HOME_CONTACT_INFO"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.VARCHAR),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEL", property="tel", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.INTEGER)
    })
    List<THomeContactInfo> selectAll();

    @Update({
        "update HOME_CONTACT_INFO",
        "set " +
//            "TENANT_ID = #{tenantId,jdbcType=VARCHAR},",
          "OU_CODE = #{ouCode,jdbcType=VARCHAR},",
          "CUST_CODE = #{custCode,jdbcType=VARCHAR},",
          "TITLE = #{title,jdbcType=VARCHAR},",
          "CONTACT = #{contact,jdbcType=VARCHAR},",
          "TEL = #{tel,jdbcType=VARCHAR},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=INTEGER}",
//          "CREATE_USER_ID = #{createUserId,jdbcType=INTEGER},",
//          "MODIFY_USER_ID = #{modifyUserId,jdbcType=INTEGER},",
//          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
//          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
//          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(THomeContactInfo record);
}
