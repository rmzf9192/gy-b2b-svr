package com.el.b2b.domain;

import com.el.mbg.domain.TPbAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description: domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Address extends TPbAddress {
    /**
     * 地址 号 （页面展示用）
     */
    private String addrCode;
    /**
     * 地址名称（页面展示用）
     */
    private String addrName;

}
