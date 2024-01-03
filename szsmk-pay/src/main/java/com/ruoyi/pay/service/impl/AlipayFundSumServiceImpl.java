package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.AlipayFundSumMapper;
import com.ruoyi.pay.domain.AlipayFundSum;
import com.ruoyi.pay.service.IAlipayFundSumService;

/**
 * 支付宝资金汇总Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@Service
public class AlipayFundSumServiceImpl implements IAlipayFundSumService 
{
    @Autowired
    private AlipayFundSumMapper alipayFundSumMapper;

    /**
     * 查询支付宝资金汇总
     * 
     * @param columnId 支付宝资金汇总主键
     * @return 支付宝资金汇总
     */
    @Override
    public AlipayFundSum selectAlipayFundSumByColumnId(Long columnId)
    {
        return alipayFundSumMapper.selectAlipayFundSumByColumnId(columnId);
    }

    /**
     * 查询支付宝资金汇总列表
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 支付宝资金汇总
     */
    @Override
    public List<AlipayFundSum> selectAlipayFundSumList(AlipayFundSum alipayFundSum)
    {
        return alipayFundSumMapper.selectAlipayFundSumList(alipayFundSum);
    }

    /**
     * 新增支付宝资金汇总
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 结果
     */
    @Override
    public int insertAlipayFundSum(AlipayFundSum alipayFundSum)
    {
        return alipayFundSumMapper.insertAlipayFundSum(alipayFundSum);
    }

    /**
     * 修改支付宝资金汇总
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 结果
     */
    @Override
    public int updateAlipayFundSum(AlipayFundSum alipayFundSum)
    {
        return alipayFundSumMapper.updateAlipayFundSum(alipayFundSum);
    }

    /**
     * 批量删除支付宝资金汇总
     * 
     * @param columnIds 需要删除的支付宝资金汇总主键
     * @return 结果
     */
    @Override
    public int deleteAlipayFundSumByColumnIds(Long[] columnIds)
    {
        return alipayFundSumMapper.deleteAlipayFundSumByColumnIds(columnIds);
    }

    /**
     * 删除支付宝资金汇总信息
     * 
     * @param columnId 支付宝资金汇总主键
     * @return 结果
     */
    @Override
    public int deleteAlipayFundSumByColumnId(Long columnId)
    {
        return alipayFundSumMapper.deleteAlipayFundSumByColumnId(columnId);
    }
}
