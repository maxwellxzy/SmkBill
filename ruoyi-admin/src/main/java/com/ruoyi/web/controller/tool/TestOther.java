package com.ruoyi.web.controller.tool;

import com.ruoyi.pay.tools.SmkTools;
import com.ruoyi.web.controller.pay.auto.AutoAddData;
import com.ruoyi.web.controller.pay.auto.AutoDownloadFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class TestOther {

    @Value("${server.port}")
    private String a;
    @Test

    public void testabc(){

//        AutoDownloadFile.downloadAlipayZip("2021001161687288");
//        AutoDownloadFile.downloadWechatFund("1507955111");
//        AutoDownloadFile.downloadWechatTrade("1507955111");

    }

}
