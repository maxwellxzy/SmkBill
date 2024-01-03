package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.PayTradeSum;

/**
 * 每日汇总Mapper接口
 * 
 * @author xuzy
 * @date 2023-12-27
 */
public interface PayTradeSumMapper 
{
    /**
     * 查询每日汇总
     * 
     * @param id 每日汇总主键
     * @return 每日汇总
     */
    public PayTradeSum selectPayTradeSumById(Long id);

    /**
     * 查询每日汇总列表
     * 
     * @param payTradeSum 每日汇总
     * @return 每日汇总集合
     */
    public List<PayTradeSum> selectPayTradeSumList(PayTradeSum payTradeSum);

    /**
     * 新增每日汇总
     * 
     * @param payTradeSum 每日汇总
     * @return 结果
     */
    public int insertPayTradeSum(PayTradeSum payTradeSum);

    /**
     * 修改每日汇总
     * 
     * @param payTradeSum 每日汇总
     * @return 结果
     */
    public int updatePayTradeSum(PayTradeSum payTradeSum);

    /**
     * 删除每日汇总
     * 
     * @param id 每日汇总主键
     * @return 结果
     */
    public int deletePayTradeSumById(Long id);

    /**
     * 批量删除每日汇总
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayTradeSumByIds(Long[] ids);
}
