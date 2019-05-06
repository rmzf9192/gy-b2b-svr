package com.el.mbg.mapper;

import com.el.mbg.domain.TB2bSoD;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TB2bSoDMapper {
    @Delete({
        "delete from B2B_SO_D",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into B2B_SO_D ( ",
//        "ID," ,
//            " TENANT_ID, ",
        "SO_ID, LINE_NO, WH_ID, ",
        "ITEM_ID, ITEM_CODE, ",
        "ITEM_NAME, SKU_ID, ",
        "LINE_TYPE, LINE_STATUS, ",
        "PAY_STATUS, QTY, ",
        "UOM, WEIGHT, WEIGHT_UOM, ",
        "WEIGHT_RATIO, BASE_PRICE, ",
        "PRICE_TYPE, PRICE, ",
        "DISC_TYPE, DISC_RATIO, ",
        "DISC_AMT, TAX_RATE, ",
        "TAX_AMT, NET_AMT, ",
        "AMT, REMARK," +
//            " CREATE_USER_ID, ",
//        "CREATE_TIME, MODIFY_USER_ID, ",
//        "MODIFY_TIME," +
            " AUDIT_DATA_VERSION, ",
        "DELETE_FLAG," +
            "ASSIGNED_QTY, UNASSIGNED_QTY, CANCELLED_QTY, ASSIGNED_AMT,DOC_NO,DOC_STATUS,OU_CODE,ERP_STATUS )",
        "values ( ",
//        "#{id,jdbcType=DECIMAL},",
//            " #{tenantId,jdbcType=DECIMAL}, ",
        "#{soId,jdbcType=DECIMAL}, #{lineNo,jdbcType=INTEGER}, #{whId,jdbcType=DECIMAL}, ",
        "#{itemId,jdbcType=DECIMAL}, #{itemCode,jdbcType=VARCHAR}, ",
        "#{itemName,jdbcType=VARCHAR}, #{skuId,jdbcType=DECIMAL}, ",
        "#{lineType,jdbcType=VARCHAR}, #{lineStatus,jdbcType=VARCHAR}, ",
        "#{payStatus,jdbcType=VARCHAR}, #{qty,jdbcType=INTEGER}, ",
        "#{uom,jdbcType=VARCHAR}, #{weight,jdbcType=DECIMAL}, #{weightUom,jdbcType=VARCHAR}, ",
        "#{weightRatio,jdbcType=DECIMAL}, #{basePrice,jdbcType=DECIMAL}, ",
        "#{priceType,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, ",
        "#{discType,jdbcType=VARCHAR}, #{discRatio,jdbcType=DECIMAL}, ",
        "#{discAmt,jdbcType=DECIMAL}, #{taxRate,jdbcType=DECIMAL}, ",
        "#{taxAmt,jdbcType=DECIMAL}, #{netAmt,jdbcType=DECIMAL}, ",
        "#{amt,jdbcType=DECIMAL}, #{remark,jdbcType=VARCHAR}, " +
//            "#{createUserId,jdbcType=DECIMAL}, ",
//        "#{createTime,jdbcType=TIMESTAMP}, #{modifyUserId,jdbcType=DECIMAL}, ",
//        "#{modifyTime,jdbcType=TIMESTAMP}, " +
            "#{auditDataVersion,jdbcType=DECIMAL}, ",
        "#{deleteFlag,jdbcType=DECIMAL},",
        "#{assignedQty,jdbcType=INTEGER}, #{unassignedQty,jdbcType=INTEGER},",
        "#{cancelledQty,jdbcType=INTEGER}, #{assignedAmt,jdbcType=DECIMAL},",
        "#{docNo,jdbcType=VARCHAR}, #{docStatus,jdbcType=VARCHAR}, #{ouCode,jdbcType=VARCHAR}, #{erpStatus,jdbcType=VARCHAR} )"
    })
//    @SelectKey(statement="SELECT SEQ_B2B_SO_D.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
    int insert(TB2bSoD record);

    @Select({
        "select",
        "ID, TENANT_ID, SO_ID, LINE_NO, WH_ID, ITEM_ID, ITEM_CODE, ITEM_NAME, SKU_ID, ",
        "LINE_TYPE, LINE_STATUS, PAY_STATUS, QTY, UOM, WEIGHT, WEIGHT_UOM, WEIGHT_RATIO, ",
        "BASE_PRICE, PRICE_TYPE, PRICE, DISC_TYPE, DISC_RATIO, DISC_AMT, TAX_RATE, TAX_AMT, ",
        "NET_AMT, AMT, REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, ",
        "AUDIT_DATA_VERSION, DELETE_FLAG, ASSIGNED_QTY, UNASSIGNED_QTY, CANCELLED_QTY, ASSIGNED_AMT,DOC_NO,ERP_STATUS",
        "from B2B_SO_D",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column = "ID", property = "id", jdbcType = JdbcType.DECIMAL, id = true),
        @Result(column = "TENANT_ID", property = "tenantId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "SO_ID", property = "soId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LINE_NO", property = "lineNo", jdbcType = JdbcType.INTEGER),
        @Result(column = "WH_ID", property = "whId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ITEM_ID", property = "itemId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ITEM_CODE", property = "itemCode", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ITEM_NAME", property = "itemName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SKU_ID", property = "skuId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LINE_TYPE", property = "lineType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LINE_STATUS", property = "lineStatus", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PAY_STATUS", property = "payStatus", jdbcType = JdbcType.VARCHAR),
        @Result(column = "QTY", property = "qty", jdbcType = JdbcType.INTEGER),
        @Result(column = "UOM", property = "uom", jdbcType = JdbcType.VARCHAR),
        @Result(column = "WEIGHT", property = "weight", jdbcType = JdbcType.DECIMAL),
        @Result(column = "WEIGHT_UOM", property = "weightUom", jdbcType = JdbcType.VARCHAR),
        @Result(column = "WEIGHT_RATIO", property = "weightRatio", jdbcType = JdbcType.DECIMAL),
        @Result(column = "BASE_PRICE", property = "basePrice", jdbcType = JdbcType.DECIMAL),
        @Result(column = "PRICE_TYPE", property = "priceType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PRICE", property = "price", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DISC_TYPE", property = "discType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DISC_RATIO", property = "discRatio", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DISC_AMT", property = "discAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "TAX_RATE", property = "taxRate", jdbcType = JdbcType.DECIMAL),
        @Result(column = "TAX_AMT", property = "taxAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "NET_AMT", property = "netAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "AMT", property = "amt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
        @Result(column = "CREATE_USER_ID", property = "createUserId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "CREATE_TIME", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "MODIFY_USER_ID", property = "modifyUserId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "MODIFY_TIME", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "AUDIT_DATA_VERSION", property = "auditDataVersion", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.DECIMAL),
        @Result(column="ASSIGNED_QTY", property="assignedQty", jdbcType=JdbcType.INTEGER),
        @Result(column="UNASSIGNED_QTY", property="unassignedQty", jdbcType=JdbcType.INTEGER),
        @Result(column="CANCELLED_QTY", property="cancelledQty", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSIGNED_AMT", property="assignedAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DOC_NO", property="docNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERP_STATUS", property="erpStatus", jdbcType=JdbcType.VARCHAR)
    })
    TB2bSoD selectByPrimaryKey(Long id);

    @Select({
        "select",
        "ID, TENANT_ID, SO_ID, LINE_NO, WH_ID, ITEM_ID, ITEM_CODE, ITEM_NAME, SKU_ID, ",
        "LINE_TYPE, LINE_STATUS, PAY_STATUS, QTY, UOM, WEIGHT, WEIGHT_UOM, WEIGHT_RATIO, ",
        "BASE_PRICE, PRICE_TYPE, PRICE, DISC_TYPE, DISC_RATIO, DISC_AMT, TAX_RATE, TAX_AMT, ",
        "NET_AMT, AMT, REMARK, CREATE_USER_ID, CREATE_TIME, MODIFY_USER_ID, MODIFY_TIME, ",
        "AUDIT_DATA_VERSION, DELETE_FLAG,ASSIGNED_QTY, UNASSIGNED_QTY, CANCELLED_QTY, ASSIGNED_AMT, DOC_NO ,ERP_STATUS",
        "from B2B_SO_D"
    })
    @Results({
        @Result(column = "ID", property = "id", jdbcType = JdbcType.DECIMAL, id = true),
        @Result(column = "TENANT_ID", property = "tenantId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "SO_ID", property = "soId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LINE_NO", property = "lineNo", jdbcType = JdbcType.INTEGER),
        @Result(column = "WH_ID", property = "whId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ITEM_ID", property = "itemId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ITEM_CODE", property = "itemCode", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ITEM_NAME", property = "itemName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SKU_ID", property = "skuId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LINE_TYPE", property = "lineType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LINE_STATUS", property = "lineStatus", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PAY_STATUS", property = "payStatus", jdbcType = JdbcType.VARCHAR),
        @Result(column = "QTY", property = "qty", jdbcType = JdbcType.INTEGER),
        @Result(column = "UOM", property = "uom", jdbcType = JdbcType.VARCHAR),
        @Result(column = "WEIGHT", property = "weight", jdbcType = JdbcType.DECIMAL),
        @Result(column = "WEIGHT_UOM", property = "weightUom", jdbcType = JdbcType.VARCHAR),
        @Result(column = "WEIGHT_RATIO", property = "weightRatio", jdbcType = JdbcType.DECIMAL),
        @Result(column = "BASE_PRICE", property = "basePrice", jdbcType = JdbcType.DECIMAL),
        @Result(column = "PRICE_TYPE", property = "priceType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PRICE", property = "price", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DISC_TYPE", property = "discType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DISC_RATIO", property = "discRatio", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DISC_AMT", property = "discAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "TAX_RATE", property = "taxRate", jdbcType = JdbcType.DECIMAL),
        @Result(column = "TAX_AMT", property = "taxAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "NET_AMT", property = "netAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "AMT", property = "amt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
        @Result(column = "CREATE_USER_ID", property = "createUserId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "CREATE_TIME", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "MODIFY_USER_ID", property = "modifyUserId", jdbcType = JdbcType.DECIMAL),
        @Result(column = "MODIFY_TIME", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "AUDIT_DATA_VERSION", property = "auditDataVersion", jdbcType = JdbcType.DECIMAL),
        @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.DECIMAL),
        @Result(column="ASSIGNED_QTY", property="assignedQty", jdbcType=JdbcType.INTEGER),
        @Result(column="UNASSIGNED_QTY", property="unassignedQty", jdbcType=JdbcType.INTEGER),
        @Result(column="CANCELLED_QTY", property="cancelledQty", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSIGNED_AMT", property="assignedAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DOC_NO", property="docNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERP_STATUS", property="erpStatus", jdbcType=JdbcType.VARCHAR)

    })
    List<TB2bSoD> selectAll();

    @Update({
        "update B2B_SO_D",
        "set " +
//            "TENANT_ID = #{tenantId,jdbcType=DECIMAL},",
            "SO_ID = #{soId,jdbcType=DECIMAL},",
        "LINE_NO = #{lineNo,jdbcType=INTEGER},",
        "WH_ID = #{whId,jdbcType=DECIMAL},",
        "ITEM_ID = #{itemId,jdbcType=DECIMAL},",
        "ITEM_CODE = #{itemCode,jdbcType=VARCHAR},",
        "ITEM_NAME = #{itemName,jdbcType=VARCHAR},",
        "SKU_ID = #{skuId,jdbcType=DECIMAL},",
        "LINE_TYPE = #{lineType,jdbcType=VARCHAR},",
        "LINE_STATUS = #{lineStatus,jdbcType=VARCHAR},",
        "PAY_STATUS = #{payStatus,jdbcType=VARCHAR},",
        "QTY = #{qty,jdbcType=INTEGER},",
        "UOM = #{uom,jdbcType=VARCHAR},",
        "WEIGHT = #{weight,jdbcType=DECIMAL},",
        "WEIGHT_UOM = #{weightUom,jdbcType=VARCHAR},",
        "WEIGHT_RATIO = #{weightRatio,jdbcType=DECIMAL},",
        "BASE_PRICE = #{basePrice,jdbcType=DECIMAL},",
        "PRICE_TYPE = #{priceType,jdbcType=VARCHAR},",
        "PRICE = #{price,jdbcType=DECIMAL},",
        "DISC_TYPE = #{discType,jdbcType=VARCHAR},",
        "DISC_RATIO = #{discRatio,jdbcType=DECIMAL},",
        "DISC_AMT = #{discAmt,jdbcType=DECIMAL},",
        "TAX_RATE = #{taxRate,jdbcType=DECIMAL},",
        "TAX_AMT = #{taxAmt,jdbcType=DECIMAL},",
        "NET_AMT = #{netAmt,jdbcType=DECIMAL},",
        "AMT = #{amt,jdbcType=DECIMAL},",
        "REMARK = #{remark,jdbcType=VARCHAR},",
//        "CREATE_USER_ID = #{createUserId,jdbcType=DECIMAL},",
//        "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
        "MODIFY_USER_ID = #{modifyUserId,jdbcType=DECIMAL},",
        "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
        "AUDIT_DATA_VERSION = #{auditDataVersion,jdbcType=DECIMAL},",
        "DELETE_FLAG = #{deleteFlag,jdbcType=DECIMAL},",
        "ASSIGNED_QTY = #{assignedQty,jdbcType=INTEGER},",
        "UNASSIGNED_QTY = #{unassignedQty,jdbcType=INTEGER},",
        "CANCELLED_QTY = #{cancelledQty,jdbcType=INTEGER},",
        "ASSIGNED_AMT = #{assignedAmt,jdbcType=DECIMAL},",
        "DOC_NO = #{docNo,jdbcType=VARCHAR},",
        "ERP_STATUS = #{erpStatus,jdbcType=VARCHAR} ",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TB2bSoD record);
}
