package com.el.b2b.excel.excel;

import lombok.Getter;

/**
 * @author kevin.guo
 * @since 2018-05-22
 */
public enum CsvFileName {

    SO_EXPORT("Order.csv"), // 订单导出
    SO_DELIVERED("Delivered.csv"),//已发货订单导出
    ;
    @Getter
    private final String fileName;

    CsvFileName(String fileName) {
        this.fileName = fileName;
    }

}
