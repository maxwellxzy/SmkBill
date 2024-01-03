package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.WechatFund;

/**
 * 微信支付资金账单Service接口
 * 
 * @author xuzy
 * @date 2023-12-16
 */
public interface IWechatFundService 
{
    /**
     * 查询微信支付资金账单
     * 
     * @param columnId 微信支付资金账单主键
     * @return 微信支付资金账单
     */
    public WechatFund selectWechatFundByColumnId(Long columnId);

    /**
     * 查询微信支付资金账单列表
     * 
     * @param wechatFund 微信支付资金账单
     * @return 微信支付资金账单集合
     */
    public List<WechatFund> selectWechatFundList(WechatFund wechatFund);

    /**
     * 新增微信支付资金账单
     * 
     * @param wechatFund 微信支付资金账单
     * @return 结果
     */
    public int insertWechatFund(WechatFund wechatFund);

    /**
     * 修改微信支付资金账单
     * 
     * @param wechatFund 微信支付资金账单
     * @return 结果
     */
    public int updateWechatFund(WechatFund wechatFund);

    /**
     * 批量删除微信支付资金账单
     * 
     * @param columnIds 需要删除的微信支付资金账单主键集合
     * @return 结果
     */
    public int deleteWechatFundByColumnIds(Long[] columnIds);

    /**
     * 删除微信支付资金账单信息
     * 
     * @param columnId 微信支付资金账单主键
     * @return 结果
     */
    public int deleteWechatFundByColumnId(Long columnId);
}
