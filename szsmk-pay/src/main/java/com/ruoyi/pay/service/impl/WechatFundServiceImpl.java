package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.WechatFundMapper;
import com.ruoyi.pay.domain.WechatFund;
import com.ruoyi.pay.service.IWechatFundService;

/**
 * 微信支付资金账单Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Service
public class WechatFundServiceImpl implements IWechatFundService 
{
    @Autowired
    private WechatFundMapper wechatFundMapper;

    /**
     * 查询微信支付资金账单
     * 
     * @param columnId 微信支付资金账单主键
     * @return 微信支付资金账单
     */
    @Override
    public WechatFund selectWechatFundByColumnId(Long columnId)
    {
        return wechatFundMapper.selectWechatFundByColumnId(columnId);
    }

    /**
     * 查询微信支付资金账单列表
     * 
     * @param wechatFund 微信支付资金账单
     * @return 微信支付资金账单
     */
    @Override
    public List<WechatFund> selectWechatFundList(WechatFund wechatFund)
    {
        return wechatFundMapper.selectWechatFundList(wechatFund);
    }

    /**
     * 新增微信支付资金账单
     * 
     * @param wechatFund 微信支付资金账单
     * @return 结果
     */
    @Override
    public int insertWechatFund(WechatFund wechatFund)
    {
        return wechatFundMapper.insertWechatFund(wechatFund);
    }

    /**
     * 修改微信支付资金账单
     * 
     * @param wechatFund 微信支付资金账单
     * @return 结果
     */
    @Override
    public int updateWechatFund(WechatFund wechatFund)
    {
        return wechatFundMapper.updateWechatFund(wechatFund);
    }

    /**
     * 批量删除微信支付资金账单
     * 
     * @param columnIds 需要删除的微信支付资金账单主键
     * @return 结果
     */
    @Override
    public int deleteWechatFundByColumnIds(Long[] columnIds)
    {
        return wechatFundMapper.deleteWechatFundByColumnIds(columnIds);
    }

    /**
     * 删除微信支付资金账单信息
     * 
     * @param columnId 微信支付资金账单主键
     * @return 结果
     */
    @Override
    public int deleteWechatFundByColumnId(Long columnId)
    {
        return wechatFundMapper.deleteWechatFundByColumnId(columnId);
    }
}
