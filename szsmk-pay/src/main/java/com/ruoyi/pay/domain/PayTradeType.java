package com.ruoyi.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务分类对象 pay_trade_type
 * 
 * @author xuzy
 * @date 2023-12-29
 */
public class PayTradeType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 业务种类 */
    @Excel(name = "业务种类")
    private String tradeType;

    /** 业务种类名字 */
    @Excel(name = "业务种类名字")
    private String tradeTypeName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTradeType(String tradeType) 
    {
        this.tradeType = tradeType;
    }

    public String getTradeType() 
    {
        return tradeType;
    }
    public void setTradeTypeName(String tradeTypeName) 
    {
        this.tradeTypeName = tradeTypeName;
    }

    public String getTradeTypeName() 
    {
        return tradeTypeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tradeType", getTradeType())
            .append("tradeTypeName", getTradeTypeName())
            .append("remark", getRemark())
            .toString();
    }
}
