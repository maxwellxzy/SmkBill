package com.ruoyi.web.controller.pay.auto;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.domain.AlipayFundSum;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.IAlipayFundSumService;
import com.ruoyi.pay.service.IAlipayTradeService;
import com.ruoyi.pay.service.IWechatTradeService;
import com.ruoyi.pay.tools.ImportCsv;
import com.ruoyi.pay.domain.WechatFund;
import com.ruoyi.pay.service.IWechatFundService;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.pay.tools.SmkTools;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("importCsv")
public class AutoAddData {
    @Autowired
    private IWechatFundService wechatFundService;
    @Autowired
    private IWechatTradeService wechatTradeService;
    @Autowired
    private IAlipayFundSumService   alipayFundSumService;
    @Autowired
    private IAlipayTradeService alipayTradeService;
    private static final String PAY_PATH = SmkTools.ReadYamlFile("pay.path.download");
    public  AjaxResult add(String payType,String mchId)
    {

        switch (payType){
            case "wechat":
                String wechatTradePath= PAY_PATH + "wechat/" + mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_trade.csv";
                String wechatFundPath= PAY_PATH + "wechat/" + mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_fund.csv";
                List<WechatFund> wechatFundList = ImportCsv.importWechatFund(wechatFundPath);
                List<WechatTrade> wechatTradeList = ImportCsv.importWechatTrade(wechatTradePath);
                for (WechatFund wechatFund : wechatFundList) {
                    wechatFundService.insertWechatFund(wechatFund);
                }
                for(WechatTrade wechatTrade : wechatTradeList){
                    wechatTradeService.insertWechatTrade(wechatTrade);
                }
                break;
            case "alipay":
                String alipayTradePath= PAY_PATH + "alipay/" + mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_trade.csv";
                String alipayFundSumPath= PAY_PATH + "alipay/" + mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_sum.csv";
                List<AlipayTrade>  alipayTradeList = ImportCsv.importAlipayTrade(alipayTradePath);
                List<AlipayFundSum> alipayFundSumList = ImportCsv.importAlipayFundSum(alipayFundSumPath);

                for(AlipayTrade alipayTrade : alipayTradeList){
                    alipayTradeService.insertAlipayTrade(alipayTrade);
                }
                for(AlipayFundSum alipayFundSum : alipayFundSumList){
                    alipayFundSumService.insertAlipayFundSum(alipayFundSum);
                }
                break;
            default:
                return AjaxResult.error("错误的支付方式。只支持wechat和alipay");
        }
        return AjaxResult.success(payType + "账单导入成功。");
    }

}
