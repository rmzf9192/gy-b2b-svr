package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品主文件Query
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PbItemQuery extends PagingQuery {
    /**
     * 品项名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;

    /**
     * 公司编码
     */
    private String ouCode;

    /**
     * 外部编码（商品编号）
     */
    private String outerCode;
    /**
     * 品项编号
     */
    private String itemCode;

    /**
     * 厂家物料编码
     */
    private String spe8cjwlbm;

    /**
     * 生产厂家编码
     */
    private String span8dsc;
    /**
     * 生产厂家
     */
    private String span8;
}
