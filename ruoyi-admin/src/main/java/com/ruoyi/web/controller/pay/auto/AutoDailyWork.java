package com.ruoyi.web.controller.pay.auto;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.domain.*;
import com.ruoyi.pay.service.*;
import com.ruoyi.pay.tools.AlipayMch;
import com.ruoyi.pay.tools.ImportCsv;
import com.ruoyi.pay.tools.SmkTools;
import com.ruoyi.pay.tools.WechatMch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("autoDaily")
public class AutoDailyWork {
    @Autowired
    private IPayMchIdService payMchIdService;
    @Autowired
    private IAlipayTradeService alipayTradeService;
    @Autowired
    private IPayTradeSumService payTradeSumService;
    @Autowired
    private IWechatTradeService wechatTradeService;
    @Autowired
    private IPayTradeTypeService payTradeTypeService;
    @Autowired
    private IWechatFundService wechatFundService;
    @Autowired
    private IAlipayFundSumService   alipayFundSumService;
    @Autowired
    private IGoodsNameService goodsNameService;


    /**
     * 每天定时任务：下载订单、导入数据库、解析数据
     * 此函数只能在定时任务执行
     * */
    /**方法一：无任何参数，自动下载前一天的账单，并下载并导入数据库中的所有
     * 商户号账单
     * */
    /**Get Yesterday's bill*/
    public  void autoImport(){
        autoImport(SmkTools.getYesterday());
    }
    /**
     * 方法二： 下载并导入指定日期的账单,商户号从PayMchId中取
     * */
    public void autoImport(String billDate){
        //获取PayMchId中的所有商户号及配置，根据这些配置进行设置

        PayMchId mchId = new PayMchId();
        mchId.setStatus("0"); //筛选出正常的商户号
         List<PayMchId> payMchIdList =  payMchIdService.selectPayMchIdList(mchId);
         for(PayMchId payMchId : payMchIdList){
             autoImport(payMchId.getMchType(),payMchId.getMchId(),billDate);
         }
    }

    /**
     * 方法三： 下载并导入指定日期、指定商户号的账单
     * */
    public  void autoImport(String mchType,String mchId,String billDate){
        if(importBill(mchType,mchId,billDate))
            AjaxResult.success();
        else
            AjaxResult.error();
          sumDataGoods(mchType,mchId,billDate);
//        sumData(mchType,mchId,billDate);
    }


/////////////////////////////////////////////////////////////////

    /**
     * autoImport方法的实现
     * */
    private  boolean importBill(String mchType,String mchId,String billDate){
        String billPath = SmkTools.ReadYamlFile("pay.path.download");
         if(!downloadBill(mchType,mchId,billDate,billPath)) return  false;
        return importCsv(mchType, mchId, billDate, billPath);

    }

    /**
     * 下载账单到指定路径
     * 账单类型、商户号、日期、下载路径
     * 返回。。。
     * */
    private boolean downloadBill(String mchType,String mchId,String billDate,String billPath){
        return switch (mchType) {
            case "alipay" -> downloadAlipayBill(mchId, billDate, billPath);
            case "wechat" -> downloadWechatBill(mchId, billDate, billPath);
            default -> {
                AjaxResult.error("Wrong mchType" + mchType + ": cannot find.");
                yield false;
            }
        };
    }

    private boolean downloadAlipayBill(String mchId,String billDate,String billPath){
        PayMchId payMchId = new PayMchId();
        payMchId.setMchType("alipay");
        payMchId.setMchId(mchId);
        payMchId.setStatus("0");
        List<PayMchId> payMchIdList =  payMchIdService.selectPayMchIdList(payMchId);
        if(payMchIdList.size() != 1) {
            System.out.println("Error,不是唯一的mchID 或找不到mchID");
            return false;
        }
        /***AlipayMch
         * (String appId, String appPrivateKeyPath, String alipayPublicCertPath,
         * String appPublicCertPath, String alipayRootCertPath,
         * String billDate,String savePath) {*/
        payMchId = payMchIdList.getFirst();

        AlipayMch alipayMch = new AlipayMch(mchId,
                payMchId.getAlipayappPrivatePath(),
                payMchId.getAlipayCertPath(),
                payMchId.getAlipayappPublicPath(),
                payMchId.getAlipayRootPath(),
                billDate,
                billPath);
        return alipayMch.downloadAlipayBill(billDate,billPath);
    }
    private boolean downloadWechatBill(String mchId,String billDate,String billPath){
        PayMchId payMchId = new PayMchId();
        payMchId.setMchType("wechat");
        payMchId.setMchId(mchId);
        payMchId.setStatus("0");
        List<PayMchId> payMchIdList =  payMchIdService.selectPayMchIdList(payMchId);
        if(payMchIdList.size() != 1) {
            System.out.println("Error,不是唯一的mchID 或找不到mchID");
            return false;
        }
        /***WechatMch(String merchantId,String privateKeyPath,
         *  String merchantSerialNumber, String apiV3Key,
         *  String billDate, String savePath) {*/
        payMchId = payMchIdList.getFirst();
        WechatMch wechatMch = new WechatMch(mchId,
/*                payMchId.getB1Key(), */
                payMchId.getWechatApiCert(),
                payMchId.getSnKey(),
                payMchId.getApiKey(),
                billDate,
                billPath);
         if(!wechatMch.downloadFundBill(billDate,billPath)) return false;
        return wechatMch.downloadTradeBill(billDate, billPath);
    }

