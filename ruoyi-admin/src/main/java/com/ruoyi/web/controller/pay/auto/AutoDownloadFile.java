package com.ruoyi.web.controller.pay.auto;

import com.ruoyi.pay.tools.AlipayMch;
import com.ruoyi.pay.tools.SmkTools;
import com.ruoyi.pay.tools.WechatMch;
import org.springframework.stereotype.Component;

/**
 * 定时任务：每天下载微信和支付宝的订单
 * 
 * @author xuzy
 */

@Component("dwFile")

public class AutoDownloadFile
{

    private static final String downloadPath = SmkTools.ReadYamlFile("pay.path.download");

    private static final String certPath = SmkTools.ReadYamlFile("pay.path.cert");


    public static void downloadWechatFund(String appid)
    {
        WechatMch wechatMch = new WechatMch(appid,certPath,downloadPath);
        wechatMch.downloadFundBill();
    }
    public static void downloadWechatTrade(String appid)
    {
        WechatMch wechatMch = new WechatMch(appid,certPath,downloadPath);
        wechatMch.downloadTradeBill();
    }
    public static void downloadAlipayZip(String appid)
    {
        AlipayMch alipayMch = new AlipayMch(appid,certPath,downloadPath);
        alipayMch.downloadAlipayBill();
    }

}