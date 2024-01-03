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
import com.ruoyi.pay.domain.PayTradeSum;
import com.ruoyi.pay.service.IPayTradeSumService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 每日汇总Controller
 * 
 * @author xuzy
 * @date 2023-12-27
 */
@RestController
@RequestMapping("/pay/sum")
public class PayTradeSumController extends BaseController
{
    @Autowired
    private IPayTradeSumService payTradeSumService;

    /**
     * 查询每日汇总列表
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTradeSum payTradeSum)
    {
        startPage();
        List<PayTradeSum> list = payTradeSumService.selectPayTradeSumList(payTradeSum);
        return getDataTable(list);
    }

    /**
     * 导出每日汇总列表
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:export')")
    @Log(title = "每日汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayTradeSum payTradeSum)
    {
        List<PayTradeSum> list = payTradeSumService.selectPayTradeSumList(payTradeSum);
        ExcelUtil<PayTradeSum> util = new ExcelUtil<PayTradeSum>(PayTradeSum.class);
        util.exportExcel(response, list, "每日汇总数据");
    }

    /**
     * 获取每日汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payTradeSumService.selectPayTradeSumById(id));
    }

    /**
     * 新增每日汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:add')")
    @Log(title = "每日汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayTradeSum payTradeSum)
    {
        return toAjax(payTradeSumService.insertPayTradeSum(payTradeSum));
    }

    /**
     * 修改每日汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:edit')")
    @Log(title = "每日汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayTradeSum payTradeSum)
    {
        return toAjax(payTradeSumService.updatePayTradeSum(payTradeSum));
    }

    /**
     * 删除每日汇总
     */
    @PreAuthorize("@ss.hasPermi('pay:sum:remove')")
    @Log(title = "每日汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payTradeSumService.deletePayTradeSumByIds(ids));
    }
}