    /**
     * 导入指定路径的csv文件到数据库
     * 数据库定义在application.yaml里面
     * 账单类型、商户号、日期、下载路径
     * 返回。。。
     * */
    public  boolean importCsv(String payType,String mchId,String billDate,String billPath)
    {

        switch (payType){
            case "wechat":
                String wechatTradePath= billPath +  mchId + "_" + billDate.replaceAll("-","")+ "_trade.csv";
                String wechatFundPath= billPath + mchId + "_" + billDate.replaceAll("-","")+ "_fund.csv";
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
                String alipayTradePath= billPath + mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_trade.csv";
                String alipayFundSumPath= billPath +  mchId + "_" + SmkTools.getYesterday().replaceAll("-","")+ "_sum.csv";
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
                AjaxResult.error("错误的支付方式。只支持wechat和alipay");
                return false;
        }
        AjaxResult.success(payType + "账单导入成功。");
        return true;
    }

    /**
     * insert quickly
     * sumData:根据订单号分类，进行分类。PayTradeType
     * */
    private boolean sumData(String payType,String mchId,String date){
        Date date1;
        List<PayTradeType> typeList = payTradeTypeService.selectPayTradeTypeList(new PayTradeType());
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        PayTradeSum payTradeSum = new PayTradeSum();
        switch (payType) {
            case "wechat":
                WechatTrade wechatTrade = new WechatTrade();
                wechatTrade.setMchId(mchId);
                wechatTrade.setTradeTime(date1);
                // 先把所有list都查出来
                List<WechatTrade> wechatTradeList = wechatTradeService.selectWechatTradeList(new WechatTrade());
                if(wechatTradeList.isEmpty()){return true;}
                payTradeSum.setMchId(mchId);
                payTradeSum.setRecordTime(date1);
                //根据每个交易类型，进行筛选
                for(PayTradeType payTradeType : typeList) {

                    List<WechatTrade> list = wechatTradeList.stream()
                            .filter(s->s.getMchTradeNo().substring(0,3).equals(payTradeType.getTradeType()))
                            .toList();
                    if(list.isEmpty()) continue;
                    //原来的减去筛选掉的：
                    wechatTradeList.removeAll(list);
                    payTradeSum.setTradeName(payTradeType.getTradeTypeName());
                    payTradeSum.setTradePrice(list.stream().mapToDouble(WechatTrade::getTradePrice).sum());
                    payTradeSum.setRefundPrice(list.stream().mapToDouble(WechatTrade::getRefundPrice).sum());
                    payTradeSum.setServiceFee(list.stream().mapToDouble(WechatTrade::getServiceFee).sum());
                    payTradeSum.setRealPrice(payTradeSum.getTradePrice() - payTradeSum.getRefundPrice() - payTradeSum.getServiceFee());
                    payTradeSumService.insertPayTradeSum(payTradeSum);
                }
                if(wechatTradeList.isEmpty()){return true;}
                //如果还有剩余，把剩余的都归为others其他
                payTradeSum.setTradeName("其他");
                payTradeSum.setTradePrice(wechatTradeList.stream().mapToDouble(WechatTrade::getTradePrice).sum());
                payTradeSum.setRefundPrice(wechatTradeList.stream().mapToDouble(WechatTrade::getRefundPrice).sum());
                payTradeSum.setServiceFee(wechatTradeList.stream().mapToDouble(WechatTrade::getServiceFee).sum());
                payTradeSum.setRealPrice(payTradeSum.getTradePrice() - payTradeSum.getRefundPrice() - payTradeSum.getServiceFee());
                payTradeSumService.insertPayTradeSum(payTradeSum);
                break;
            case "alipay":
                AlipayTrade alipayTrade = new AlipayTrade();
                alipayTrade.setMchId(mchId);
                alipayTrade.setCompleteTime(date1);
                // 先把所有list都查出来
                List<AlipayTrade> alipayTradeList = alipayTradeService.selectAlipayTradeList(new AlipayTrade());
                if(alipayTradeList.isEmpty()){return true;}
                payTradeSum.setMchId(mchId);
                payTradeSum.setRecordTime(date1);
                //根据每个交易类型，进行筛选
                for(PayTradeType payTradeType : typeList) {
                    List<AlipayTrade> list = alipayTradeList.stream()
                            .filter(s->s.getMerTradeNo()
                                    .substring(0,3)
                                    .equals(payTradeType.getTradeType())
                            )
                            .toList();
                    if(list.isEmpty()) continue;
                    //原来的减去筛选掉的：
                    alipayTradeList.removeAll(list);
                    payTradeSum.setTradeName(payTradeType.getTradeTypeName());
                    ///
                    List<AlipayTrade> listTrade = list.stream().filter(s->"交易".equals(s.getTradeType())).toList();
                    List<AlipayTrade> listRefund = list.stream().filter(s->"退款".equals(s.getTradeType())).toList();
                    //交易累加：去除退款的；
                    payTradeSum.setTradePrice(listTrade.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                    payTradeSum.setServiceFee(list.stream().mapToDouble(AlipayTrade::getServiceFee).sum());
                    //退款是负的；
                    payTradeSum.setRefundPrice(listRefund.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                    payTradeSum.setRealPrice(list.stream().mapToDouble(AlipayTrade::getRealPrice).sum() + payTradeSum.getServiceFee());
                    payTradeSumService.insertPayTradeSum(payTradeSum);

                }
                if(alipayTradeList.isEmpty()){
                    return true;
                }
                //如果还有剩余，把剩余的都归为others其他
                payTradeSum.setTradeName("其他");
                List<AlipayTrade> listTrade = alipayTradeList.stream().filter(s->"交易".equals(s.getTradeType())).toList();
                List<AlipayTrade> listRefund = alipayTradeList.stream().filter(s->"退款".equals(s.getTradeType())).toList();
                //交易累加：去除退款的；
                payTradeSum.setTradePrice(listTrade.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                payTradeSum.setServiceFee(alipayTradeList.stream().mapToDouble(AlipayTrade::getServiceFee).sum());
                //退款是负的；
                payTradeSum.setRefundPrice(listRefund.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                payTradeSum.setRealPrice(alipayTradeList.stream().mapToDouble(AlipayTrade::getRealPrice).sum() + payTradeSum.getServiceFee());
                payTradeSumService.insertPayTradeSum(payTradeSum);
                break;
            default:
                AjaxResult.error("没有这种支付类型：" + payType);
                return false;
        }

        return true;
    }


    /**
     * insert quickly
     * sumDataGoods:根据商品名称进行分类，GoodsName
     * */
    public boolean sumDataGoods(String payType,String mchId,String date){
        Date date1;
        GoodsName goodsName = new GoodsName();
        goodsName.setStatus("0");
        List<GoodsName> goodsNameList = goodsNameService.selectGoodsNameList(goodsName);
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        PayTradeSum payTradeSum = new PayTradeSum();
        switch (payType) {
            case "wechat":
                WechatTrade wechatTrade = new WechatTrade();
                wechatTrade.setMchId(mchId);
                wechatTrade.setTradeTime(date1);
                // 先把所有list都查出来
                List<WechatTrade> wechatTradeList = wechatTradeService.selectWechatTradeList(new WechatTrade());
                if(wechatTradeList.isEmpty()){return true;}
                payTradeSum.setMchId(mchId);
                payTradeSum.setRecordTime(date1);
                //根据每个交易类型，进行筛选
                for(GoodsName goodsName1 : goodsNameList) {

                    List<WechatTrade> list = wechatTradeList.stream()
                            .filter(s->s.getGoodsName().trim().contains(goodsName1.getGoodsName().trim()))
                            .toList();
                    if(list.isEmpty()) continue;
                    //原来的减去筛选掉的：
                    wechatTradeList.removeAll(list);
                    payTradeSum.setTradeName(goodsName1.getGoodsName());
                    payTradeSum.setTradePrice(list.stream().mapToDouble(WechatTrade::getTradePrice).sum());
                    payTradeSum.setRefundPrice(list.stream().mapToDouble(WechatTrade::getRefundPrice).sum());
                    payTradeSum.setServiceFee(list.stream().mapToDouble(WechatTrade::getServiceFee).sum());
                    payTradeSum.setRealPrice(payTradeSum.getTradePrice() - payTradeSum.getRefundPrice() - payTradeSum.getServiceFee());
                    payTradeSumService.insertPayTradeSum(payTradeSum);
                }
                if(wechatTradeList.isEmpty()){return true;}
                //如果还有剩余，把剩余的都归为others其他
                payTradeSum.setTradeName("其他");
                payTradeSum.setTradePrice(wechatTradeList.stream().mapToDouble(WechatTrade::getTradePrice).sum());
                payTradeSum.setRefundPrice(wechatTradeList.stream().mapToDouble(WechatTrade::getRefundPrice).sum());
                payTradeSum.setServiceFee(wechatTradeList.stream().mapToDouble(WechatTrade::getServiceFee).sum());
                payTradeSum.setRealPrice(payTradeSum.getTradePrice() - payTradeSum.getRefundPrice() - payTradeSum.getServiceFee());
                payTradeSumService.insertPayTradeSum(payTradeSum);
                break;
            case "alipay":
                AlipayTrade alipayTrade = new AlipayTrade();
                alipayTrade.setMchId(mchId);
                alipayTrade.setCompleteTime(date1);
                // 先把所有list都查出来
                List<AlipayTrade> alipayTradeList = alipayTradeService.selectAlipayTradeList(new AlipayTrade());
                if(alipayTradeList.isEmpty()){return true;}
                payTradeSum.setMchId(mchId);
                payTradeSum.setRecordTime(date1);
                //根据每个交易类型，进行筛选
                for(GoodsName  goodsName1 : goodsNameList) {
                    List<AlipayTrade> list = alipayTradeList.stream()
                            .filter(s->s.getGoodsName()
                                    .trim()
                                    .contains(goodsName1.getGoodsName().trim())
                            )
                            .toList();
                    if(list.isEmpty()) continue;
                    //原来的减去筛选掉的：
                    alipayTradeList.removeAll(list);
                    payTradeSum.setTradeName(goodsName1.getGoodsName().trim());
                    ///
                    List<AlipayTrade> listTrade = list.stream().filter(s->"交易".equals(s.getTradeType())).toList();
                    List<AlipayTrade> listRefund = list.stream().filter(s->"退款".equals(s.getTradeType())).toList();
                    //交易累加：去除退款的；
                    payTradeSum.setTradePrice(listTrade.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                    payTradeSum.setServiceFee(list.stream().mapToDouble(AlipayTrade::getServiceFee).sum());
                    //退款是负的；
                    payTradeSum.setRefundPrice(listRefund.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                    payTradeSum.setRealPrice(list.stream().mapToDouble(AlipayTrade::getRealPrice).sum() + payTradeSum.getServiceFee());
                    payTradeSumService.insertPayTradeSum(payTradeSum);

                }
                if(alipayTradeList.isEmpty()){
                    return true;
                }
                //如果还有剩余，把剩余的都归为others其他
                payTradeSum.setTradeName("其他");
                List<AlipayTrade> listTrade = alipayTradeList.stream().filter(s->"交易".equals(s.getTradeType())).toList();
                List<AlipayTrade> listRefund = alipayTradeList.stream().filter(s->"退款".equals(s.getTradeType())).toList();
                //交易累加：去除退款的；
                payTradeSum.setTradePrice(listTrade.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                payTradeSum.setServiceFee(alipayTradeList.stream().mapToDouble(AlipayTrade::getServiceFee).sum());
                //退款是负的；
                payTradeSum.setRefundPrice(listRefund.stream().mapToDouble(AlipayTrade::getRealPrice).sum());
                payTradeSum.setRealPrice(alipayTradeList.stream().mapToDouble(AlipayTrade::getRealPrice).sum() + payTradeSum.getServiceFee());
                payTradeSumService.insertPayTradeSum(payTradeSum);
                break;
            default:
                AjaxResult.error("没有这种支付类型：" + payType);
                return false;
        }

        return true;
    }
}