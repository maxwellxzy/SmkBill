package com.ruoyi.pay.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户号管理对象 pay_mch_id
 * 
 * @author xuzy
 * @date 2023-12-30
 */
@Data
public class PayMchId extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String mchType;

    /** 商户号ID */
    @Excel(name = "商户号ID")
    private String mchId;

    /** 商户号名称 */
    @Excel(name = "商户号名称")
    private String mchName;

    /** api key */
    private String apiKey;

    /** 证书序列号 */
    private String snKey;

    /** 微信证书路径 */
    private String wechatApiCert;

    /** 支付宝公钥路径 */
    private String alipayCertPath;

    /** 支付宝应用私钥 */
    private String alipayappPrivatePath;

    /** 支付宝应用公钥证书 */
    private String alipayappPublicPath;

    private  String alipayRootPath;

    /** 备用证书1 */
    private String b1Key;

    /** 备用证书2 */
    private String b2Key;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mchType", getMchType())
            .append("mchId", getMchId())
            .append("mchName", getMchName())
            .append("apiKey", getApiKey())
            .append("snKey", getSnKey())
            .append("wechatApiCert", getWechatApiCert())
            .append("alipayCertPath", getAlipayCertPath())
            .append("alipayappPrivatePath", getAlipayappPrivatePath())
            .append("alipayappPublicPath", getAlipayappPublicPath())
            .append("alipayRootPath",getAlipayRootPath())
            .append("b1Key", getB1Key())
            .append("b2Key", getB2Key())
            .append("remark", getRemark())
            .toString();
    }
}
