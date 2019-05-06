package com.el.mbg.mapper;

import com.el.mbg.domain.TPbItemSaleprice;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TPbItemSalepriceMapper {
    @Delete({
        "delete from PB_ITEM_SALEPRICE",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into PB_ITEM_SALEPRICE (ID, TENANT_ID, ",
        "OU_ID, ORG_ID, PRICE_TYPE, ",
        "PRICE_TYPE2, PRICE_TYPE3, ",
        "ITEM_ID, CUST_GROUP, ",
        "CUST_ID, SALE_REGION, ",
        "PRICE_GROUP, SHIPTO_ADDR_NO, ",
        "PRICE, UOM, PRICE_UOM, ",
        "CURR_CODE, VALID_FROM, ",
        "VALID_TO, FROM_QTY, ",
        "TO_QTY, TOLERANCE, ",
        "REMARK, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, ",
        "MODIFY_TIME, AUDIT_DATA_VERSION, ",
        "DELETE_FLAG, ITEM_C1, ",
        "ITEM_C2, ITEM_C3, ",
        "PRICE_STATUS, ITEM_CODE, ",
        "CUST_CODE)",
        "values (#{id,jdbcType=DECIMAL}, #{tenantId,jdbcType=DECIMAL}, ",
        "#{ouId,jdbcType=DECIMAL}, #{orgId,jdbcType=DECIMAL}, #{priceType,jdbcType=NVARCHAR}, ",
        "#{priceType2,jdbcType=NVARCHAR}, #{priceType3,jdbcType=NVARCHAR}, ",
        "#{itemId,jdbcType=DECIMAL}, #{custGroup,jdbcType=NVARCHAR}, ",
        "#{custId,jdbcType=DECIMAL}, #{saleRegion,jdbcType=NVARCHAR}, ",
        "#{priceGroup,jdbcType=NVARCHAR}, #{shiptoAddrNo,jdbcType=DECIMAL}, ",
        "#{price,jdbcType=DECIMAL}, #{uom,jdbcType=NVARCHAR}, #{priceUom,jdbcType=NVARCHAR}, ",
        "#{currCode,jdbcType=NVARCHAR}, #{validFrom,jdbcType=TIMESTAMP}, ",
        "#{validTo,jdbcType=TIMESTAMP}, #{fromQty,jdbcType=DECIMAL}, ",
        "#{toQty,jdbcType=DECIMAL}, #{tolerance,jdbcType=DECIMAL}, ",
        "#{remark,jdbcType=NVARCHAR}, #{createUserId,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=DECIMAL}, #{itemC1,jdbcType=NVARCHAR}, ",
        "#{itemC2,jdbcType=NVARCHAR}, #{itemC3,jdbcType=NVARCHAR}, ",
        "#{priceStatus,jdbcType=NVARCHAR}, #{itemCode,jdbcType=NVARCHAR}, ",
        "#{custCode,jdbcType=NVARCHAR})"
    })
    @SelectKey(statement="SELECT SEQ_PB_ITEM_SALEPRICE.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insert(TPbItemSaleprice record);

    @Select({
        "select",
        "ID, TENANT_ID, OU_ID, ORG_ID, PRICE_TYPE, PRICE_TYPE2, PRICE_TYPE3, ITEM_ID, ",
        "CUST_GROUP, CUST_ID, SALE_REGION, PRICE_GROUP, SHIPTO_ADDR_NO, PRICE, UOM, PRICE_UOM, ",
        "CURR_CODE, VALID_FROM, VALID_TO, FROM_QTY, TO_QTY, TOLERANCE, REMARK, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, ITEM_C1, ",
        "ITEM_C2, ITEM_C3, PRICE_STATUS, ITEM_CODE, CUST_CODE",
        "from PB_ITEM_SALEPRICE",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_ID", property="ouId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRICE_TYPE", property="priceType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_TYPE2", property="priceType2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_TYPE3", property="priceType3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CUST_GROUP", property="custGroup", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_ID", property="custId", jdbcType=JdbcType.DECIMAL),
        @Result(column="SALE_REGION", property="saleRegion", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_GROUP", property="priceGroup", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SHIPTO_ADDR_NO", property="shiptoAddrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRICE", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="UOM", property="uom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_UOM", property="priceUom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CURR_CODE", property="currCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_FROM", property="validFrom", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="VALID_TO", property="validTo", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FROM_QTY", property="fromQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="TO_QTY", property="toQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOLERANCE", property="tolerance", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="ITEM_C1", property="itemC1", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_C2", property="itemC2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_C3", property="itemC3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_STATUS", property="priceStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_CODE", property="itemCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.NVARCHAR)
    })
    TPbItemSaleprice selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, OU_ID, ORG_ID, PRICE_TYPE, PRICE_TYPE2, PRICE_TYPE3, ITEM_ID, ",
        "CUST_GROUP, CUST_ID, SALE_REGION, PRICE_GROUP, SHIPTO_ADDR_NO, PRICE, UOM, PRICE_UOM, ",
        "CURR_CODE, VALID_FROM, VALID_TO, FROM_QTY, TO_QTY, TOLERANCE, REMARK, CREATE_USER_ID, ",
        "CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, AUDIT_DATA_VERSION, DELETE_FLAG, ITEM_C1, ",
        "ITEM_C2, ITEM_C3, PRICE_STATUS, ITEM_CODE, CUST_CODE",
        "from PB_ITEM_SALEPRICE"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="TENANT_ID", property="tenantId", jdbcType=JdbcType.DECIMAL),
        @Result(column="OU_ID", property="ouId", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRICE_TYPE", property="priceType", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_TYPE2", property="priceType2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_TYPE3", property="priceType3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CUST_GROUP", property="custGroup", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_ID", property="custId", jdbcType=JdbcType.DECIMAL),
        @Result(column="SALE_REGION", property="saleRegion", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_GROUP", property="priceGroup", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SHIPTO_ADDR_NO", property="shiptoAddrNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRICE", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="UOM", property="uom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_UOM", property="priceUom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CURR_CODE", property="currCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="VALID_FROM", property="validFrom", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="VALID_TO", property="validTo", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FROM_QTY", property="fromQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="TO_QTY", property="toQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOLERANCE", property="tolerance", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CREATE_USER_ID", property="createUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MODIFY_USER_ID", property="modifyUserId", jdbcType=JdbcType.DECIMAL),
        @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_DATA_VERSION", property="auditDataVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.DECIMAL),
        @Result(column="ITEM_C1", property="itemC1", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_C2", property="itemC2", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_C3", property="itemC3", jdbcType=JdbcType.NVARCHAR),
        @Result(column="PRICE_STATUS", property="priceStatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ITEM_CODE", property="itemCode", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CUST_CODE", property="custCode", jdbcType=JdbcType.NVARCHAR)
    })
    List<TPbItemSaleprice> selectAll();

    @Update({
        "update PB_ITEM_SALEPRICE",
        "set TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
          "OU_ID = #{ouId,jdbcType=DECIMAL},",
          "ORG_ID = #{orgId,jdbcType=DECIMAL},",
          "PRICE_TYPE = #{priceType,jdbcType=NVARCHAR},",
          "PRICE_TYPE2 = #{priceType2,jdbcType=NVARCHAR},",
          "PRICE_TYPE3 = #{priceType3,jdbcType=NVARCHAR},",
          "ITEM_ID = #{itemId,jdbcType=DECIMAL},",
          "CUST_GROUP = #{custGroup,jdbcType=NVARCHAR},",
          "CUST_ID = #{custId,jdbcType=DECIMAL},",
          "SALE_REGION = #{saleRegion,jdbcType=NVARCHAR},",
          "PRICE_GROUP = #{priceGroup,jdbcType=NVARCHAR},",
          "SHIPTO_ADDR_NO = #{shiptoAddrNo,jdbcType=DECIMAL},",
          "PRICE = #{price,jdbcType=DECIMAL},",
          "UOM = #{uom,jdbcType=NVARCHAR},",
          "PRICE_UOM = #{priceUom,jdbcType=NVARCHAR},",
          "CURR_CODE = #{currCode,jdbcType=NVARCHAR},",
          "VALID_FROM = #{validFrom,jdbcType=TIMESTAMP},",
          "VALID_TO = #{validTo,jdbcType=TIMESTAMP},",
          "FROM_QTY = #{fromQty,jdbcType=DECIMAL},",
          "TO_QTY = #{toQty,jdbcType=DECIMAL},",
          "TOLERANCE = #{tolerance,jdbcType=DECIMAL},",
          "REMARK = #{remark,jdbcType=NVARCHAR},",
          "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
          "ITEM_C1 = #{itemC1,jdbcType=NVARCHAR},",
          "ITEM_C2 = #{itemC2,jdbcType=NVARCHAR},",
          "ITEM_C3 = #{itemC3,jdbcType=NVARCHAR},",
          "PRICE_STATUS = #{priceStatus,jdbcType=NVARCHAR},",
          "ITEM_CODE = #{itemCode,jdbcType=NVARCHAR},",
          "CUST_CODE = #{custCode,jdbcType=NVARCHAR}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TPbItemSaleprice record);
}