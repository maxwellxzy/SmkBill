package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.WechatTrade;

/**
 * 微信交易订单Mapper接口
 * 
 * @author xuzy
 * @date 2023-12-16
 */
public interface WechatTradeMapper 
{
    /**
     * 查询微信交易订单
     * 
     * @param columnId 微信交易订单主键
     * @return 微信交易订单
     */
    public WechatTrade selectWechatTradeByColumnId(Long columnId);

    /**
     * 查询微信交易订单列表
     * 
     * @param wechatTrade 微信交易订单
     * @return 微信交易订单集合
     */
    public List<WechatTrade> selectWechatTradeList(WechatTrade wechatTrade);

    /**
     * 新增微信交易订单
     * 
     * @param wechatTrade 微信交易订单
     * @return 结果
     */
    public int insertWechatTrade(WechatTrade wechatTrade);

    /**
     * 修改微信交易订单
     * 
     * @param wechatTrade 微信交易订单
     * @return 结果
     */
    public int updateWechatTrade(WechatTrade wechatTrade);

    /**
     * 删除微信交易订单
     * 
     * @param columnId 微信交易订单主键
     * @return 结果
     */
    public int deleteWechatTradeByColumnId(Long columnId);

    /**
     * 批量删除微信交易订单
     * 
     * @param columnIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWechatTradeByColumnIds(Long[] columnIds);
}
