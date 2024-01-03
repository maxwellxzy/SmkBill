package com.ruoyi.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pay.mapper.GoodsNameMapper;
import com.ruoyi.pay.domain.GoodsName;
import com.ruoyi.pay.service.IGoodsNameService;

/**
 * 商品名称分类Service业务层处理
 * 
 * @author xuzy
 * @date 2024-01-03
 */
@Service
public class GoodsNameServiceImpl implements IGoodsNameService 
{
    @Autowired
    private GoodsNameMapper goodsNameMapper;

    /**
     * 查询商品名称分类
     * 
     * @param id 商品名称分类主键
     * @return 商品名称分类
     */
    @Override
    public GoodsName selectGoodsNameById(Long id)
    {
        return goodsNameMapper.selectGoodsNameById(id);
    }

    /**
     * 查询商品名称分类列表
     * 
     * @param goodsName 商品名称分类
     * @return 商品名称分类
     */
    @Override
    public List<GoodsName> selectGoodsNameList(GoodsName goodsName)
    {
        return goodsNameMapper.selectGoodsNameList(goodsName);
    }

    /**
     * 新增商品名称分类
     * 
     * @param goodsName 商品名称分类
     * @return 结果
     */
    @Override
    public int insertGoodsName(GoodsName goodsName)
    {
        return goodsNameMapper.insertGoodsName(goodsName);
    }

    /**
     * 修改商品名称分类
     * 
     * @param goodsName 商品名称分类
     * @return 结果
     */
    @Override
    public int updateGoodsName(GoodsName goodsName)
    {
        return goodsNameMapper.updateGoodsName(goodsName);
    }

    /**
     * 批量删除商品名称分类
     * 
     * @param ids 需要删除的商品名称分类主键
     * @return 结果
     */
    @Override
    public int deleteGoodsNameByIds(Long[] ids)
    {
        return goodsNameMapper.deleteGoodsNameByIds(ids);
    }

    /**
     * 删除商品名称分类信息
     * 
     * @param id 商品名称分类主键
     * @return 结果
     */
    @Override
    public int deleteGoodsNameById(Long id)
    {
        return goodsNameMapper.deleteGoodsNameById(id);
    }
}
