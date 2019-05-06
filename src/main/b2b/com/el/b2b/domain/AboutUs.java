package com.el.b2b.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description:
 */
@Data
@ToString(callSuper = true)
public class AboutUs {
    /**
     * 首页-公司信息
     */
    private HomeOu ouInfo;
    /**
     * 联络方式 List
     */
    private List<HomeContactInfo> contactList;
    /**
     * 公告LIST
     */
    private List<Annc> anncList;
    /**
     * 附件 LIST
     */
    private List<Attach> attachmentList;
}
