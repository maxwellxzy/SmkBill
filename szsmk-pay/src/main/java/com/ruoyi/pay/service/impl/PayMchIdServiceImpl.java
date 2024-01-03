package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.PayMchIdMapper;
import com.ruoyi.pay.domain.PayMchId;
import com.ruoyi.pay.service.IPayMchIdService;

/**
 * 商户号管理Service业务层处理
 * 
 * @author xuzy
 * @date 2023-12-30
 */
@Service
public class PayMchIdServiceImpl implements IPayMchIdService 
{
    @Autowired
    private PayMchIdMapper payMchIdMapper;

    /**
     * 查询商户号管理
     * 
     * @param id 商户号管理主键
     * @return 商户号管理
     */
    @Override
    public PayMchId selectPayMchIdById(Long id)
    {
        return payMchIdMapper.selectPayMchIdById(id);
    }

    /**
     * 查询商户号管理列表
     * 
     * @param payMchId 商户号管理
     * @return 商户号管理
     */
    @Override
    public List<PayMchId> selectPayMchIdList(PayMchId payMchId)
    {
        return payMchIdMapper.selectPayMchIdList(payMchId);
    }

    /**
     * 新增商户号管理
     * 
     * @param payMchId 商户号管理
     * @return 结果
     */
    @Override
    public int insertPayMchId(PayMchId payMchId)
    {
        return payMchIdMapper.insertPayMchId(payMchId);
    }

    /**
     * 修改商户号管理
     * 
     * @param payMchId 商户号管理
     * @return 结果
     */
    @Override
    public int updatePayMchId(PayMchId payMchId)
    {
        return payMchIdMapper.updatePayMchId(payMchId);
    }

    /**
     * 批量删除商户号管理
     * 
     * @param ids 需要删除的商户号管理主键
     * @return 结果
     */
    @Override
    public int deletePayMchIdByIds(Long[] ids)
    {
        return payMchIdMapper.deletePayMchIdByIds(ids);
    }

    /**
     * 删除商户号管理信息
     * 
     * @param id 商户号管理主键
     * @return 结果
     */
    @Override
    public int deletePayMchIdById(Long id)
    {
        return payMchIdMapper.deletePayMchIdById(id);
    }
}
