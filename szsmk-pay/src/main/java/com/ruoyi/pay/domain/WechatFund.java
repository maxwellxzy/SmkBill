package com.ruoyi.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信支付资金账单对象 wechat_fund
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Data
public class WechatFund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long columnId;
    @Excel(name = "商户号")
    private String mchId;
    /** 记账时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记账时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 微信支付业务单号 */
    @Excel(name = "微信支付业务单号")
    private String wechatTradeNo;

    /** 资金流水单号 */
    @Excel(name = "资金流水单号")
    private String fundNo;

    /** 业务名称 */
    @Excel(name = "业务名称")
    private String tradeName;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String tradeType;

    /** 收支类型 */
    @Excel(name = "收支类型")
    private String inOutType;

    /** 收支金额(元) */
    @Excel(name = "收支金额(元)")
    private Double fundPrice;

    /** 账户结余(元) */
    @Excel(name = "账户结余(元)")
    private Double accountBalance;

    /** 资金变更提交申请人 */
    @Excel(name = "资金变更提交申请人")
    private String fundUser;

    /** 业务凭证号 */
    @Excel(name = "业务凭证号")
    private String voucherNo;

/** use@Date

    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId()
    {
        return columnId;
    }
    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }
    public void setWechatTradeNo(String wechatTradeNo) 
    {
        this.wechatTradeNo = wechatTradeNo;
    }

    public String getWechatTradeNo() 
    {
        return wechatTradeNo;
    }
    public void setFundNo(String fundNo) 
    {
        this.fundNo = fundNo;
    }

    public String getFundNo() 
    {
        return fundNo;
    }
    public void setTradeName(String tradeName) 
    {
        this.tradeName = tradeName;
    }

    public String getTradeName() 
    {
        return tradeName;
    }
    public void setTradeType(String tradeType) 
    {
        this.tradeType = tradeType;
    }

    public String getTradeType() 
    {
        return tradeType;
    }
    public void setInOutType(String inOutType) 
    {
        this.inOutType = inOutType;
    }

    public String getInOutType() 
    {
        return inOutType;
    }
    public void setFundPrice(Double fundPrice)
    {
        this.fundPrice = fundPrice;
    }

    public Double getFundPrice()
    {
        return fundPrice;
    }
    public void setAccountBalance(Double accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public Double getAccountBalance()
    {
        return accountBalance;
    }
    public void setFundUser(String fundUser) 
    {
        this.fundUser = fundUser;
    }

    public String getFundUser() 
    {
        return fundUser;
    }
    public void setVoucherNo(String voucherNo) 
    {
        this.voucherNo = voucherNo;
    }

    public String getVoucherNo() 
    {
        return voucherNo;
    }
*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("recordTime", getRecordTime())
            .append("wechatTradeNo", getWechatTradeNo())
            .append("fundNo", getFundNo())
            .append("tradeName", getTradeName())
            .append("tradeType", getTradeType())
            .append("inOutType", getInOutType())
            .append("fundPrice", getFundPrice())
            .append("accountBalance", getAccountBalance())
            .append("fundUser", getFundUser())
            .append("remark", getRemark())
            .append("voucherNo", getVoucherNo())
            .toString();
    }
}
