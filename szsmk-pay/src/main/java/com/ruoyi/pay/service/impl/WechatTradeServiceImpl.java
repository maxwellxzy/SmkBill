package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.WechatTradeMapper;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.IWechatTradeService;

/**
 * 微信交易订单Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Service
public class WechatTradeServiceImpl implements IWechatTradeService 
{
    @Autowired
    private WechatTradeMapper wechatTradeMapper;

    /**
     * 查询微信交易订单
     * 
     * @param columnId 微信交易订单主键
     * @return 微信交易订单
     */
    @Override
    public WechatTrade selectWechatTradeByColumnId(Long columnId)
    {
        return wechatTradeMapper.selectWechatTradeByColumnId(columnId);
    }

    /**
     * 查询微信交易订单列表
     * 
     * @param wechatTrade 微信交易订单
     * @return 微信交易订单
     */
    @Override
    public List<WechatTrade> selectWechatTradeList(WechatTrade wechatTrade)
    {
        return wechatTradeMapper.selectWechatTradeList(wechatTrade);
    }

    /**
     * 新增微信交易订单
     * 
     * @param wechatTrade 微信交易订单
     * @return 结果
     */
    @Override
    public int insertWechatTrade(WechatTrade wechatTrade)
    {
        return wechatTradeMapper.insertWechatTrade(wechatTrade);
    }

    /**
     * 修改微信交易订单
     * 
     * @param wechatTrade 微信交易订单
     * @return 结果
     */
    @Override
    public int updateWechatTrade(WechatTrade wechatTrade)
    {
        return wechatTradeMapper.updateWechatTrade(wechatTrade);
    }

    /**
     * 批量删除微信交易订单
     * 
     * @param columnIds 需要删除的微信交易订单主键
     * @return 结果
     */
    @Override
    public int deleteWechatTradeByColumnIds(Long[] columnIds)
    {
        return wechatTradeMapper.deleteWechatTradeByColumnIds(columnIds);
    }

    /**
     * 删除微信交易订单信息
     * 
     * @param columnId 微信交易订单主键
     * @return 结果
     */
    @Override
    public int deleteWechatTradeByColumnId(Long columnId)
    {
        return wechatTradeMapper.deleteWechatTradeByColumnId(columnId);
    }
}
