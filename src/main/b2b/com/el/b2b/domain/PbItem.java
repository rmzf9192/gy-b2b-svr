package com.el.b2b.domain;

import com.el.mbg.domain.TPbItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description: 商品主文件domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PbItem extends TPbItem {
    /**
     * 公司名称
     */
    private String ouName;
    /**
     * 风险等级名称
     */
    private String gradeName;
    /**
     * 产品类别名称
     */
    private String p1Name;

}
