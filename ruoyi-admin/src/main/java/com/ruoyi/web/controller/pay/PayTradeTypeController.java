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
import com.ruoyi.pay.domain.PayTradeType;
import com.ruoyi.pay.service.IPayTradeTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务分类Controller
 * 
 * @author xuzy
 * @date 2023-12-29
 */
@RestController
@RequestMapping("/pay/tradetype")
public class PayTradeTypeController extends BaseController
{
    @Autowired
    private IPayTradeTypeService payTradeTypeService;

    /**
     * 查询业务分类列表
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTradeType payTradeType)
    {
        startPage();
        List<PayTradeType> list = payTradeTypeService.selectPayTradeTypeList(payTradeType);
        return getDataTable(list);
    }

    /**
     * 导出业务分类列表
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:export')")
    @Log(title = "业务分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayTradeType payTradeType)
    {
        List<PayTradeType> list = payTradeTypeService.selectPayTradeTypeList(payTradeType);
        ExcelUtil<PayTradeType> util = new ExcelUtil<PayTradeType>(PayTradeType.class);
        util.exportExcel(response, list, "业务分类数据");
    }

    /**
     * 获取业务分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payTradeTypeService.selectPayTradeTypeById(id));
    }

    /**
     * 新增业务分类
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:add')")
    @Log(title = "业务分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayTradeType payTradeType)
    {
        return toAjax(payTradeTypeService.insertPayTradeType(payTradeType));
    }

    /**
     * 修改业务分类
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:edit')")
    @Log(title = "业务分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayTradeType payTradeType)
    {
        return toAjax(payTradeTypeService.updatePayTradeType(payTradeType));
    }

    /**
     * 删除业务分类
     */
    @PreAuthorize("@ss.hasPermi('pay:tradetype:remove')")
    @Log(title = "业务分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payTradeTypeService.deletePayTradeTypeByIds(ids));
    }
}
