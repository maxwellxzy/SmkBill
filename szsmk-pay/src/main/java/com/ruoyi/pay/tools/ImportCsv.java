package com.ruoyi.pay.tools;

import com.alibaba.fastjson2.support.csv.CSVReader;
import com.csvreader.CsvReader;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.pay.domain.AlipayFundSum;
import com.ruoyi.pay.domain.AlipayTrade;
import com.ruoyi.pay.domain.WechatFund;
import com.ruoyi.pay.domain.WechatTrade;
import com.ruoyi.pay.service.IWechatFundService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组装csv文件到实体类：把下载的csv文件，组装成实体类
 * 给Auto的controller调用成为List
 * 
 * @author ruoyi
 */
public class ImportCsv
{
    public static List<WechatFund> importWechatFund(String csvPath) {
        List<WechatFund> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            reader.readLine();//首行跳过
            long num = Files.lines(Paths.get(csvPath)).count();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            num = num -3; //去掉第一行、最后两行
            for (int i=0;i<num;i++) {
                WechatFund fund = new WechatFund();
                String item[] = reader.readLine().split(","); //读取一行到item里
                fund.setMchId(csvPath.substring(csvPath.length() -28,csvPath.length() -18));
                fund.setRecordTime(sdf.parse(item[0].substring(1)));
                fund.setWechatTradeNo(item[1].substring(1));
                fund.setFundNo(item[2].substring(1));
                fund.setTradeName(item[3].substring(1));
                fund.setTradeType(item[4].substring(1));
                fund.setInOutType(item[5].substring(1));
                fund.setFundPrice(Double.parseDouble(item[6].substring(1)));
                fund.setAccountBalance(Double.parseDouble(item[7].substring(1)));
                fund.setFundUser(item[8].substring(1));
                fund.setRemark(item[9].substring(1));
                fund.setVoucherNo(item[10].substring(1));
                list.add(fund);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<WechatTrade> importWechatTrade(String csvPath)
    {
        List<WechatTrade> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            reader.readLine();//首行跳过
            long num = Files.lines(Paths.get(csvPath)).count();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            num = num -3; //去掉第一行、最后两行
            for (int i=0;i<num;i++) {
                WechatTrade fund = new WechatTrade();
                String item[] = reader.readLine().split(","); //读取一行到item里
                fund.setTradeTime(sdf.parse(item[0].substring(1)));
                fund.setMpId(item[1].substring(1));
                fund.setMchId(item[2].substring(1));
                fund.setVipMchId(item[3].substring(1));
                fund.setTerminalId(item[4].substring(1));
                fund.setWechatTradeNo(item[5].substring(1));
                fund.setMchTradeNo(item[6].substring(1));
                fund.setUserId(item[7].substring(1));
                fund.setTradeType(item[8].substring(1));
                fund.setTradeStatus(item[9].substring(1));
                fund.setPayBank(item[10].substring(1));
                fund.setCurrencyType(item[11].substring(1));
                fund.setTradePayable(Double.parseDouble(item[12].substring(1)));
                fund.setCouponPrice(Double.parseDouble(item[13].substring(1)));
                fund.setWechatRefundNo(item[14].substring(1));
                fund.setMchRefundNo(item[15].substring(1));
                fund.setRefundPrice(Double.parseDouble(item[16].substring(1)));
                fund.setCouponRefundPrice(Double.parseDouble(item[17].substring(1)));
                fund.setRefundType(item[18].substring(1));
                fund.setRefundStatus(item[19].substring(1));
                fund.setGoodsName(item[20].substring(1));
                fund.setMchDataPackage(item[21].substring(1));
                fund.setServiceFee(Double.parseDouble(item[22].substring(1)));
                fund.setServiceRate(Double.parseDouble(item[23].substring(1).replaceAll("%","")));
                fund.setTradePrice(Double.parseDouble(item[24].substring(1)));
                fund.setRequestRefund(Double.parseDouble(item[25].substring(1)));
                fund.setRateRemark(item[26].substring(1));
                list.add(fund);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<AlipayTrade> importAlipayTrade(String csvPath)
    {
        List<AlipayTrade> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            long num = Files.lines(Paths.get(csvPath)).count();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            num = num -9; //去掉前5行、最后4行
            for (int i=0;i<num;i++) {
                AlipayTrade fund = new AlipayTrade();
                String item[] = reader.readLine().split(","); //读取一行到item里
                fund.setMchId(csvPath.substring(csvPath.length() -35,csvPath.length() -19));
                fund.setAlipayTradeNo(item[0].trim());
                fund.setMerTradeNo(item[1].trim());
                fund.setTradeType(item[2].trim());
                fund.setGoodsName(item[3]);
                fund.setCreateTime(sdf.parse(item[4]));
                fund.setCompleteTime(sdf.parse(item[5]));
                fund.setRetailNo(item[6].trim());
                fund.setRetailName(item[7]);
                fund.setOperator(item[8]);
                fund.setTerminalNo(item[9]);
                fund.setUserAccount(item[10].trim());
                fund.setOrderPrice(Double.parseDouble(item[11]));
                fund.setRealPrice(Double.parseDouble(item[12]));
                fund.setAlipayRedpocket(Double.parseDouble(item[13]));
                fund.setJifenbao(Double.parseDouble(item[14]));
                fund.setAlipayCoupon(Double.parseDouble(item[15]));
                fund.setMerCoupon(Double.parseDouble(item[16]));
                fund.setCouponCompletePrice(Double.parseDouble(item[17]));
                fund.setCouponName(item[18].trim());
                fund.setMerRedpocketComsume(Double.parseDouble(item[19]));
                fund.setCardConsume(Double.parseDouble(item[20]));
                fund.setRefundNo(item[21].trim());
                fund.setServiceFee(Double.parseDouble(item[22]));
                fund.setProfit(Double.parseDouble(item[23]));
                fund.setRemark("");
                list.add(fund);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<AlipayFundSum> importAlipayFundSum(String csvPath)
    {
        List<AlipayFundSum> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            reader.readLine();//5行跳过
            long num = Files.lines(Paths.get(csvPath)).count();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            num = num -8; //去掉5行、最后3行
            for (int i=0;i<num;i++) {
                AlipayFundSum fund = new AlipayFundSum();
                String item[] = reader.readLine().split(","); //读取一行到item里
                fund.setMchId(csvPath.substring(csvPath.length() -33,csvPath.length() -17));
                fund.setFundDate(sdf.parse(SmkTools.getYesterday()));
                fund.setRetailNo(item[0].trim());
                fund.setRetailName(item[1].trim());
                fund.setTradeNoSum(Long.parseLong(item[2]));
                fund.setRefundNoSum(Long.parseLong(item[3]));
                fund.setTradePriceSum(Double.parseDouble(item[4]));
                fund.setMerRealSum(Double.parseDouble(item[5]));
                fund.setCardPriceSum(Double.parseDouble(item[6]));
                fund.setAlipayCouponSum(Double.parseDouble(item[7]));
                fund.setMerCouponSum(Double.parseDouble(item[8]));
                fund.setServiceFeeSum(Double.parseDouble(item[9]));
                fund.setProfitSum(Double.parseDouble(item[10]));
                fund.setRealIncomeSum(Double.parseDouble(item[11]));
                list.add(fund);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


}