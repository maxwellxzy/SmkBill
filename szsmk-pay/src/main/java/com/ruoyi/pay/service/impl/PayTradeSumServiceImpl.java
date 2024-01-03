package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.PayTradeSumMapper;
import com.ruoyi.pay.domain.PayTradeSum;
import com.ruoyi.pay.service.IPayTradeSumService;

/**
 * 每日汇总Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-27
 */
@Service
public class PayTradeSumServiceImpl implements IPayTradeSumService 
{
    @Autowired
    private PayTradeSumMapper payTradeSumMapper;

    /**
     * 查询每日汇总
     * 
     * @param id 每日汇总主键
     * @return 每日汇总
     */
    @Override
    public PayTradeSum selectPayTradeSumById(Long id)
    {
        return payTradeSumMapper.selectPayTradeSumById(id);
    }

    /**
     * 查询每日汇总列表
     * 
     * @param payTradeSum 每日汇总
     * @return 每日汇总
     */
    @Override
    public List<PayTradeSum> selectPayTradeSumList(PayTradeSum payTradeSum)
    {
        return payTradeSumMapper.selectPayTradeSumList(payTradeSum);
    }

    /**
     * 新增每日汇总
     * 
     * @param payTradeSum 每日汇总
     * @return 结果
     */
    @Override
    public int insertPayTradeSum(PayTradeSum payTradeSum)
    {
        return payTradeSumMapper.insertPayTradeSum(payTradeSum);
    }

    /**
     * 修改每日汇总
     * 
     * @param payTradeSum 每日汇总
     * @return 结果
     */
    @Override
    public int updatePayTradeSum(PayTradeSum payTradeSum)
    {
        return payTradeSumMapper.updatePayTradeSum(payTradeSum);
    }

    /**
     * 批量删除每日汇总
     * 
     * @param ids 需要删除的每日汇总主键
     * @return 结果
     */
    @Override
    public int deletePayTradeSumByIds(Long[] ids)
    {
        return payTradeSumMapper.deletePayTradeSumByIds(ids);
    }

    /**
     * 删除每日汇总信息
     * 
     * @param id 每日汇总主键
     * @return 结果
     */
    @Override
    public int deletePayTradeSumById(Long id)
    {
        return payTradeSumMapper.deletePayTradeSumById(id);
    }
}
