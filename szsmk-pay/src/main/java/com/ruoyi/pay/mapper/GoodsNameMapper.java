package com.ruoyi.pay.mapper;

import java.util.List;
import com.ruoyi.pay.domain.GoodsName;

/**
 * 商品名称分类Mapper接口
 * 
 * @author xuzy
 * @date 2024-01-03
 */
public interface GoodsNameMapper 
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
     * 删除商品名称分类
     * 
     * @param id 商品名称分类主键
     * @return 结果
     */
    public int deleteGoodsNameById(Long id);

    /**
     * 批量删除商品名称分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsNameByIds(Long[] ids);
}
