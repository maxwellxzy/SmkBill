package com.ruoyi.pay.service;

import java.util.List;
import com.ruoyi.pay.domain.GoodsName;

/**
 * 商品名称分类Service接口
 * 
 * @author xuzy
 * @date 2024-01-03
 */
public interface IGoodsNameService 
{
    /**
     * 查询商品名称分类
     * 
     * @param id 商品名称分类主键
     * @return 商品名称分类
     */
    public GoodsName selectGoodsNameById(Long id);

    /**
     * 查询商品名称分类列表
     * 
     * @param goodsName 商品名称分类
     * @return 商品名称分类集合
     */
    public List<GoodsName> selectGoodsNameList(GoodsName goodsName);

    /**
     * 新增商品名称分类
     * 
     * @param goodsName 商品名称分类
     * @return 结果
     */
    public int insertGoodsName(GoodsName goodsName);

    /**
     * 修改商品名称分类
     * 
     * @param goodsName 商品名称分类
     * @return 结果
     */
    public int updateGoodsName(GoodsName goodsName);

    /**
     * 批量删除商品名称分类
     * 
     * @param ids 需要删除的商品名称分类主键集合
     * @return 结果
     */
    public int deleteGoodsNameByIds(Long[] ids);

    /**
     * 删除商品名称分类信息
     * 
     * @param id 商品名称分类主键
     * @return 结果
     */
    public int deleteGoodsNameById(Long id);
}
