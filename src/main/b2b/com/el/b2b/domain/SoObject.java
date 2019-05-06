package com.el.b2b.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/26
 * @Description: 销售订单对象domain
 */
@Data
@ToString(callSuper = true)
public class SoObject {

    /**
     * 订单表头
     */
    private So so;
    /**
     * 订单明细List
     */
    private List<SoD> sodList;

    /**
     * ids
     */
    private Long[] ids;
    /**
     *
     */
    private boolean flag;


}
