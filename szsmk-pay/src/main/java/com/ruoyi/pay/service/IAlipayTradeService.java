package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.AlipayTrade;

/**
 * 支付宝交易明细单Service接口
 * 
 * @author xuzy
 * @date 2023-12-16
 */
public interface IAlipayTradeService 
{
    /**
     * 查询支付宝交易明细单
     * 
     * @param columnId 支付宝交易明细单主键
     * @return 支付宝交易明细单
     */
    public AlipayTrade selectAlipayTradeByColumnId(Long columnId);

    /**
     * 查询支付宝交易明细单列表
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 支付宝交易明细单集合
     */
    public List<AlipayTrade> selectAlipayTradeList(AlipayTrade alipayTrade);

    /**
     * 新增支付宝交易明细单
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 结果
     */
    public int insertAlipayTrade(AlipayTrade alipayTrade);

    /**
     * 修改支付宝交易明细单
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 结果
     */
    public int updateAlipayTrade(AlipayTrade alipayTrade);

    /**
     * 批量删除支付宝交易明细单
     * 
     * @param columnIds 需要删除的支付宝交易明细单主键集合
     * @return 结果
     */
    public int deleteAlipayTradeByColumnIds(Long[] columnIds);

    /**
     * 删除支付宝交易明细单信息
     * 
     * @param columnId 支付宝交易明细单主键
     * @return 结果
     */
    public int deleteAlipayTradeByColumnId(Long columnId);
}
