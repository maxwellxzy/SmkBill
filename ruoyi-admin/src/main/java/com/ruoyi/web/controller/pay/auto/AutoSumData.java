package com.ruoyi.web.controller.pay.auto;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.domain.PayTradeSum;
import com.ruoyi.pay.domain.PayTradeType;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.IAlipayTradeService;
import com.ruoyi.pay.service.IPayTradeSumService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.pay.service.IPayTradeTypeService;
import com.ruoyi.pay.service.IWechatTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("autoSum")
public class AutoSumData {
    @Autowired
    private IAlipayTradeService alipayTradeService;
    @Autowired
    private IPayTradeSumService payTradeSumService;
    @Autowired
    private IWechatTradeService wechatTradeService;
    @Autowired
    private IPayTradeTypeService payTradeTypeService;

    public boolean insertPaySum(String payType,String mchId,String date){
        PayTradeType payTradeType = new PayTradeType();
        List<PayTradeType> typeList = payTradeTypeService.selectPayTradeTypeList(payTradeType);
        for(PayTradeType payTradeType1 : typeList){
            switch (payType) {
                case "wechat":
                    insertWeSum(payTradeType1.getTradeType(),mchId,date);
                    break;
                case "alipay":
                    insertAliSum(payTradeType1.getTradeType(),mchId,date);
                    break;
                default:
                    AjaxResult.error("没有这种支付类型：" + payType);
                    return false;
            }
        }
        return true;
    }
/**
 * insert quickly
 * */
public boolean insertPaySumQuick(String payType,String mchId,String date){
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
     * select * from alipay_trade where complete_time = time
     * and mch_id = 'mchid' and mer_trade_no like 'type';
     **/
    public void insertAliSum(String tradeType, String mchId, String date) {

        AlipayTrade alipayTrade = new AlipayTrade();
        alipayTrade.setMerTradeNo(tradeType);
        alipayTrade.setMchId(mchId);
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            alipayTrade.setCompleteTime(date1);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<AlipayTrade> list = alipayTradeService.selectAlipayTradeList(alipayTrade);
        //找不到，则直接返回0
        if(list.isEmpty()){ return;}
        PayTradeSum payTradeSum = new PayTradeSum();
        //构造一个支付宝的统计类
        payTradeSum.setMchId(mchId);
        payTradeSum.setRecordTime(date1);
        payTradeSum.setTradeName(tradeType);
        //构造两个交易、退款的list，把交易和退款分开来
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
    /**
     * select * from wechat_trade where complete_time = time
     * and mch_id = 'mchid' and mer_trade_no like 'type';
     **/
    public void insertWeSum(String tradeType, String mchId, String date) {

        WechatTrade wechatTrade = new WechatTrade();
        wechatTrade.setMchId(mchId);
        wechatTrade.setMchTradeNo(tradeType);
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            wechatTrade.setTradeTime(date1);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<WechatTrade> list = wechatTradeService.selectWechatTradeList(wechatTrade);

        //找不到，则直接返回0
        if(list.isEmpty()){ return;}
        PayTradeSum payTradeSum = new PayTradeSum();
        //构造一个微信支付的统计类
        payTradeSum.setMchId(mchId);
        payTradeSum.setRecordTime(date1);
        // 根据交易类型分类
        payTradeSum.setTradeName(tradeType);
        //构造两个交易、退款的list，把交易和退款分开来
        payTradeSum.setTradePrice(list.stream().mapToDouble(WechatTrade::getTradePrice).sum());
        payTradeSum.setServiceFee(list.stream().mapToDouble(WechatTrade::getServiceFee).sum());
        payTradeSum.setRefundPrice(list.stream().mapToDouble(WechatTrade::getRefundPrice).sum());
        payTradeSum.setRealPrice(payTradeSum.getTradePrice() -  payTradeSum.getServiceFee() - payTradeSum.getRefundPrice());

        payTradeSumService.insertPayTradeSum(payTradeSum);
    }

}
