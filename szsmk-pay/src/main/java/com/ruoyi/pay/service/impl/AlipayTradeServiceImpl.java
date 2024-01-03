package com.ruoyi.pay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.AlipayTradeMapper;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.service.IAlipayTradeService;

/**
 * 支付宝交易明细单Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Service
public class AlipayTradeServiceImpl implements IAlipayTradeService 
{
    @Autowired
    private AlipayTradeMapper alipayTradeMapper;

    /**
     * 查询支付宝交易明细单
     * 
     * @param columnId 支付宝交易明细单主键
     * @return 支付宝交易明细单
     */
    @Override
    public AlipayTrade selectAlipayTradeByColumnId(Long columnId)
    {
        return alipayTradeMapper.selectAlipayTradeByColumnId(columnId);
    }

    /**
     * 查询支付宝交易明细单列表
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 支付宝交易明细单
     */
    @Override
    public List<AlipayTrade> selectAlipayTradeList(AlipayTrade alipayTrade)
    {
        return alipayTradeMapper.selectAlipayTradeList(alipayTrade);
    }

    /**
     * 新增支付宝交易明细单
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 结果
     */
    @Override
    public int insertAlipayTrade(AlipayTrade alipayTrade)
    {
        alipayTrade.setCreateTime(DateUtils.getNowDate());
        return alipayTradeMapper.insertAlipayTrade(alipayTrade);
    }

    /**
     * 修改支付宝交易明细单
     * 
     * @param alipayTrade 支付宝交易明细单
     * @return 结果
     */
    @Override
    public int updateAlipayTrade(AlipayTrade alipayTrade)
    {
        return alipayTradeMapper.updateAlipayTrade(alipayTrade);
    }

    /**
     * 批量删除支付宝交易明细单
     * 
     * @param columnIds 需要删除的支付宝交易明细单主键
     * @return 结果
     */
    @Override
    public int deleteAlipayTradeByColumnIds(Long[] columnIds)
    {
        return alipayTradeMapper.deleteAlipayTradeByColumnIds(columnIds);
    }

    /**
     * 删除支付宝交易明细单信息
     * 
     * @param columnId 支付宝交易明细单主键
     * @return 结果
     */
    @Override
    public int deleteAlipayTradeByColumnId(Long columnId)
    {
        return alipayTradeMapper.deleteAlipayTradeByColumnId(columnId);
    }
}
