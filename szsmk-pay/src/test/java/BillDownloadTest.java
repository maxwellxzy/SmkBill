import com.ruoyi.pay.tools.AlipayMch;
import com.ruoyi.pay.tools.WechatMch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;

/** BillDownloadService使用示例 */
public class BillDownloadTest {


  public static void main(String[] args) {
    String downloadPath = "/var/pay/download/";
    String certPath = "/var/pay/cert/";
    WechatMch wechatMch = new WechatMch(
        "1507955111",
//        "",
        "/var/pay/cert/wechat_1507955111/apiclient_key.pem",
        "756F9AC86A73203F7284EF859A31565890C291B7",
        "u8N7d5F0P8n6G4X1lIj70M1k7uBczP42",
        "2023-12-15",
        downloadPath
    );
    /**
    WechatMch wechatMch1 = new WechatMch(
        "1507955111",
        certPath,
        downloadPath
    );
**/
    AlipayMch alipayMch = new AlipayMch(
        "2021001161687288",
        "/var/pay/cert/alipay/appPrivate_2021001161687288.txt",
        "/var/pay/cert/alipay/alipayCertPublicKey_RSA2_2021001161687288.crt",
        "/var/pay/cert/alipay/appCertPublicKey_2021001161687288.crt",
        "/var/pay/cert/alipay/alipayRootCert_2021001161687288.crt",
        "2023-12-19",
        downloadPath
    );

    AlipayMch alipayMch1 = new AlipayMch(
        "2021001161687288",
        certPath,
        downloadPath
    );

    alipayMch.downloadAlipayBill();
    //    wechatMch.downloadFundBill();

    // wechatMch.downloadTradeBill();
 //   wechatMch1.downloadTradeBill();
 //   wechatMch1.downloadFundBill();
//    alipayMch.downloadAlipayBill();
//      alipayMch1.downloadAlipayBill();


//    file.downloadAlipayZip("2021001161687288");

    }
  }
