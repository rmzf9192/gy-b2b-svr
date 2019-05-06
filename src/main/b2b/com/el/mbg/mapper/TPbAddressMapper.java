package com.el.mbg.mapper;

import com.el.mbg.domain.TPbAddress;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbAddressMapper {
    @Delete({
        "delete from PB_ADDRESS",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into PB_ADDRESS (ID, TENANT_ID, ",
        "ADDR_NO, LINE_NO, ",
        "ADDRESS_TYPE, DEFAULT_FLAG, ",
        "ADDRESS_STATUS, CONT_PERSON, ",
        "MOBILE, MOBILE2, ",
        "TEL, TEL2, FAX, ",
        "EMAIL, EMAIL2, ",
        "COUNTRY, PROVINCE, ",
        "CITY, PREFECTURE, ",
        "DISTRICT, DETAILADDR, ",
        "ZIPCODE, WEBADDRESS, ",
        "WEIBO, WECHAT_MP, ",
        "SNS, SNS2, SNS3, ",
        "REMARK, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, POSITION, ",
        "PID, ADDR_NAME, ",
        "CUST_CODE, OU_CODE)",
        "values (#{id,jdbcType=DECIMAL}, #{tenantId,jdbcType=DECIMAL}, ",
        "#{addrNo,jdbcType=DECIMAL}, #{lineNo,jdbcType=DECIMAL}, ",
        "#{addressType,jdbcType=NVARCHAR}, #{defaultFlag,jdbcType=NCHAR}, ",
        "#{addressStatus,jdbcType=NVARCHAR}, #{contPerson,jdbcType=NVARCHAR}, ",
        "#{mobile,jdbcType=NCHAR}, #{mobile2,jdbcType=NVARCHAR}, ",
        "#{tel,jdbcType=NCHAR}, #{tel2,jdbcType=NVARCHAR}, #{fax,jdbcType=NVARCHAR}, ",
        "#{email,jdbcType=NVARCHAR}, #{email2,jdbcType=NVARCHAR}, ",
        "#{country,jdbcType=NVARCHAR}, #{province,jdbcType=NVARCHAR}, ",
        "#{city,jdbcType=NVARCHAR}, #{prefecture,jdbcType=NVARCHAR}, ",
        "#{district,jdbcType=NVARCHAR}, #{detailaddr,jdbcType=NVARCHAR}, ",
        "#{zipcode,jdbcType=NVARCHAR}, #{webaddress,jdbcType=NVARCHAR}, ",
        "#{weibo,jdbcType=NVARCHAR}, #{wechatMp,jdbcType=NVARCHAR}, ",
        "#{sns,jdbcType=NVARCHAR}, #{sns2,jdbcType=NVARCHAR}, #{sns3,jdbcType=NVARCHAR}, ",
        "#{remark,jdbcType=NVARCHAR}, #{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=DECIMAL}, #{createUserId,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{position,jdbcType=NVARCHAR}, ",
        "#{pid,jdbcType=DECIMAL}, #{addrName,jdbcType=NVARCHAR}, ",
        "#{custCode,jdbcType=NVARCHAR}, #{ouCode,jdbcType=NVARCHAR})"
    })
    @SelectKey(statement="SELECT SEQ_PB_ADDRESS.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insert(TPbAddress record);

    @Select({
        "select",
        "ID, TENANT_ID, ADDR_NO, LINE_NO, ADDRESS_TYPE, DEFAULT_FLAG, ADDRESS_STATUS, ",
        "CONT_PERSON, MOBILE, MOBILE2, TEL, TEL2, FAX, EMAIL, EMAIL2, COUNTRY, PROVINCE, ",
        "CITY, PREFECTURE, DISTRICT, DETAILADDR, ZIPCODE, WEBADDRESS, WEIBO, WECHAT_MP, ",
        "SNS, SNS2, SNS3, REMARK, AUDIT_DATA_VERSION, DELETE_FLAG, CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, POSITION, PID, ADDR_NAME, CUST_CODE, OU_CODE",
        "from PB_ADDRESS",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDRESS_TYPE", property="addressType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DEFAULT_FLAG", property="defaultFlag", jdbcType=JdbcType.NCHAR),
        @Result(column="ADDRESS_STATUS", property="addressStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CONT_PERSON", property="contPerson", jdbcType=JdbcType.NVARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.NCHAR),
        @Result(column="MOBILE2", property="mobile2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="TEL", property="tel", jdbcType=JdbcType.NCHAR),
        @Result(column="TEL2", property="tel2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="FAX", property="fax", jdbcType=JdbcType.NVARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.NVARCHAR),
        @Result(column="EMAIL2", property="email2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="COUNTRY", property="country", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PREFECTURE", property="prefecture", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DETAILADDR", property="detailaddr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ZIPCODE", property="zipcode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WEBADDRESS", property="webaddress", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WEIBO", property="weibo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WECHAT_MP", property="wechatMp", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS", property="sns", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS2", property="sns2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS3", property="sns3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="POSITION", property="position", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PID", property="pid", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NAME", property="addrName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.NVARCHAR)
    })
    TPbAddress selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, ADDR_NO, LINE_NO, ADDRESS_TYPE, DEFAULT_FLAG, ADDRESS_STATUS, ",
        "CONT_PERSON, MOBILE, MOBILE2, TEL, TEL2, FAX, EMAIL, EMAIL2, COUNTRY, PROVINCE, ",
        "CITY, PREFECTURE, DISTRICT, DETAILADDR, ZIPCODE, WEBADDRESS, WEIBO, WECHAT_MP, ",
        "SNS, SNS2, SNS3, REMARK, AUDIT_DATA_VERSION, DELETE_FLAG, CREATE_USER_ID, CREATE_TIME, ",
        "MODIFY_USER_ID, MODIFY_TIME, POSITION, PID, ADDR_NAME, CUST_CODE, OU_CODE",
        "from PB_ADDRESS"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NO", property="addrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="LINE_NO", property="lineNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDRESS_TYPE", property="addressType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DEFAULT_FLAG", property="defaultFlag", jdbcType=JdbcType.NCHAR),
        @Result(column="ADDRESS_STATUS", property="addressStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CONT_PERSON", property="contPerson", jdbcType=JdbcType.NVARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.NCHAR),
        @Result(column="MOBILE2", property="mobile2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="TEL", property="tel", jdbcType=JdbcType.NCHAR),
        @Result(column="TEL2", property="tel2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="FAX", property="fax", jdbcType=JdbcType.NVARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.NVARCHAR),
        @Result(column="EMAIL2", property="email2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="COUNTRY", property="country", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PREFECTURE", property="prefecture", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.NVARCHAR),
        @Result(column="DETAILADDR", property="detailaddr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ZIPCODE", property="zipcode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WEBADDRESS", property="webaddress", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WEIBO", property="weibo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="WECHAT_MP", property="wechatMp", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS", property="sns", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS2", property="sns2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SNS3", property="sns3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="POSITION", property="position", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PID", property="pid", jdbcType=JdbcType.DECIMAL),
        @Result(column="ADDR_NAME", property="addrName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="OU_CODE", property="ouCode", jdbcType=JdbcType.NVARCHAR)
    })
    List<TPbAddress> selectAll();

    @Update({
        "update PB_ADDRESS",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "ADDR_NO = #{addrNo,jdbcType=DECIMAL},",
          "LINE_NO = #{lineNo,jdbcType=DECIMAL},",
          "ADDRESS_TYPE = #{addressType,jdbcType=NVARCHAR},",
          "DEFAULT_FLAG = #{defaultFlag,jdbcType=NCHAR},",
          "ADDRESS_STATUS = #{addressStatus,jdbcType=NVARCHAR},",
          "CONT_PERSON = #{contPerson,jdbcType=NVARCHAR},",
          "MOBILE = #{mobile,jdbcType=NCHAR},",
          "MOBILE2 = #{mobile2,jdbcType=NVARCHAR},",
          "TEL = #{tel,jdbcType=NCHAR},",
          "TEL2 = #{tel2,jdbcType=NVARCHAR},",
          "FAX = #{fax,jdbcType=NVARCHAR},",
          "EMAIL = #{email,jdbcType=NVARCHAR},",
          "EMAIL2 = #{email2,jdbcType=NVARCHAR},",
          "COUNTRY = #{country,jdbcType=NVARCHAR},",
          "PROVINCE = #{province,jdbcType=NVARCHAR},",
          "CITY = #{city,jdbcType=NVARCHAR},",
          "PREFECTURE = #{prefecture,jdbcType=NVARCHAR},",
          "DISTRICT = #{district,jdbcType=NVARCHAR},",
          "DETAILADDR = #{detailaddr,jdbcType=NVARCHAR},",
          "ZIPCODE = #{zipcode,jdbcType=NVARCHAR},",
          "WEBADDRESS = #{webaddress,jdbcType=NVARCHAR},",
          "WEIBO = #{weibo,jdbcType=NVARCHAR},",
          "WECHAT_MP = #{wechatMp,jdbcType=NVARCHAR},",
          "SNS = #{sns,jdbcType=NVARCHAR},",
          "SNS2 = #{sns2,jdbcType=NVARCHAR},",
          "SNS3 = #{sns3,jdbcType=NVARCHAR},",
          "REMARK = #{remark,jdbcType=NVARCHAR},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "POSITION = #{position,jdbcType=NVARCHAR},",
          "PID = #{pid,jdbcType=DECIMAL},",
          "ADDR_NAME = #{addrName,jdbcType=NVARCHAR},",
          "CUST_CODE = #{custCode,jdbcType=NVARCHAR},",
          "OU_CODE = #{ouCode,jdbcType=NVARCHAR}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TPbAddress record);
}