package com.el.b2b.domain;

import lombok.Data;

@Data
public class UomTrans {
    /**
     * mcu
     */
    private String mcu;
    /**
     * 短项目号
     */
    private String itm;
    /**
     * 自量单位-库存管理计量单位
     */
    private String um;
    /**
     * 转换至计量单位—主计量单位
     */
    private String rum;

    /**
     * 转换系数
     */
    private Integer conv;

    /**
     * 自计量单位转换为主计量单位
     */
    private Integer cnv1;

    private String umdesc;



}
