package com.ruoyi.pay.tools;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.billdownload.BillDownloadServiceExtension;
import com.wechat.pay.java.service.billdownload.DigestBillEntity;
import com.wechat.pay.java.service.billdownload.model.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class WechatMch {
    /** 商户号 */
    private   String merchantId;
    /** 商户API私钥路径 */
    private String privateKeyPath;
    /** 商户证书序列号 */
    private String merchantSerialNumber;
    /** 商户APIV3密钥 */
    private String apiV3Key;
    /**订单日期*/
    private String billDate;

    /**订单路径*/
    private String savePath;

    public WechatMch(String merchantId, String privateKeyPath, String merchantSerialNumber, String apiV3Key, String billDate, String savePath) {
        this.merchantId = merchantId;
        this.privateKeyPath = privateKeyPath;
        this.merchantSerialNumber = merchantSerialNumber;
        this.apiV3Key = apiV3Key;
        this.billDate = billDate;
        this.savePath = savePath + "wechat/";
    }
    public  WechatMch(String merchantId, String certPath,String downloadPath){
        this.merchantId = merchantId;
        this.privateKeyPath = certPath + "wechat/" +  "apiclient_key_" + merchantId + ".pem";
        this.merchantSerialNumber = getAccessKey(merchantId,certPath + "wechat/"  + "sn.json");
        this.apiV3Key = getAccessKey(merchantId,certPath + "wechat/" +  "api.json");
        this.billDate = getYesterday();
        this.savePath = downloadPath + "wechat/";
    }
    private String getYesterday(){
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    /**
     * 下载资金账单
     * */
    public boolean downloadFundBill(){
        AccountType accountType = AccountType.BASIC;
        return downloadFundBill(accountType);
    }
    public boolean downloadFundBill(AccountType accountType){
        GetFundFlowBillRequest request = new GetFundFlowBillRequest();
        request.setBillDate(this.billDate);
        request.setAccountType(accountType);
        request.setTarType(TarType.GZIP);
        BillDownloadServiceExtension billDownloadServiceExtension = configBill();
        DigestBillEntity bill = billDownloadServiceExtension.getFundFlowBill(request);
        String filePath = this.savePath + this.merchantId + "_" +this.billDate.replaceAll("-","") + "_fund.csv";
        try(InputStream inputStream = bill.getInputStream()){
            // 使用有缓存的 BufferedOutputStream
            try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
                // 处理大文件时，常用的缓冲区大小为 8192 或 16384。
                // 不过，最佳缓冲区大小可能取决于具体的硬件和系统配置。
                byte[] buffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,bytesRead);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        // 验证数据流中已读取数据的摘要
        if(bill.verifyHash()){
            // 账单是完整准确，可以开启后续操作，例如完成每日对账。
            return true;
        }else {
            // 账单不完整或者被篡改，应清理之前保存的文件
            try{
                Path path = Paths.get(filePath);
                Files.delete(path);
            } catch(IOException e){
                System.out.println("Error!");
            }
        }
        return false;
    }

    public boolean downloadFundBill(String billDate,String savePath){
        GetFundFlowBillRequest request = new GetFundFlowBillRequest();
        request.setBillDate(billDate);
        request.setAccountType(AccountType.ALL);
        request.setTarType(TarType.GZIP);
        BillDownloadServiceExtension billDownloadServiceExtension = configBill();
        DigestBillEntity bill = billDownloadServiceExtension.getFundFlowBill(request);
        String filePath = savePath + this.merchantId + "_" + billDate.replaceAll("-","") + "_fund.csv";
        try(InputStream inputStream = bill.getInputStream()){
            // 使用有缓存的 BufferedOutputStream
            try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
                // 处理大文件时，常用的缓冲区大小为 8192 或 16384。
                // 不过，最佳缓冲区大小可能取决于具体的硬件和系统配置。
                byte[] buffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,bytesRead);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        // 验证数据流中已读取数据的摘要
        if(bill.verifyHash()){
            // 账单是完整准确，可以开启后续操作，例如完成每日对账。
            return true;
        }else {
            // 账单不完整或者被篡改，应清理之前保存的文件
            try{
                Path path = Paths.get(filePath);
                Files.delete(path);
            } catch(IOException e){
                System.out.println("Error!");
            }
        }
        return false;
    }
    /**
     * 交易订单下载
     * */
    public boolean downloadTradeBill(){
        BillType billType = BillType.ALL;
        return downloadTradeBill(billType);
    }
    public boolean downloadTradeBill(BillType billType){
        GetTradeBillRequest request = new GetTradeBillRequest();
        request.setBillDate(this.billDate);
        request.setBillType(billType);
        request.setTarType(TarType.GZIP);
        BillDownloadServiceExtension billDownloadServiceExtension = configBill();
        DigestBillEntity bill = billDownloadServiceExtension.getTradeBill(request);
        String filePath = this.savePath + this.merchantId + "_" + this.billDate.replaceAll("-","") + "_trade.csv";
        try(InputStream inputStream = bill.getInputStream()){
            // 使用有缓存的 BufferedOutputStream
            try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
                // 处理大文件时，常用的缓冲区大小为 8192 或 16384。
                // 不过，最佳缓冲区大小可能取决于具体的硬件和系统配置。
                byte[] buffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,bytesRead);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        // 验证数据流中已读取数据的摘要
        if(bill.verifyHash()){
            // 账单是完整准确，可以开启后续操作，例如完成每日对账。
            return true;
        }else {
            // 账单不完整或者被篡改，应清理之前保存的文件
            try{
                Path path = Paths.get(filePath);
                Files.delete(path);
            } catch(IOException e){
                System.out.println("Error!");
            }
        }
        return false;
    }
    public boolean downloadTradeBill(String billDate,String savePath){
        GetTradeBillRequest request = new GetTradeBillRequest();
        request.setBillDate(billDate);
        request.setBillType(BillType.ALL);
        request.setTarType(TarType.GZIP);
        BillDownloadServiceExtension billDownloadServiceExtension = configBill();
        DigestBillEntity bill = billDownloadServiceExtension.getTradeBill(request);
        String filePath = savePath + this.merchantId + "_" + billDate.replaceAll("-","") + "_trade.csv";
        try(InputStream inputStream = bill.getInputStream()){
            // 使用有缓存的 BufferedOutputStream
            try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
                // 处理大文件时，常用的缓冲区大小为 8192 或 16384。
                // 不过，最佳缓冲区大小可能取决于具体的硬件和系统配置。
                byte[] buffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,bytesRead);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        // 验证数据流中已读取数据的摘要
        if(bill.verifyHash()){
            // 账单是完整准确，可以开启后续操作，例如完成每日对账。
            return true;
        }else {
            // 账单不完整或者被篡改，应清理之前保存的文件
            try{
                Path path = Paths.get(filePath);
                Files.delete(path);
            } catch(IOException e){
                System.out.println("Error!");
            }
        }
        return false;
    }
    /**
     * 配置函数，内部。
     * */
    private BillDownloadServiceExtension configBill(){
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(this.merchantId)
                        // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
                        .privateKeyFromPath(this.privateKeyPath)
                        .merchantSerialNumber(this.merchantSerialNumber)
                        .apiV3Key(this.apiV3Key)
                        .build();
        return new BillDownloadServiceExtension.Builder().config(config).build();
    }

    private static String getAccessKey(String key,String certPath){

        try {
            Path path = Paths.get(certPath);
            String content = Files.readString(path);
            JSONObject jsonObject = JSON.parseObject(content);
            return jsonObject.getString(key);
        }
        catch (Exception ignored){
            AjaxResult.error("Cannot open file!");
        }
        return null;
    }
}
