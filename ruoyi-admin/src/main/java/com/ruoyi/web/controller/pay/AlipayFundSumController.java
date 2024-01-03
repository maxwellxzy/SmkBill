package com.ruoyi.web.controller.pay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.AlipayFundSum;
import com.ruoyi.pay.service.IAlipayFundSumService;
import com.ruoyi.web.controller.pay.auto.AutoAddData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 支付宝资金汇总Controller
 * 
 * @author xuzy
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/pay/alifundsum")
public class AlipayFundSumController extends BaseController
{
    @Autowired
    private IAlipayFundSumService alipayFundSumService;

    /**
     * 查询支付宝资金汇总列表
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlipayFundSum alipayFundSum)
    {
        startPage();
        List<AlipayFundSum> list = alipayFundSumService.selectAlipayFundSumList(alipayFundSum);
        return getDataTable(list);
    }

    /**
     * 导出支付宝资金汇总列表
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:export')")
    @Log(title = "支付宝资金汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AlipayFundSum alipayFundSum)
    {
        List<AlipayFundSum> list = alipayFundSumService.selectAlipayFundSumList(alipayFundSum);
        ExcelUtil<AlipayFundSum> util = new ExcelUtil<AlipayFundSum>(AlipayFundSum.class);
        util.exportExcel(response, list, "支付宝资金汇总数据");
    }

    /**
     * 获取支付宝资金汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:query')")
    @GetMapping(value = "/{columnId}")
    public AjaxResult getInfo(@PathVariable("columnId") Long columnId)
    {
        return success(alipayFundSumService.selectAlipayFundSumByColumnId(columnId));
    }

    /**
     * 新增支付宝资金汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:add')")
    @Log(title = "支付宝资金汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AlipayFundSum alipayFundSum)
    {
        return toAjax(alipayFundSumService.insertAlipayFundSum(alipayFundSum));
    }

    /**
     * 修改支付宝资金汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:edit')")
    @Log(title = "支付宝资金汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AlipayFundSum alipayFundSum)
    {
        return toAjax(alipayFundSumService.updateAlipayFundSum(alipayFundSum));
    }

    /**
     * 删除支付宝资金汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:alifundsum:remove')")
    @Log(title = "支付宝资金汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{columnIds}")
    public AjaxResult remove(@PathVariable Long[] columnIds)
    {
        return toAjax(alipayFundSumService.deleteAlipayFundSumByColumnIds(columnIds));
    }

}
