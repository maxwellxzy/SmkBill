package com.ruoyi.web.controller.pay;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.pay.domain.GoodsName;
import com.ruoyi.pay.service.IGoodsNameService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品名称分类Controller
 * 
 * @author xuzy
 * @date 2024-01-03
 */
@RestController
@RequestMapping("/pay/goodsname")
public class GoodsNameController extends BaseController
{
    @Autowired
    private IGoodsNameService goodsNameService;

    /**
     * 查询商品名称分类列表
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsName goodsName)
    {
        startPage();
        List<GoodsName> list = goodsNameService.selectGoodsNameList(goodsName);
        return getDataTable(list);
    }

    /**
     * 导出商品名称分类列表
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:export')")
    @Log(title = "商品名称分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsName goodsName)
    {
        List<GoodsName> list = goodsNameService.selectGoodsNameList(goodsName);
        ExcelUtil<GoodsName> util = new ExcelUtil<GoodsName>(GoodsName.class);
        util.exportExcel(response, list, "商品名称分类数据");
    }

    /**
     * 获取商品名称分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(goodsNameService.selectGoodsNameById(id));
    }

    /**
     * 新增商品名称分类
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:add')")
    @Log(title = "商品名称分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsName goodsName)
    {
        return toAjax(goodsNameService.insertGoodsName(goodsName));
    }

    /**
     * 修改商品名称分类
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:edit')")
    @Log(title = "商品名称分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsName goodsName)
    {
        return toAjax(goodsNameService.updateGoodsName(goodsName));
    }

    /**
     * 删除商品名称分类
     */
    @PreAuthorize("@ss.hasPermi('pay:goodsname:remove')")
    @Log(title = "商品名称分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsNameService.deleteGoodsNameByIds(ids));
    }
}
