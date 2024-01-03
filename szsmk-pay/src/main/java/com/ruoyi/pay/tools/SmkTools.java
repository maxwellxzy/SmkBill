package com.ruoyi.pay.tools;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SmkTools {
    public static boolean GBKToUTF8(String srcPath, String dstPath){
        try {
            InputStream inputStream = new FileInputStream(srcPath);
            InputStreamReader reader = new InputStreamReader(inputStream,"GBK");
            char[] buff = new char[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len = reader.read(buff)) != -1){
                sb.append(new String(buff,0,len));
            }
            inputStream.close();
            reader.close();
            // 创建一个文件输出流对象，指定文件对象和编码为UTF-8
            File output = new File(dstPath);
            FileOutputStream fos = new FileOutputStream(output);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            // 把字符串写入文件输出流
            osw.write(sb.toString());

            // 关闭文件输出流
            osw.close();
            fos.close();

            return true;

        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
            return false;

        }
    }
    /**
     * 读取resources下的yaml文件。文件名自定义。
     * */
    public static String ReadYamlFile(String fileName,String keyword){
            YamlPropertiesFactoryBean yamlProFb = new YamlPropertiesFactoryBean();
            yamlProFb.setResources(new ClassPathResource(fileName));
            Properties properties = yamlProFb.getObject();
            return properties.getProperty(keyword);

    }
    public static String ReadYamlFile(String keyword){
         return ReadYamlFile("application.yml",keyword);
    }

    public static String getYesterday() {
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}