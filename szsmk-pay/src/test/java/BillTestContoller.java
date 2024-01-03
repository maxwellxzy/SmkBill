import com.ruoyi.common.core.controller.BaseController;

import com.ruoyi.pay.service.IWechatFundService;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillTestContoller extends BaseController{

    @Autowired
    private IWechatFundService wechatFundService;

    @Test
    @GetMapping("/hello")
    public void testWechat(){
        System.out.println("hello!");
      //  WechatFund fund = ImportCsv.importWechatFund("/var/pay/download/wechat/2023-12-17.fund.csv");
  //      wechatFundService.insertWechatFund(fund);
    }
}
