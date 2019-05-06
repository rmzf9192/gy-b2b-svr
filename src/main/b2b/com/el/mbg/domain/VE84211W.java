package com.el.mbg.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class VE84211W {
    /**
     *  定单 公司 - VE84211W.SDKCOO
     */
    private String sdkcoo;

    /**
     *  定单 号 - VE84211W.SDDOCO
     */
    private Integer sddoco;

    /**
     *  定单 类型 - VE84211W.SDDCTO
     */
    private String sddcto;

    /**
     *  行 号 - VE84211W.SDLNID
     */
    private Integer sdlnid;

    /**
     *  请求 日期 - VE84211W.SDDRQJ
     */
    private Integer sddrqj;

    /**
     *  定单 日期 - VE84211W.SDTRDJ
     */
    private Integer sdtrdj;

    /**
     *  计划 提货 - VE84211W.SDPDDJ
     */
    private Integer sdpddj;

    /**
     *  实际发运 日期 - VE84211W.SDADDJ
     */
    private Integer sdaddj;

    /**
     *  发票 日期 - VE84211W.SDIVD
     */
    private Integer sdivd;

    /**
     *  取消 日期 - VE84211W.SDCNDJ
     */
    private Integer sdcndj;

    /**
     *  总帐 日期 - VE84211W.SDDGL
     */
    private Integer sddgl;

    /**
     *  商品编码  - VE84211W.SDLITM
     */
    private String sdlitm;

    /**
     *  第三项目 号 - VE84211W.SDAITM
     */
    private String sdaitm;

    /**
     *  库位  - VE84211W.SDLOCN
     */
    private String sdlocn;

    /**
     *  批次 序列号 - VE84211W.SDLOTN
     */
    private String sdlotn;

    /**
     *  生成批号 - VE84211W.IOLOT1
     */
    private String iolot1;

    /**
     *  生成日期 - VE84211W.FCD01
     */
    private Integer fcd01;

    /**
     *  失效日期 - VE84211W.IOMMEJ
     */
    private Integer iommej;

    /**
     *  商品名称  - VE84211W.SDDSC1
     */
    private String sddsc1;

    /**
     *  规格型号  - VE84211W.SDDSC2
     */
    private String sddsc2;

    /**
     *  行 类型 - VE84211W.SDLNTY
     */
    private String sdlnty;

    /**
     *  下一 状态 - VE84211W.SDNXTR
     */
    private String sdnxtr;

    /**
     *  上一 状态 - VE84211W.SDLTTR
     */
    private String sdlttr;

    /**
     *  表头 经营单位 - VE84211W.SDEMCU
     */
    private String sdemcu;

    /**
     *  计量单位  - VE84211W.SDUOM
     */
    private String sduom;

    /**
     *  定购 数量 - VE84211W.SDUORG
     */
    private Integer sduorg;

    /**
     *  发运 数量 - VE84211W.SDSOQS
     */
    private Integer sdsoqs;

    /**
     *  延交定单 数量 - VE84211W.SDSOBK
     */
    private Integer sdsobk;

    /**
     *  已取消 数量 - VE84211W.SDSOCN
     */
    private Integer sdsocn;

    /**
     *  将来 承诺数量 - VE84211W.SDSONE
     */
    private Integer sdsone;

    /**
     *  未结 数量 - VE84211W.SDUOPN
     */
    private Integer sduopn;

    /**
     *  发运数量 累计 - VE84211W.SDQTYT
     */
    private Integer sdqtyt;

    /**
     *  已释放 数量 - VE84211W.SDQRLV
     */
    private Integer sdqrlv;

    /**
     *  承诺  - VE84211W.SDCOMM
     */
    private String sdcomm;

    /**
     *  其他 数量 - VE84211W.SDOTQY
     */
    private String sdotqy;

    /**
     *  单 价 - VE84211W.SDUPRC
     */
    private BigDecimal sduprc;

    /**
     *  总 价 - VE84211W.SDAEXP
     */
    private BigDecimal sdaexp;

    /**
     *  含税单价 - VE84211W.DTTUPRC
     */
    private BigDecimal dttuprc;

    /**
     *  含税总价 - VE84211W.DTTAEXP
     */
    private BigDecimal dttaexp;

    /**
     *  未结 金额 - VE84211W.SDAOPN
     */
    private BigDecimal sdaopn;

    /**
     *  价格 覆盖码 - VE84211W.SDPROV
     */
    private String sdprov;

    /**
     *  临时 价格 - VE84211W.SDTPC
     */
    private String sdtpc;

    /**
     *  已输入的 计量单位 - VE84211W.SDAPUM
     */
    private String sdapum;

    /**
     *  单位 出厂价格 - VE84211W.SDLPRC
     */
    private BigDecimal sdlprc;

    /**
     *  单位 成本 - VE84211W.SDUNCS
     */
    private BigDecimal sduncs;

    /**
     *  总 成本 - VE84211W.SDECST
     */
    private BigDecimal sdecst;

    /**
     *  成本 覆盖 - VE84211W.SDCSTO
     */
    private String sdcsto;

    /**
     *  转移 成本 - VE84211W.SDTCST
     */
    private BigDecimal sdtcst;

    /**
     *  打印 消息 - VE84211W.SDINMG
     */
    private String sdinmg;

    /**
     *  付款 条款 - VE84211W.SDPTC
     */
    private String sdptc;

    /**
     *  付款 方式 - VE84211W.SDRYIN
     */
    private String sdryin;

    /**
     *  分配 佣金 - VE84211W.SDACOM
     */
    private String sdacom;

    /**
     *  佣金 类别 - VE84211W.SDCMCG
     */
    private String sdcmcg;

    /**
     *  原因 码 - VE84211W.SDRCD
     */
    private String sdrcd;

    /**
     *  子 帐 - VE84211W.SDSBL
     */
    private String sdsbl;

    /**
     *  子帐 类型 - VE84211W.SDSBLT
     */
    private String sdsblt;

    /**
     *  税 码 - VE84211W.SDLCOD
     */
    private String sdlcod;

    /**
     *  汇 率 - VE84211W.SDCRR
     */
    private BigDecimal sdcrr;

    /**
     *  外币出厂 价格 - VE84211W.SDFPRC
     */
    private BigDecimal sdfprc;

    /**
     *  外币 单价 - VE84211W.SDFUP
     */
    private BigDecimal sdfup;

    /**
     *  外币 总价 - VE84211W.SDFEA
     */
    private BigDecimal sdfea;

    /**
     *  外币单位 成本 - VE84211W.SDFUC
     */
    private BigDecimal sdfuc;

    /**
     *  外币 总成本 - VE84211W.SDFEC
     */
    private BigDecimal sdfec;

    /**
     *  用户 码 - VE84211W.SDURCD
     */
    private String sdurcd;

    /**
     *  用户 日期 - VE84211W.SDURDT
     */
    private Integer sdurdt;

    /**
     *  用户 金额 - VE84211W.SDURAT
     */
    private BigDecimal sdurat;

    /**
     *  用户 号 - VE84211W.SDURAB
     */
    private Integer sdurab;

    /**
     *  用户 参考 - VE84211W.SDURRF
     */
    private String sdurrf;

    /**
     *  业务记录 发起人 - VE84211W.SDTORG
     */
    private String sdtorg;

    /**
     *  用户 号 - VE84211W.SDUSER
     */
    private String sduser;

    /**
     *  程序 号 - VE84211W.SDPID
     */
    private String sdpid;

    /**
     *  工作 站号 - VE84211W.SDJOBN
     */
    private String sdjobn;

    /**
     *  更新 日期 - VE84211W.SDUPMJ
     */
    private Integer sdupmj;

    /**
     *  具体 时间 - VE84211W.SDTDAY
     */
    private Integer sdtday;

    /**
     *  定单 状态 16 - VE84211W.SDSO16
     */
    private String sdso16;

    /**
     *  体积优惠 标志 - VE84211W.SDSO17
     */
    private String sdso17;

    /**
     *  定单 状态 18 - VE84211W.SDSO18
     */
    private String sdso18;

    /**
     *  定单 状态 19 - VE84211W.SDSO19
     */
    private String sdso19;

    /**
     *  定单 状态 20 - VE84211W.SDSO20
     */
    private String sdso20;

    /**
     *  集成性 参考 01 - VE84211W.SDIR01
     */
    private String sdir01;

    /**
     *  集成性 参考 02 - VE84211W.SDIR02
     */
    private String sdir02;

    /**
     *  集成性 参考 03 - VE84211W.SDIR03
     */
    private String sdir03;

    /**
     *  集成性 参考 04 - VE84211W.SDIR04
     */
    private String sdir04;

    /**
     *  集成性 参考 05 - VE84211W.SDIR05
     */
    private String sdir05;

    /**
     *  定单 来源 - VE84211W.SDSOOR
     */
    private Integer sdsoor;

    /**
     *  计划 发运时间 - VE84211W.SDPMDT
     */
    private Integer sdpmdt;

    /**
     *  发放 时间 - VE84211W.SDRLTM
     */
    private Integer sdrltm;

    /**
     *  发放 日期 - VE84211W.SDRLDJ
     */
    private Integer sdrldj;

    /**
     *  要求交 货时间 - VE84211W.SDDRQT
     */
    private Integer sddrqt;

    /**
     *  实际 发运时间 - VE84211W.SDADTM
     */
    private Integer sdadtm;

    /**
     *  原始 承诺时间 - VE84211W.SDOPTT
     */
    private Integer sdoptt;

    /**
     *  计划 提货时间 - VE84211W.SDPDTT
     */
    private Integer sdpdtt;

    /**
     *  累计已开发票 金额（本币） - VE84211W.SDKITAMTDOM
     */
    private BigDecimal sdkitamtdom;

    /**
     *  累计已开发票 金额（外币） - VE84211W.SDKITAMTFOR
     */
    private BigDecimal sdkitamtfor;

    /**
     *  平台号 - VE84211W.EHE8PTID
     */
    private String ehe8ptid;

    /**
     *   - VE84211W.EHE8PTTP
     */
    private String ehe8pttp;

    /**
     *   - VE84211W.EHE8PTNUM
     */
    private String ehe8ptnum;

    /**
     *   - VE84211W.EHE8PTLIN
     */
    private Integer ehe8ptlin;

    /**
     *   - VE84211W.SHIP_STATUS
     */
    private Integer shipStatus;

}
