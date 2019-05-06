package com.el.b2b.api;

import com.el.core.domain.PagingQuery;
import com.el.core.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品主文件Query
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoQuery extends PagingQuery {

    /**
     * 公司编号
     */
    private String ouCode;
    /**
     * 客户编码
     */
    private String custCode;
    /**
     * 下单员工编号
     */
    private Long SoEmpId;
    /**
     * 单据状态 - B2B_SO.DOC_
     */
    private String docStatus;

    /**
     * ERP单据状态 - B2B_SO.
     */
    private String erpStatus;
    /**
     * 下单时间(开始)
     */
    @TimeUtil.DATE
    private LocalDate docStartDate;
    /**
     * 下单时间(结束)
     */
    @TimeUtil.DATE
    private LocalDate docEndDate;


    /**
     * 订单ID
     */
    private Long id;
    /**
     * 单据编号
     */
    private String docNo;


    /**
     * 员工单位 - PS_AUTH_USER.ENT_ID
     */
    private String entId;

    /**
     * 联系人姓名 - PB_ADDRESS.CONT_PERSON
     */
    private String contPerson;
    /**
     * 手机 - PB_ADDRESS.MOBILE
     */
    private String mobile;
    /**
     * 详细地址 - PB_ADDRESS.DETAILADDR
     */
    private String detailaddr;
    /**
     * 单据类型 - B2B_SO.DOC_TYPE
     */
    private String docType;

    /**
     * 单据状态
     */
    private String shipStatus;

    /**
     * 接收开票状态
     */
    private String billStatus;

    /**
     * 申请单号
     */
    private String ehe8ptnum;
}
