package com.ruoyi.web.controller.pay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.IWechatTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信交易订单Controller
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/pay/wetrade")
public class WechatTradeController extends BaseController
{
    @Autowired
    private IWechatTradeService wechatTradeService;

    /**
     * 查询微信交易订单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:list')")
    @GetMapping("/list")
    public TableDataInfo list(WechatTrade wechatTrade)
    {
        startPage();
        List<WechatTrade> list = wechatTradeService.selectWechatTradeList(wechatTrade);
        return getDataTable(list);
    }

    /**
     * 导出微信交易订单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:export')")
    @Log(title = "微信交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WechatTrade wechatTrade)
    {
        List<WechatTrade> list = wechatTradeService.selectWechatTradeList(wechatTrade);
        ExcelUtil<WechatTrade> util = new ExcelUtil<WechatTrade>(WechatTrade.class);
        util.exportExcel(response, list, "微信交易订单数据");
    }

    /**
     * 获取微信交易订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:query')")
    @GetMapping(value = "/{columnId}")
    public AjaxResult getInfo(@PathVariable("columnId") Long columnId)
    {
        return success(wechatTradeService.selectWechatTradeByColumnId(columnId));
    }

    /**
     * 新增微信交易订单
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:add')")
    @Log(title = "微信交易订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WechatTrade wechatTrade)
    {
        return toAjax(wechatTradeService.insertWechatTrade(wechatTrade));
    }

    /**
     * 修改微信交易订单
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:edit')")
    @Log(title = "微信交易订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WechatTrade wechatTrade)
    {
        return toAjax(wechatTradeService.updateWechatTrade(wechatTrade));
    }

    /**
     * 删除微信交易订单
     */
    @PreAuthorize("@ss.hasPermi('pay:wetrade:remove')")
    @Log(title = "微信交易订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{columnIds}")
    public AjaxResult remove(@PathVariable Long[] columnIds)
    {
        return toAjax(wechatTradeService.deleteWechatTradeByColumnIds(columnIds));
    }
}
