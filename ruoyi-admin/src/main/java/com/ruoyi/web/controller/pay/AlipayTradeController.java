package com.ruoyi.web.controller.pay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.service.IAlipayTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 支付宝交易明细单Controller
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/pay/alitrade")
public class AlipayTradeController extends BaseController
{
    @Autowired
    private IAlipayTradeService alipayTradeService;

    /**
     * 查询支付宝交易明细单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlipayTrade alipayTrade)
    {
        startPage();
        List<AlipayTrade> list = alipayTradeService.selectAlipayTradeList(alipayTrade);
        return getDataTable(list);
    }

    /**
     * 导出支付宝交易明细单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:export')")
    @Log(title = "支付宝交易明细单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AlipayTrade alipayTrade)
    {
        List<AlipayTrade> list = alipayTradeService.selectAlipayTradeList(alipayTrade);
        ExcelUtil<AlipayTrade> util = new ExcelUtil<AlipayTrade>(AlipayTrade.class);
        util.exportExcel(response, list, "支付宝交易明细单数据");
    }

    /**
     * 获取支付宝交易明细单详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:query')")
    @GetMapping(value = "/{columnId}")
    public AjaxResult getInfo(@PathVariable("columnId") Long columnId)
    {
        return success(alipayTradeService.selectAlipayTradeByColumnId(columnId));
    }

    /**
     * 新增支付宝交易明细单
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:add')")
    @Log(title = "支付宝交易明细单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AlipayTrade alipayTrade)
    {
        return toAjax(alipayTradeService.insertAlipayTrade(alipayTrade));
    }

    /**
     * 修改支付宝交易明细单
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:edit')")
    @Log(title = "支付宝交易明细单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AlipayTrade alipayTrade)
    {
        return toAjax(alipayTradeService.updateAlipayTrade(alipayTrade));
    }

    /**
     * 删除支付宝交易明细单
     */
    @PreAuthorize("@ss.hasPermi('pay:alitrade:remove')")
    @Log(title = "支付宝交易明细单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{columnIds}")
    public AjaxResult remove(@PathVariable Long[] columnIds)
    {
        return toAjax(alipayTradeService.deleteAlipayTradeByColumnIds(columnIds));
    }
}
