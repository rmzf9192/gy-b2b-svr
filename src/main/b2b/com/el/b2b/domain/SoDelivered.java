package com.el.b2b.domain;

import com.el.core.domain.PagingQuery;
import com.el.core.util.CsvColumn;
import com.el.core.util.TimeUtil;
import com.el.edp.util.LocalDateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Auther: fei.zhang
 * @Date: 2018/12/17
 * @Description:已发货订单
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoDelivered extends PagingQuery {

    /**
     * 申请单号-VE84211W.EHE8PTNUM
     */
    private String ehe8ptnum;

    /**
     *  定单 号 - VE84211W.SDDOCO
     */
    private Integer sddoco;
    /**
     *  实际发运 日期 - VE84211W.SDADDJ
     */
    @TimeUtil.TIME
    private LocalDateTime sdaddj;


    /**
     *  商品编码  - VE84211W.SDLITM
     */
    private String sdlitm;


    /**
     *  生成批号 - VE84211W.IOLOT1
     */
    private String iolot1;

    /**
     *  生成日期 - VE84211W.FCD01
     */
    @TimeUtil.TIME
    private LocalDateTime fcd01;

    /**
     *  失效日期 - VE84211W.IOMMEJ
     */
    @TimeUtil.TIME
    private LocalDateTime iommej;

    /**
     *  商品名称  - VE84211W.SDDSC1
     */
    private String sddsc1;

    /**
     *  规格型号  - VE84211W.SDDSC2
     */
    private String sddsc2;

    /**
     *  计量单位  - VE84211W.SDUOM
     */
    private String sduom;

    /**
     *  发运 数量 - VE84211W.SDSOQS
     */
    private Integer sdsoqs;

    /**
     *  含税单价 - VE84211W.DTTUPRC
     */
    private Integer dttuprc;
    /**
     * 含税金额
     */
    private Integer dttaexp;
    /**
     * 提交时间-B2B_SO.COMMENT_TIME;
     */
    @TimeUtil.TIME
    private LocalDateTime commentTime;
    /**
     * 注册证号-VE84211W.FCE8ZZBM
     */
    private String fce8zzbm;

    /**
     * 发票号-VE84211W.AB58BINVNUM2
     */
    private String ab58binvnum2;



    @CsvColumn(header = "订单编号", colIdx = 0)
    public String getDocNoCsv() {
        return getEhe8ptnum();
    }
    @CsvColumn(header = "ERP单号", colIdx = 1)
    public Integer getSddocoCsv() {
        return getSddoco();
    }


    @CsvColumn(header = "提交日期", colIdx = 2)
    public String getCommentTimeCsv() {
        return StringUtils.isEmpty(getCommentTime()) ?
            "" : LocalDateUtil.formatTime(getCommentTime(), "yyyy-MM-dd HH:mm:ss");
    }

    @CsvColumn(header = "发货日期", colIdx = 3)
    public String getSdaddjCsv() {
        return StringUtils.isEmpty(getSdaddj()) ?
            "" : LocalDateUtil.formatTime(getSdaddj(), "yyyy-MM-dd HH:mm:ss");
    }
    @CsvColumn(header = "产品编码", colIdx = 4)
    public String getSdlitmCsv() {
        return getSdlitm();
    }

    @CsvColumn(header = "商品名称", colIdx = 5)
    public String getddsc1Csv() {
        return getSddsc1();
    }

    @CsvColumn(header = "规格型号", colIdx = 6)
    public String getSddsc2Csv() {
        return getSddsc2();
    }

    @CsvColumn(header = "生产批号", colIdx = 7)
    public String getIolot1Csv() {
        return getIolot1();
    }

    @CsvColumn(header = "注册证号", colIdx = 8)
    public String getFce8zzbmCsv() {
        return getFce8zzbm();
    }

    @CsvColumn(header = "生效日期", colIdx = 9)
    public String getFcd01Csv() {
        return StringUtils.isEmpty(getFcd01()) ?
            "" : LocalDateUtil.formatTime(getFcd01(), "yyyy-MM-dd HH:mm:ss");
    }
    @CsvColumn(header = "失效日期", colIdx = 10)
    public String getIommejCsv() {
        return StringUtils.isEmpty(getIommej()) ?
            "" : LocalDateUtil.formatTime(getIommej(), "yyyy-MM-dd HH:mm:ss");
    }

    @CsvColumn(header = "单位", colIdx = 11)
    public String getSduomCsv() {
        return getSduom();
    }

    @CsvColumn(header = "含税单价", colIdx = 12)
    public int getDttuprcCsv() {
        return getDttuprc();
    }

    @CsvColumn(header = "发货数量", colIdx = 13)
    public int getSdsoqsCsv() {
        return getSdsoqs();
    }

    @CsvColumn(header = "含税金额", colIdx = 14)
    public int getDttaexpCsv1() {
        return getDttaexp();
    }

    @CsvColumn(header = "发票号码", colIdx = 15)
    public String getAb58binvnum2Csv() {
        return getAb58binvnum2();
    }

}
