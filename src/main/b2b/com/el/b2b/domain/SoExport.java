package com.el.b2b.domain;

import com.el.core.util.CsvColumn;
import com.el.core.util.TimeUtil;
import com.el.edp.util.LocalDateUtil;
import com.el.mbg.domain.TB2bSoD;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/26
 * @Description: 导出订单domain
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SoExport extends TB2bSoD {

    /**
     * 订单编号
     */
    private String docNo;
    /**
     * 订单类型
     */
    private String docType;
    /**
     * 订单状态
     */
    private String docStatus;
    /**
     * 公司名称
     */
    private String ouName;
    /**
     * 产品名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;

    /**
     * 已分配数量 - B2B_SO_D.ASSIGNED_QTY
     */
    private int assignedQty;

    /**
     * 未分配数量 - B2B_SO_D.UNASSIGNED_QTY
     */
    private int unassignedQty;

    /**
     * 已取消数量 - B2B_SO_D.CANCELLED_QTY
     */
    private int cancelledQty;

    /**
     * 已分配金额 - B2B_SO_D.ASSIGNED_AMT
     */
    private BigDecimal assignedAmt;

    /**
     * 厂家物料编码 - PB_ITEM.SPE8CJWLBM
     */
    private String spe8cjwlbm;

    /**
     * 最后编辑时间 - MODIFY_TIME
     */
    @TimeUtil.TIME
    private LocalDateTime modifyTime;

    /**
     * 生产厂家
     */
    private String span8dsc;


    @CsvColumn(header = "订单编号", colIdx = 0)
    public String getDocNoCsv() {
        return getDocNo();
    }

    @CsvColumn(header = "提交日期", colIdx = 1)
    public String getModifyTimeCsv() {
        return StringUtils.isEmpty(getModifyTime()) ?
            "" : LocalDateUtil.formatTime(getModifyTime(), "yyyy-MM-dd HH:mm:ss");
    }

    @CsvColumn(header = "订单类型", colIdx = 2)
    public String getDocTypeCsv() {
        return getDocType();
    }

    @CsvColumn(header = "产品", colIdx = 3)
    public String getItemCodeCsv() {
        return getItemCode();
    }

    @CsvColumn(header = "产品名称", colIdx = 4)
    public String getItemNameCsv() {
        return getItemName();
    }

    @CsvColumn(header = "生产厂家", colIdx = 5)
    public String getSpan8dscCsv() {
        return getSpan8dsc();
    }

    @CsvColumn(header = "厂家物料编码", colIdx = 6)
    public String getSpe8cjwlbmCsv() {
        return getSpe8cjwlbm();
    }

    @CsvColumn(header = "规格", colIdx = 7)
    public String getSpecCsv() {
        return getSpec();
    }

    @CsvColumn(header = "单位", colIdx = 8)
    public String getUomCsv() {
        return getUom();
    }

    @CsvColumn(header = "单价", colIdx = 9)
    public BigDecimal getPriceCsv() {
        return getPrice();
    }

    @CsvColumn(header = "需求数量", colIdx = 10)
    public int getQtyCsv() {
        return getQty();
    }

    @CsvColumn(header = "需求金额", colIdx = 11)
    public BigDecimal getAmtCsv() {
        return getAmt();
    }

    @CsvColumn(header = "已分配数量", colIdx = 12)
    public int getAssignedQtyCsv() {
        return getAssignedQty();
    }

    @CsvColumn(header = "未分配数量", colIdx = 13)
    public int getUnassignedQtyCsv() {
        return getUnassignedQty();
    }

    @CsvColumn(header = "已取消数量", colIdx = 14)
    public int getCancelledQtyCsv() {
        return getCancelledQty();
    }

    @CsvColumn(header = "已分配金额", colIdx = 15)
    public BigDecimal getAssignedAmtCsv() {
        return getAssignedAmt();
    }

    @CsvColumn(header = "状态", colIdx = 16)
    public String getErpStatusCsv() {
        return getErpStatus();
    }


}
