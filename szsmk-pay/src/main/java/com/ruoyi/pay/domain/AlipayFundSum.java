package com.ruoyi.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付宝资金汇总对象 alipay_fund_sum
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Data
public class AlipayFundSum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long columnId;

    /** 入帐时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入帐时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fundDate;

    @Excel(name = "商户号")
    private String mchId;
    /** 门店编号 */
    @Excel(name = "门店编号")
    private String retailNo;

    /** 门店名称  */
    @Excel(name = "门店名称 ")
    private String retailName;

    /** 交易订单总笔数 */
    @Excel(name = "交易订单总笔数")
    private Long tradeNoSum;

    /** 退款订单总笔数 */
    @Excel(name = "退款订单总笔数")
    private Long refundNoSum;

    /** 订单金额（元） */
    @Excel(name = "订单金额", readConverterExp = "元=")
    private Double tradePriceSum;

    /** 商家实收（元） */
    @Excel(name = "商家实收", readConverterExp = "元=")
    private Double merRealSum;

    /** 支付宝优惠（元） */
    @Excel(name = "支付宝优惠", readConverterExp = "元=")
    private Double alipayCouponSum;

    /** 商家优惠（元） */
    @Excel(name = "商家优惠", readConverterExp = "元=")
    private Double merCouponSum;

    /** 卡消费金额（元） */
    @Excel(name = "卡消费金额", readConverterExp = "元=")
    private Double cardPriceSum;

    /** 服务费（元） */
    @Excel(name = "服务费", readConverterExp = "元=")
    private Double serviceFeeSum;

    /** 分润（元） */
    @Excel(name = "分润", readConverterExp = "元=")
    private Double profitSum;

    /** 实收净额（元） */
    @Excel(name = "实收净额", readConverterExp = "元=")
    private Double realIncomeSum;

    /**Use @Data

    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId() 
    {
        return columnId;
    }
    public void setFundDate(Date fundDate) 
    {
        this.fundDate = fundDate;
    }

    public Date getFundDate() 
    {
        return fundDate;
    }
    public void setRetailNo(String retailNo) 
    {
        this.retailNo = retailNo;
    }

    public String getRetailNo() 
    {
        return retailNo;
    }
    public void setRetailName(String retailName) 
    {
        this.retailName = retailName;
    }

    public String getRetailName() 
    {
        return retailName;
    }
    public void setTradeNoSum(Long tradeNoSum) 
    {
        this.tradeNoSum = tradeNoSum;
    }

    public Long getTradeNoSum() 
    {
        return tradeNoSum;
    }
    public void setRefundNoSum(Long refundNoSum) 
    {
        this.refundNoSum = refundNoSum;
    }

    public Long getRefundNoSum() 
    {
        return refundNoSum;
    }
    public void setTradePriceSum(Long tradePriceSum) 
    {
        this.tradePriceSum = tradePriceSum;
    }

    public Long getTradePriceSum() 
    {
        return tradePriceSum;
    }
    public void setMerRealSum(Long merRealSum) 
    {
        this.merRealSum = merRealSum;
    }

    public Long getMerRealSum() 
    {
        return merRealSum;
    }
    public void setAlipayCouponSum(Long alipayCouponSum) 
    {
        this.alipayCouponSum = alipayCouponSum;
    }

    public Long getAlipayCouponSum() 
    {
        return alipayCouponSum;
    }
    public void setMerCouponSum(Long merCouponSum) 
    {
        this.merCouponSum = merCouponSum;
    }

    public Long getMerCouponSum() 
    {
        return merCouponSum;
    }
    public void setCardPriceSum(Long cardPriceSum) 
    {
        this.cardPriceSum = cardPriceSum;
    }

    public Long getCardPriceSum() 
    {
        return cardPriceSum;
    }
    public void setServiceFeeSum(Long serviceFeeSum) 
    {
        this.serviceFeeSum = serviceFeeSum;
    }

    public Long getServiceFeeSum() 
    {
        return serviceFeeSum;
    }
    public void setProfitSum(Long profitSum) 
    {
        this.profitSum = profitSum;
    }

    public Long getProfitSum() 
    {
        return profitSum;
    }
    public void setRealIncomeSum(Long realIncomeSum) 
    {
        this.realIncomeSum = realIncomeSum;
    }

    public Long getRealIncomeSum() 
    {
        return realIncomeSum;
    }
*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("fundDate", getFundDate())
            .append("retailNo", getRetailNo())
            .append("retailName", getRetailName())
            .append("tradeNoSum", getTradeNoSum())
            .append("refundNoSum", getRefundNoSum())
            .append("tradePriceSum", getTradePriceSum())
            .append("merRealSum", getMerRealSum())
            .append("alipayCouponSum", getAlipayCouponSum())
            .append("merCouponSum", getMerCouponSum())
            .append("cardPriceSum", getCardPriceSum())
            .append("serviceFeeSum", getServiceFeeSum())
            .append("profitSum", getProfitSum())
            .append("realIncomeSum", getRealIncomeSum())
            .toString();
    }
}
