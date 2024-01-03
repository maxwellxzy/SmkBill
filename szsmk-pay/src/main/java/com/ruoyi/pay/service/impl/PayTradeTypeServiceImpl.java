package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.PayTradeTypeMapper;
import com.ruoyi.pay.domain.PayTradeType;
import com.ruoyi.pay.service.IPayTradeTypeService;

/**
 * 业务分类Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-29
 */
@Service
public class PayTradeTypeServiceImpl implements IPayTradeTypeService 
{
    @Autowired
    private PayTradeTypeMapper payTradeTypeMapper;

    /**
     * 查询业务分类
     * 
     * @param id 业务分类主键
     * @return 业务分类
     */
    @Override
    public PayTradeType selectPayTradeTypeById(Long id)
    {
        return payTradeTypeMapper.selectPayTradeTypeById(id);
    }

    /**
     * 查询业务分类列表
     * 
     * @param payTradeType 业务分类
     * @return 业务分类
     */
    @Override
    public List<PayTradeType> selectPayTradeTypeList(PayTradeType payTradeType)
    {
        return payTradeTypeMapper.selectPayTradeTypeList(payTradeType);
    }

    /**
     * 新增业务分类
     * 
     * @param payTradeType 业务分类
     * @return 结果
     */
    @Override
    public int insertPayTradeType(PayTradeType payTradeType)
    {
        return payTradeTypeMapper.insertPayTradeType(payTradeType);
    }

    /**
     * 修改业务分类
     * 
     * @param payTradeType 业务分类
     * @return 结果
     */
    @Override
    public int updatePayTradeType(PayTradeType payTradeType)
    {
        return payTradeTypeMapper.updatePayTradeType(payTradeType);
    }

    /**
     * 批量删除业务分类
     * 
     * @param ids 需要删除的业务分类主键
     * @return 结果
     */
    @Override
    public int deletePayTradeTypeByIds(Long[] ids)
    {
        return payTradeTypeMapper.deletePayTradeTypeByIds(ids);
    }

    /**
     * 删除业务分类信息
     * 
     * @param id 业务分类主键
     * @return 结果
     */
    @Override
    public int deletePayTradeTypeById(Long id)
    {
        return payTradeTypeMapper.deletePayTradeTypeById(id);
    }
}
