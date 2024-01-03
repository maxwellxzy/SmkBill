package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.PayTradeType;

/**
 * 业务分类Service接口
 * 
 * @author xuzy
 * @date 2023-12-29
 */
public interface IPayTradeTypeService 
{
    /**
     * 查询业务分类
     * 
     * @param id 业务分类主键
     * @return 业务分类
     */
    public PayTradeType selectPayTradeTypeById(Long id);

    /**
     * 查询业务分类列表
     * 
     * @param payTradeType 业务分类
     * @return 业务分类集合
     */
    public List<PayTradeType> selectPayTradeTypeList(PayTradeType payTradeType);

    /**
     * 新增业务分类
     * 
     * @param payTradeType 业务分类
     * @return 结果
     */
    public int insertPayTradeType(PayTradeType payTradeType);

    /**
     * 修改业务分类
     * 
     * @param payTradeType 业务分类
     * @return 结果
     */
    public int updatePayTradeType(PayTradeType payTradeType);

    /**
     * 批量删除业务分类
     * 
     * @param ids 需要删除的业务分类主键集合
     * @return 结果
     */
    public int deletePayTradeTypeByIds(Long[] ids);

    /**
     * 删除业务分类信息
     * 
     * @param id 业务分类主键
     * @return 结果
     */
    public int deletePayTradeTypeById(Long id);
}
