package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.AlipayFundSum;

/**
 * 支付宝资金汇总Mapper接口
 * 
 * @author xuzy
 * @date 2023-12-16
 */
public interface AlipayFundSumMapper 
{
    /**
     * 查询支付宝资金汇总
     * 
     * @param columnId 支付宝资金汇总主键
     * @return 支付宝资金汇总
     */
    public AlipayFundSum selectAlipayFundSumByColumnId(Long columnId);

    /**
     * 查询支付宝资金汇总列表
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 支付宝资金汇总集合
     */
    public List<AlipayFundSum> selectAlipayFundSumList(AlipayFundSum alipayFundSum);

    /**
     * 新增支付宝资金汇总
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 结果
     */
    public int insertAlipayFundSum(AlipayFundSum alipayFundSum);

    /**
     * 修改支付宝资金汇总
     * 
     * @param alipayFundSum 支付宝资金汇总
     * @return 结果
     */
    public int updateAlipayFundSum(AlipayFundSum alipayFundSum);

    /**
     * 删除支付宝资金汇总
     * 
     * @param columnId 支付宝资金汇总主键
     * @return 结果
     */
    public int deleteAlipayFundSumByColumnId(Long columnId);

    /**
     * 批量删除支付宝资金汇总
     * 
     * @param columnIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlipayFundSumByColumnIds(Long[] columnIds);
}
