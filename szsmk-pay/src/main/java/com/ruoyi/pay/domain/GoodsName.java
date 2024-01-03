package com.ruoyi.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品名称分类对象 goods_name
 * 
 * @author xuzy
 * @date 2024-01-03
 */
public class GoodsName extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsNum;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品备注 */
    @Excel(name = "商品备注")
    private String goodsRemark;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsNum(String goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum() 
    {
        return goodsNum;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsRemark(String goodsRemark) 
    {
        this.goodsRemark = goodsRemark;
    }

    public String getGoodsRemark() 
    {
        return goodsRemark;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsNum", getGoodsNum())
            .append("goodsName", getGoodsName())
            .append("goodsRemark", getGoodsRemark())
            .append("status", getStatus())
            .toString();
    }
}
