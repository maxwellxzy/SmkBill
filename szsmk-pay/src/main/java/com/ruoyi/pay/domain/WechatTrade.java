package com.ruoyi.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信交易订单对象 wechat_trade
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Data
public class WechatTrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long columnId;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tradeTime;

    /** 公众账号ID */
    @Excel(name = "公众账号ID")
    private String mpId;

    /** 商户号 */
    @Excel(name = "商户号")
    private String mchId;

    /** 特约商户号 */
    @Excel(name = "特约商户号")
    private String vipMchId;

    /** 设备号 */
    @Excel(name = "设备号")
    private String terminalId;

    /** 微信订单号 */
    @Excel(name = "微信订单号")
    private String wechatTradeNo;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String mchTradeNo;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private String userId;

    /** 交易类型 */
    @Excel(name = "交易类型")
    private String tradeType;

    /** 交易状态 */
    @Excel(name = "交易状态")
    private String tradeStatus;

    /** 付款银行 */
    @Excel(name = "付款银行")
    private String payBank;

    /** 货币种类 */
    @Excel(name = "货币种类")
    private String currencyType;

    /** 应结订单金额 */
    @Excel(name = "应结订单金额")
    private Double tradePayable;

    /** 代金券金额 */
    @Excel(name = "代金券金额")
    private Double couponPrice;

    /** 微信退款单号 */
    @Excel(name = "微信退款单号")
    private String wechatRefundNo;

    /** 商户退款单号 */
    @Excel(name = "商户退款单号")
    private String mchRefundNo;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private Double refundPrice;

    /** 充值券退款金额 */
    @Excel(name = "充值券退款金额")
    private Double couponRefundPrice;

    /** 退款类型 */
    @Excel(name = "退款类型")
    private String refundType;

    /** 退款状态 */
    @Excel(name = "退款状态")
    private String refundStatus;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商户数据包 */
    @Excel(name = "商户数据包")
    private String mchDataPackage;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double serviceFee;

    /** 费率 */
    @Excel(name = "费率")
    private Double serviceRate;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Double tradePrice;

    /** 申请退款金额 */
    @Excel(name = "申请退款金额")
    private Double requestRefund;

    /** 费率备注 */
    @Excel(name = "费率备注")
    private String rateRemark;

