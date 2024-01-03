package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.PayTradeType;

/**
 * 业务分类Mapper接口
 * 
 * @author xuzy
 * @date 2023-12-29
 */
public interface PayTradeTypeMapper 
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
     * 删除业务分类
     * 
     * @param id 业务分类主键
     * @return 结果
     */
    public int deletePayTradeTypeById(Long id);

    /**
     * 批量删除业务分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayTradeTypeByIds(Long[] ids);
}
