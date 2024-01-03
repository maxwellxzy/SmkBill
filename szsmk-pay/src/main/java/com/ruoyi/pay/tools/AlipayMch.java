package com.ruoyi.pay.tools;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayDataDataserviceBillDownloadurlQueryResponse;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;
@Data
public class AlipayMch {
    /** 商户号 */
    private   String appId;
    /** 商户应用私钥路径 */
    private String appPrivateKeyPath;
    private String appPrivateKey;
    /** 支付宝公钥证书路径 */
    private String alipayPublicCertPath;
    /** 商户公钥证书路径*/
    private String appPublicCertPath;
    /** 支付宝根证书路径 */
    private String alipayRootCertPath;
    /**订单日期*/
    private String billDate;

    private String protocol;
    private String gatewayHost;
    private  String signType;
    /**订单路径*/
    private String savePath;
    private String notifyUrl;

    public Config getAlipayConfig(){
        Config config = new Config();
        config.protocol = this.protocol;
        config.gatewayHost = this.gatewayHost;
        config.signType = this.signType;
        config.notifyUrl = this.notifyUrl;
        config.appId = this.appId;
        config.alipayCertPath = this.alipayPublicCertPath;
        config.alipayRootCertPath = this.alipayRootCertPath;
        config.merchantCertPath = this.appPublicCertPath;
        config.merchantPrivateKey = this.appPrivateKey;
        return config;
    }
    /**
     * 自定义支付宝初始化,所有路径都是全路径
     * */
    public AlipayMch(String appId, String appPrivateKeyPath, String alipayPublicCertPath, String appPublicCertPath, String alipayRootCertPath, String billDate,String savePath) {
        this.protocol = "https";
        this.gatewayHost = "openapi.alipay.com";
        this.signType = "RSA2";
        this.notifyUrl = "https://www.szsmk.com/alipay/callback";
        this.appId = appId;
        this.alipayPublicCertPath = alipayPublicCertPath;
        this.alipayRootCertPath = alipayRootCertPath;
        this.appPublicCertPath = appPublicCertPath;
        this.savePath = savePath;
        this.billDate = billDate;
        this.appPrivateKeyPath = appPrivateKeyPath;
        this.appPrivateKey = getPrivateKey(appPrivateKeyPath);
    }
    /**
     * 默认获取前一天的订单
     * 设置下载目录downloadPath，以/结尾;
     * 设置cert目录，以/结尾；cert目录下的alipay是放置alipay密钥的地方
     * cert/alipay目录下面文件格式：
     * 支付宝公钥证书：alipayPublicKey: alpayCertPublicKey_RSA2_appid.crt
     * 支付宝跟证书：alipayRootCert_appid.crt
     * 应用公钥证书：appCertPublicKey_appid.crt
     * 应用私钥文件：appPrivate_appid.txt
     * */
    public AlipayMch(String appId, String certPath, String downloadPath){
        this.protocol = "https";
        this.gatewayHost = "openapi.alipay.com";
        this.signType = "RSA2";
        this.notifyUrl = "https://www.szsmk.com/alipay/callback";
        this.appId = appId;
        this.alipayPublicCertPath = certPath + "alipay/alipayCertPublicKey_RSA2_" + appId + ".crt";
        this.alipayRootCertPath = certPath +"alipay/alipayRootCert_" + appId + ".crt";
        this.appPublicCertPath = certPath + "alipay/appCertPublicKey_" + appId + ".crt";
        this.savePath = downloadPath;
        this.billDate = getYesterday();
        this.appPrivateKeyPath = certPath + "alipay/appPrivate_" + appId + ".txt";
        this.appPrivateKey = getPrivateKey(appPrivateKeyPath);
    }
    public AlipayMch(String appId, String billDate,String certPath, String downloadPath){
        this.protocol = "https";
        this.gatewayHost = "openapi.alipay.com";
        this.signType = "RSA2";
        this.notifyUrl = "https://www.szsmk.com/alipay/callback";
        this.appId = appId;
        this.alipayPublicCertPath = certPath + "alipay/alipayCertPublicKey_RSA2_" + appId + ".crt";
        this.alipayRootCertPath = certPath +"alipay/alipayRootCert_" + appId + ".crt";
        this.appPublicCertPath = certPath + "alipay/appCertPublicKey_" + appId + ".crt";
        this.savePath = downloadPath;
        this.billDate = billDate;
        this.appPrivateKeyPath = certPath + "alipay/appPrivate_" + appId + ".txt";
        this.appPrivateKey = getPrivateKey(appPrivateKeyPath);
    }
    private String getYesterday(){
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    private static String getPrivateKey(String path){
        String content = null;
        try {
            InputStream stream = new FileInputStream(path);
            // Read all the bytes from the input stream and store them in a byte array
            byte[] bytes = stream.readAllBytes();
            // Close the input stream
            stream.close();
            // Create a new String object from the byte array, using UTF-8 encoding
           content = new String(bytes, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return content;
    }
    public boolean downloadAlipayBill(String billDate,String filePath) {

        Factory.setOptions(getAlipayConfig());
        try {
            AlipayDataDataserviceBillDownloadurlQueryResponse response
                    = Factory.Payment.Common().downloadBill("trade", billDate);
            if (!response.code.equals("10000")) {
                System.out.println("Error!!" + response.code + response.msg);
                return false;
            }
            System.out.println(response.billDownloadUrl);
            String fileName = getDownloadFileName(response.billDownloadUrl,"downloadFileName");
            if(!filePath.endsWith("/")) filePath += "/"; //自动在路径后面加斜杠
            String zipFullPath =  filePath  + fileName;

            String tmpPath = zipFullPath.substring(0,zipFullPath.length() - 8);
            String dstPathSum = tmpPath + "_业务明细(汇总).csv";
            String dstPathTrade =tmpPath + "_业务明细.csv";
            /*
             * 通过调用支付宝接口返回的url下载zip文件
             */
            boolean down_success = downLoadZip(response.billDownloadUrl,zipFullPath);

            if(down_success) {
                unZip(zipFullPath, filePath);
                // 转换解压缩下来的文件为UTF8格式
                SmkTools.GBKToUTF8(dstPathTrade, filePath + this.appId + "_" + billDate.replaceAll("-","") + "_trade.csv");
                SmkTools.GBKToUTF8(dstPathSum, filePath + this.appId + "_" + billDate.replaceAll("-","")  + "_sum.csv");
                //删除解压缩出来的原始csv文件和zip文件
                File tradeFile = new File(dstPathTrade);
                File sumFile = new File(dstPathSum);
                File zipFile = new File(zipFullPath);
                tradeFile.delete();
                sumFile.delete();
                zipFile.delete();
            }
        }catch (Exception e) {
            System.out.println("Error to download Bill.");
            return false;
        }
        return true;
    }
    public boolean downloadAlipayBill()  {
        Factory.setOptions(getAlipayConfig());
        try {
            AlipayDataDataserviceBillDownloadurlQueryResponse response
                    = Factory.Payment.Common().downloadBill("trade", this.billDate);
            if (!response.code.equals("10000")) {
                System.out.println("Error!!" + response.code + response.msg);
                return false;
            }

            System.out.println(response.billDownloadUrl);
            /**
            MatcherAssert.assertThat(response.code, CoreMatchers.is("10000"));
            MatcherAssert.assertThat(response.msg, CoreMatchers.is("Success"));
            MatcherAssert.assertThat(response.subCode, CoreMatchers.is(CoreMatchers.nullValue()));
            MatcherAssert.assertThat(response.subMsg, CoreMatchers.is(CoreMatchers.nullValue()));
            MatcherAssert.assertThat(response.httpBody, CoreMatchers.not(CoreMatchers.nullValue()));
            MatcherAssert.assertThat(response.billDownloadUrl.startsWith("http://dwbillcenter.alipay.com/"), CoreMatchers.is(true));
*/
            String fileName = getDownloadFileName(response.billDownloadUrl,"downloadFileName");
            String dstPath = this.savePath + "alipay/" + fileName;

            dstPath = dstPath.substring(0,dstPath.length() - 8);
            String dstPathSum = dstPath + "_业务明细(汇总).csv";
            String dstPathTrade =dstPath + "_业务明细.csv";
            String fileDate = fileName.substring(21,29);
            /*
             * 通过调用支付宝接口返回的url下载zip文件
             */
            boolean down_success = downLoadZip(response.billDownloadUrl,this.savePath + fileName);

            if(down_success)
            {
                unZip(this.savePath + fileName,this.savePath + "alipay/");
                // 转换解压缩下来的文件为UTF8格式
                SmkTools.GBKToUTF8(dstPathTrade,this.savePath + "alipay/" + this.appId + "_" +  fileDate +  "_trade.csv");
                SmkTools.GBKToUTF8(dstPathSum,this.savePath + "alipay/" + this.appId + "_" +  fileDate +  "_sum.csv");
                //删除解压缩出来的原始csv文件和zip文件
                File tradeFile = new File(dstPathTrade);
                File sumFile = new File(dstPathSum);
                File zipFile = new File(this.savePath + fileName);
                tradeFile.delete();
                sumFile.delete();
                zipFile.delete();
                }

        }catch (Exception e) {
            System.out.println("Error to download Bill.");
            return false;
        }
        return true;
    }

    /**
     * 解压缩并放到zipped文件夹
     * **/
    public static void unZip(String zipFilePath, String destDir)
            throws Exception {

        ZipFile zipFile = new ZipFile(zipFilePath, "GBK");
        Enumeration<?> emu = zipFile.getEntries();
        BufferedInputStream bis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        File file, parentFile;
        ZipEntry entry;
        byte[] cache = new byte[1024];
        while (emu.hasMoreElements()) {
            entry = (ZipEntry) emu.nextElement();
            if (entry.isDirectory()) {
                new File(destDir + entry.getName()).mkdirs();
                continue;
            }
            bis = new BufferedInputStream(zipFile.getInputStream(entry));
            file = new File(destDir + entry.getName());
            parentFile = file.getParentFile();
            if (parentFile != null && (!parentFile.exists())) {
                parentFile.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos, 1024);
            int nRead = 0;
            while ((nRead = bis.read(cache, 0, 1024)) != -1) {
                fos.write(cache, 0, nRead);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        }
        zipFile.close();
    }


    /**
     * 读取zip文件，不解压缩直接解析，支持文件名中文，解决内容乱码
     * @param file
     * @return   读取zip文件，返回字符串
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private String readZipToString(File file) throws Exception {
        String connet = "";
        try {

            //获得输入流，文件为zip格式，
            //支付宝提供
            //20886126836996110156_20160906.csv.zip内包含
            //20886126836996110156_20160906_业务明细.csv
            //20886126836996110156_20160906_业务明细(汇总).csv
            ZipInputStream in = new ZipInputStream(new FileInputStream(file));
            //不解压直接读取,加上gbk解决乱码问题
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"gbk"));
            ZipEntry zipFile = null;
            //返回的字符串---每个文件内容相加
            BufferedWriter bw = null;
            //循环读取zip中的cvs文件，无法使用jdk自带，因为文件名中有中文
            while (bw != null) {
                if (zipFile.isDirectory()){
                    //如果是目录，不处理
                }
                String file_connet = "";
                //获得cvs名字
                String fileName = zipFile.getName();
                System.out.println("-----"+fileName);
                //检测文件是否存在
                if (fileName != null && fileName.indexOf(".") != -1) {
                    String line;
                    /*
                     * 1.每一行用 | 隔开
                     * 2.每一个文件用 ; 隔开
                     */
                    //  bw = new BufferedWriter(new FileWriter("d:\\test\\test.txt")); //测试读取内容
                    while ((line = br.readLine()) != null) {

                        file_connet = file_connet + "|" + line;
                        // System.out.println(line);

                    }
                }

                connet = connet + file_connet + ";";
            }
            //  bw.write(connet);
            //关闭流
            //  bw.close();
            br.close();
            in.close();


        } catch (Exception e) {
            System.out.println("zip文件读取失敗" + e);

            return "false";
        }

        return connet;
    }


