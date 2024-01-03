package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.PayMchId;

/**
 * 商户号管理Service接口
 * 
 * @author xuzy
 * @date 2023-12-30
 */
public interface IPayMchIdService 
{
    /**
     * 查询商户号管理
     * 
     * @param id 商户号管理主键
     * @return 商户号管理
     */
    public PayMchId selectPayMchIdById(Long id);

    /**
     * 查询商户号管理列表
     * 
     * @param payMchId 商户号管理
     * @return 商户号管理集合
     */
    public List<PayMchId> selectPayMchIdList(PayMchId payMchId);

    /**
     * 新增商户号管理
     * 
     * @param payMchId 商户号管理
     * @return 结果
     */
    public int insertPayMchId(PayMchId payMchId);

    /**
     * 修改商户号管理
     * 
     * @param payMchId 商户号管理
     * @return 结果
     */
    public int updatePayMchId(PayMchId payMchId);

    /**
     * 批量删除商户号管理
     * 
     * @param ids 需要删除的商户号管理主键集合
     * @return 结果
     */
    public int deletePayMchIdByIds(Long[] ids);

    /**
     * 删除商户号管理信息
     * 
     * @param id 商户号管理主键
     * @return 结果
     */
    public int deletePayMchIdById(Long id);
}
