package com.ruoyi.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 每日汇总对象 pay_trade_sum
 * 
 * @author xuzy
 * @date 2023-12-27
 */
@Data
public class PayTradeSum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  序号 */
    @Excel(name = " 序号")
    private Long id;

    /** 支付商户号 */
    @Excel(name = "支付商户号")
    private String mchId;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 业务名称 */
    @Excel(name = "业务名称")
    private String tradeName;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private Double tradePrice;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private Double refundPrice;

    /** 服务费 */
    @Excel(name = "服务费")
    private Double serviceFee;

    /** 实收金额 */
    @Excel(name = "实收金额")
    private Double realPrice;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mchId", getMchId())
            .append("recordTime", getRecordTime())
            .append("tradeName", getTradeName())
            .append("tradePrice", getTradePrice())
            .append("refundPrice", getRefundPrice())
            .append("serviceFee", getServiceFee())
            .append("realPrice", getRealPrice())
            .append("remark", getRemark())
            .toString();
    }
}