    /**
     * 通过支付宝查询对账单接口返回的url,下载zip文件
     * @param alipay_url
     * @param down_url
     * @return
     */
    private boolean downLoadZip(String alipay_url,String down_url) {
        boolean down_success = false;
        int bytesum = 0;
        int byteread = 0;
        InputStream inStream = null;
        FileOutputStream fs = null;

        try {

            URL url = new URL(alipay_url);
            URLConnection conn = url.openConnection();
            inStream = conn.getInputStream();

            //解压文件是否已存在
            File entryFile = new File(down_url);
            if (entryFile.exists()) {
                //删除已存在的目标文件
                entryFile.delete();
            }

            fs = new FileOutputStream(down_url);

            byte[] buffer = new byte[4028];

            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            down_success = true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                inStream = null;
            }

            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (IOException e) {
                fs = null;
            }
        }
        return down_success;
    }

    private String getDownloadFileName(String url,String key) throws URISyntaxException {

        URI uri = new URI(url);
        // Get the query part of the URI
        String query = uri.getQuery();
        // Split the query by "&" to get the individual parameters
        String[] params = query.split("&");
        // Create a map to store the parameters as key-value pairs
        Map<String, String> map = new HashMap<>();
        // Loop through the parameters and add them to the map
        for (String param : params) {
            // Split the parameter by "=" to get the key and value
            String[] kv = param.split("=");
            // Put the key and value in the map
            map.put(kv[0], kv[1]);
        }
        return map.get(key);
        // Print the result

    }
}