/**use @Data

    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId() 
    {
        return columnId;
    }
    public void setTradeTime(Date tradeTime) 
    {
        this.tradeTime = tradeTime;
    }

    public Date getTradeTime() 
    {
        return tradeTime;
    }
    public void setMpId(String mpId) 
    {
        this.mpId = mpId;
    }

    public String getMpId() 
    {
        return mpId;
    }
    public void setMchId(String mchId) 
    {
        this.mchId = mchId;
    }

    public String getMchId() 
    {
        return mchId;
    }
    public void setVipMchId(String vipMchId) 
    {
        this.vipMchId = vipMchId;
    }

    public String getVipMchId() 
    {
        return vipMchId;
    }
    public void setTerminalId(String terminalId) 
    {
        this.terminalId = terminalId;
    }

    public String getTerminalId() 
    {
        return terminalId;
    }
    public void setWechatTradeNo(String wechatTradeNo) 
    {
        this.wechatTradeNo = wechatTradeNo;
    }

    public String getWechatTradeNo() 
    {
        return wechatTradeNo;
    }
    public void setMchTradeNo(String mchTradeNo) 
    {
        this.mchTradeNo = mchTradeNo;
    }

    public String getMchTradeNo() 
    {
        return mchTradeNo;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setTradeType(String tradeType) 
    {
        this.tradeType = tradeType;
    }

    public String getTradeType() 
    {
        return tradeType;
    }
    public void setTradeStatus(String tradeStatus) 
    {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatus() 
    {
        return tradeStatus;
    }
    public void setPayBank(String payBank) 
    {
        this.payBank = payBank;
    }

    public String getPayBank() 
    {
        return payBank;
    }
    public void setCurrencyType(String currencyType) 
    {
        this.currencyType = currencyType;
    }

    public String getCurrencyType() 
    {
        return currencyType;
    }
    public void setTradePayable(Long tradePayable) 
    {
        this.tradePayable = tradePayable;
    }

    public Long getTradePayable() 
    {
        return tradePayable;
    }
    public void setCouponPrice(Long couponPrice) 
    {
        this.couponPrice = couponPrice;
    }

    public Long getCouponPrice() 
    {
        return couponPrice;
    }
    public void setWechatRefundNo(String wechatRefundNo) 
    {
        this.wechatRefundNo = wechatRefundNo;
    }

    public String getWechatRefundNo() 
    {
        return wechatRefundNo;
    }
    public void setMchRefundNo(String mchRefundNo) 
    {
        this.mchRefundNo = mchRefundNo;
    }

    public String getMchRefundNo() 
    {
        return mchRefundNo;
    }
    public void setRefundPrice(Long refundPrice) 
    {
        this.refundPrice = refundPrice;
    }

    public Long getRefundPrice() 
    {
        return refundPrice;
    }
    public void setCouponRefundPrice(Long couponRefundPrice) 
    {
        this.couponRefundPrice = couponRefundPrice;
    }

    public Long getCouponRefundPrice() 
    {
        return couponRefundPrice;
    }
    public void setRefundType(String refundType) 
    {
        this.refundType = refundType;
    }

    public String getRefundType() 
    {
        return refundType;
    }
    public void setRefundStatus(String refundStatus) 
    {
        this.refundStatus = refundStatus;
    }

    public String getRefundStatus() 
    {
        return refundStatus;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setMchDataPackage(String mchDataPackage) 
    {
        this.mchDataPackage = mchDataPackage;
    }

    public String getMchDataPackage() 
    {
        return mchDataPackage;
    }
    public void setServiceFee(Long serviceFee) 
    {
        this.serviceFee = serviceFee;
    }

    public Long getServiceFee() 
    {
        return serviceFee;
    }
    public void setServiceRate(Long serviceRate) 
    {
        this.serviceRate = serviceRate;
    }

    public Long getServiceRate() 
    {
        return serviceRate;
    }
    public void setTradePrice(Long tradePrice) 
    {
        this.tradePrice = tradePrice;
    }

    public Long getTradePrice() 
    {
        return tradePrice;
    }
    public void setRequestRefund(Long requestRefund) 
    {
        this.requestRefund = requestRefund;
    }

    public Long getRequestRefund() 
    {
        return requestRefund;
    }
    public void setRateRemark(String rateRemark) 
    {
        this.rateRemark = rateRemark;
    }

    public String getRateRemark() 
    {
        return rateRemark;
    }
*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("tradeTime", getTradeTime())
            .append("mpId", getMpId())
            .append("mchId", getMchId())
            .append("vipMchId", getVipMchId())
            .append("terminalId", getTerminalId())
            .append("wechatTradeNo", getWechatTradeNo())
            .append("mchTradeNo", getMchTradeNo())
            .append("userId", getUserId())
            .append("tradeType", getTradeType())
            .append("tradeStatus", getTradeStatus())
            .append("payBank", getPayBank())
            .append("currencyType", getCurrencyType())
            .append("tradePayable", getTradePayable())
            .append("couponPrice", getCouponPrice())
            .append("wechatRefundNo", getWechatRefundNo())
            .append("mchRefundNo", getMchRefundNo())
            .append("refundPrice", getRefundPrice())
            .append("couponRefundPrice", getCouponRefundPrice())
            .append("refundType", getRefundType())
            .append("refundStatus", getRefundStatus())
            .append("goodsName", getGoodsName())
            .append("mchDataPackage", getMchDataPackage())
            .append("serviceFee", getServiceFee())
            .append("serviceRate", getServiceRate())
            .append("tradePrice", getTradePrice())
            .append("requestRefund", getRequestRefund())
            .append("rateRemark", getRateRemark())
            .toString();
    }
}
