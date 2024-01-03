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
import com.ruoyi.pay.domain.PayMchId;
import com.ruoyi.pay.service.IPayMchIdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户号管理Controller
 * 
 * @author xuzy
 * @date 2023-12-30
 */
@RestController
@RequestMapping("/pay/paymchid")
public class PayMchIdController extends BaseController
{
    @Autowired
    private IPayMchIdService payMchIdService;

    /**
     * 查询商户号管理列表
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayMchId payMchId)
    {
        startPage();
        List<PayMchId> list = payMchIdService.selectPayMchIdList(payMchId);
        return getDataTable(list);
    }

    /**
     * 导出商户号管理列表
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:export')")
    @Log(title = "商户号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayMchId payMchId)
    {
        List<PayMchId> list = payMchIdService.selectPayMchIdList(payMchId);
        ExcelUtil<PayMchId> util = new ExcelUtil<PayMchId>(PayMchId.class);
        util.exportExcel(response, list, "商户号管理数据");
    }

    /**
     * 获取商户号管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payMchIdService.selectPayMchIdById(id));
    }

    /**
     * 新增商户号管理
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:add')")
    @Log(title = "商户号管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayMchId payMchId)
    {
        return toAjax(payMchIdService.insertPayMchId(payMchId));
    }

    /**
     * 修改商户号管理
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:edit')")
    @Log(title = "商户号管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayMchId payMchId)
    {
        return toAjax(payMchIdService.updatePayMchId(payMchId));
    }

    /**
     * 删除商户号管理
     */
    @PreAuthorize("@ss.hasPermi('pay:paymchid:remove')")
    @Log(title = "商户号管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payMchIdService.deletePayMchIdByIds(ids));
    }
}
