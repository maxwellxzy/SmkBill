package com.ruoyi.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付宝交易明细单对象 alipay_trade
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Data
public class AlipayTrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long columnId;
    @Excel(name = "商户号")
    private String mchId;
    /** 支付宝交易号 */
    @Excel(name = "支付宝交易号")
    private String alipayTradeNo;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String merTradeNo;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String tradeType;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String retailNo;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String retailName;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 终端号 */
    @Excel(name = "终端号")
    private String terminalNo;

    /** 对方账户 */
    @Excel(name = "对方账户")
    private String userAccount;

    /** 订单金额（元） */
    @Excel(name = "订单金额", readConverterExp = "元=")
    private Double orderPrice;

    /** 商家实收（元） */
    @Excel(name = "商家实收", readConverterExp = "元=")
    private Double realPrice;

    /** 支付宝红包（元） */
    @Excel(name = "支付宝红包", readConverterExp = "元=")
    private Double alipayRedpocket;

    /** 集分宝（元） */
    @Excel(name = "集分宝", readConverterExp = "元=")
    private Double jifenbao;

    /** 支付宝优惠(元) */
    @Excel(name = "支付宝优惠(元)")
    private Double alipayCoupon;

    /** 商家优惠（元） */
    @Excel(name = "商家优惠", readConverterExp = "元=")
    private Double merCoupon;

    /** 券核销金额（元） */
    @Excel(name = "券核销金额", readConverterExp = "元=")
    private Double couponCompletePrice;

    /** 券名称 */
    @Excel(name = "券名称")
    private String couponName;

    /** 商家红包消费金额（元） */
    @Excel(name = "商家红包消费金额", readConverterExp = "元=")
    private Double merRedpocketComsume;

    /** 卡消费金额（元） */
    @Excel(name = "卡消费金额", readConverterExp = "元=")
    private Double cardConsume;

    /** 退款批次号/请求号 */
    @Excel(name = "退款批次号/请求号")
    private String refundNo;

    /** 服务费（元） */
    @Excel(name = "服务费", readConverterExp = "元=")
    private Double serviceFee;

    /** 分润（元） */
    @Excel(name = "分润", readConverterExp = "元=")
    private Double profit;

/** use@Data

    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId() 
    {
        return columnId;
    }
    public void setAlipayTradeNo(String alipayTradeNo) 
    {
        this.alipayTradeNo = alipayTradeNo;
    }

    public String getAlipayTradeNo() 
    {
        return alipayTradeNo;
    }
    public void setMerTradeNo(String merTradeNo) 
    {
        this.merTradeNo = merTradeNo;
    }

    public String getMerTradeNo() 
    {
        return merTradeNo;
    }
    public void setTradeType(String tradeType) 
    {
        this.tradeType = tradeType;
    }

    public String getTradeType() 
    {
        return tradeType;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
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
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setTerminalNo(String terminalNo) 
    {
        this.terminalNo = terminalNo;
    }

    public String getTerminalNo() 
    {
        return terminalNo;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setOrderPrice(Long orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public Long getOrderPrice() 
    {
        return orderPrice;
    }
    public void setRealPrice(Long realPrice) 
    {
        this.realPrice = realPrice;
    }

    public Long getRealPrice() 
    {
        return realPrice;
    }
    public void setAlipayRedpocket(Long alipayRedpocket) 
    {
        this.alipayRedpocket = alipayRedpocket;
    }

    public Long getAlipayRedpocket() 
    {
        return alipayRedpocket;
    }
    public void setJifenbao(Long jifenbao) 
    {
        this.jifenbao = jifenbao;
    }

    public Long getJifenbao() 
    {
        return jifenbao;
    }
    public void setAlipayCoupon(Long alipayCoupon) 
    {
        this.alipayCoupon = alipayCoupon;
    }

    public Long getAlipayCoupon() 
    {
        return alipayCoupon;
    }
    public void setMerCoupon(Long merCoupon) 
    {
        this.merCoupon = merCoupon;
    }

    public Long getMerCoupon() 
    {
        return merCoupon;
    }
    public void setCouponCompletePrice(Long couponCompletePrice) 
    {
        this.couponCompletePrice = couponCompletePrice;
    }

    public Long getCouponCompletePrice() 
    {
        return couponCompletePrice;
    }
    public void setCouponName(String couponName) 
    {
        this.couponName = couponName;
    }

    public String getCouponName() 
    {
        return couponName;
    }
    public void setMerRedpocketComsume(Long merRedpocketComsume) 
    {
        this.merRedpocketComsume = merRedpocketComsume;
    }

    public Long getMerRedpocketComsume() 
    {
        return merRedpocketComsume;
    }
    public void setCardConsume(Long cardConsume) 
    {
        this.cardConsume = cardConsume;
    }

    public Long getCardConsume() 
    {
        return cardConsume;
    }
    public void setRefundNo(String refundNo) 
    {
        this.refundNo = refundNo;
    }

    public String getRefundNo() 
    {
        return refundNo;
    }
    public void setServiceFee(Long serviceFee) 
    {
        this.serviceFee = serviceFee;
    }

    public Long getServiceFee() 
    {
        return serviceFee;
    }
    public void setProfit(Long profit) 
    {
        this.profit = profit;
    }

    public Long getProfit() 
    {
        return profit;
    }
*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("alipayTradeNo", getAlipayTradeNo())
            .append("merTradeNo", getMerTradeNo())
            .append("tradeType", getTradeType())
            .append("goodsName", getGoodsName())
            .append("createTime", getCreateTime())
            .append("completeTime", getCompleteTime())
            .append("retailNo", getRetailNo())
            .append("retailName", getRetailName())
            .append("operator", getOperator())
            .append("terminalNo", getTerminalNo())
            .append("userAccount", getUserAccount())
            .append("orderPrice", getOrderPrice())
            .append("realPrice", getRealPrice())
            .append("alipayRedpocket", getAlipayRedpocket())
            .append("jifenbao", getJifenbao())
            .append("alipayCoupon", getAlipayCoupon())
            .append("merCoupon", getMerCoupon())
            .append("couponCompletePrice", getCouponCompletePrice())
            .append("couponName", getCouponName())
            .append("merRedpocketComsume", getMerRedpocketComsume())
            .append("cardConsume", getCardConsume())
            .append("refundNo", getRefundNo())
            .append("serviceFee", getServiceFee())
            .append("profit", getProfit())
            .append("remark", getRemark())
            .toString();
    }
}
