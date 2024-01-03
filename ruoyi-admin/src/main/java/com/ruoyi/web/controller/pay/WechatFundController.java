package com.ruoyi.web.controller.pay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.WechatFund;
import com.ruoyi.pay.service.IWechatFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信支付资金账单Controller
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/pay/wefund")
public class WechatFundController extends BaseController
{
    @Autowired
    private IWechatFundService wechatFundService;

    /**
     * 查询微信支付资金账单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:list')")
    @GetMapping("/list")
    public TableDataInfo list(WechatFund wechatFund)
    {
        startPage();
        List<WechatFund> list = wechatFundService.selectWechatFundList(wechatFund);
        return getDataTable(list);
    }

    /**
     * 导出微信支付资金账单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:export')")
    @Log(title = "微信支付资金账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WechatFund wechatFund)
    {
        List<WechatFund> list = wechatFundService.selectWechatFundList(wechatFund);
        ExcelUtil<WechatFund> util = new ExcelUtil<WechatFund>(WechatFund.class);
        util.exportExcel(response, list, "微信支付资金账单数据");
    }

    /**
     * 获取微信支付资金账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:query')")
    @GetMapping(value = "/{columnId}")
    public AjaxResult getInfo(@PathVariable("columnId") Long columnId)
    {
        return success(wechatFundService.selectWechatFundByColumnId(columnId));
    }

    /**
     * 新增微信支付资金账单
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:add')")
    @Log(title = "微信支付资金账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WechatFund wechatFund)
    {
        return toAjax(wechatFundService.insertWechatFund(wechatFund));
    }

    /**
     * 修改微信支付资金账单
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:edit')")
    @Log(title = "微信支付资金账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WechatFund wechatFund)
    {
        return toAjax(wechatFundService.updateWechatFund(wechatFund));
    }

    /**
     * 删除微信支付资金账单
     */
    @PreAuthorize("@ss.hasPermi('pay:wefund:remove')")
    @Log(title = "微信支付资金账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{columnIds}")
    public AjaxResult remove(@PathVariable Long[] columnIds)
    {
        return toAjax(wechatFundService.deleteWechatFundByColumnIds(columnIds));
    }
}
