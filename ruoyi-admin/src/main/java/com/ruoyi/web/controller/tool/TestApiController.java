package com.ruoyi.web.controller.tool;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.domain.PayTradeSum;
import com.ruoyi.pay.domain.PayTradeType;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.web.controller.pay.auto.AutoSumData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApiController {

    private IAlipayFundSumService alipayFundSumService;
    @Autowired
    private IAlipayTradeService alipayTradeService;
    private IWechatFundService wechatFundService;
    @Autowired
    private IWechatTradeService wechatTradeService;
    @Autowired
    private IPayTradeTypeService payTradeTypeService;
    @GetMapping("/hello")
    public AjaxResult add(){
            PayTradeType payTradeType = new PayTradeType();
            List<PayTradeType> list = payTradeTypeService.selectPayTradeTypeList(payTradeType);
            return AjaxResult.success();
        }
 //

}
