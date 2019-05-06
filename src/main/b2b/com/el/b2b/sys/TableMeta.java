package com.el.b2b.sys;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by Conway on 2016-12-11.
 * 系统中用到的业务表
 * <p>
 * MYSQL: SELECT CONCAT(TABLE_NAME,  '("', TABLE_NAME, '", "', left(TABLE_COMMENT, 10) ,'"',  '),')  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'egcrm' AND TABLE_NAME LIKE 'T_%'
 * ORACLE: SELECT TABLE_NAME||'("'||TABLE_NAME||'", "' || COMMENTS ||'"'||'),' FROM USER_TAB_COMMENTS WHERE USER = 'C##CRM' and TABLE_NAME LIKE 'T%';
 */
@Getter
@ToString
public enum TableMeta {
    T_UDC("T_UDC", "UDC值"),
    T_UDC_TYPE("T_UDC_TYPE", "UDC类型"),
    /************************B2B********************************
     */
    B2B_TSO("B2B_TSO", "销售订单模板"),
    B2B_TSO_D("B2B_TSO_D", "销售订单模板明细"),
    B2B_SO("B2B_SO", "订单"),
    B2B_SO_D("B2B_SO_D", "订单明细"),

    /*********************EDP*******************************/
    PB_ITEM("PB_ITEM", "商品信息"),
    PB_ITEM_SALEPRICE("PB_ITEM_SALEPRICE", "商品价格信息"),
    PB_ADDRESS("PB_ADDRESS", "地址信息"),
    PB_OU("PB_OU", "公司信息"),
    PB_CUST("PB_CUST", "客户信息"),
    HOME_OU("HOME_OU","首页-公司信息"),
    HOME_CONTACT_INFO("HOME_CONTACT_INFO","联系方式"),
    PB_ANNC("PB_ANNC","通知公告"),
    PB_ATTACH("PB_ATTACH","附件");


    private final String name;
    private final String desc;

    TableMeta(String tableName, String desc) {
        this.name = tableName;
        this.desc = desc;
    }
}
