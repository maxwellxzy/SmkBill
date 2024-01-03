package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.AlipayTrade;

/**
 * 支付宝交易明细单Mapper接口
 * 
 * @author xuzy
 * @date 2023-12-16
 */
public interface AlipayTradeMapper 
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
     * 删除支付宝交易明细单
     * 
     * @param columnId 支付宝交易明细单主键
     * @return 结果
     */
    public int deleteAlipayTradeByColumnId(Long columnId);

    /**
     * 批量删除支付宝交易明细单
     * 
     * @param columnIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlipayTradeByColumnIds(Long[] columnIds);
}
