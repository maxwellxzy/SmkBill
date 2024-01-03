package com.ruoyi.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户号管理对象 pay_mch_id
 * 
 * @author xuzy
 * @date 2024-01-03
 */
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

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 商户号名称 */
    @Excel(name = "商户号名称")
    private String mchName;

    /** api key */
    @Excel(name = "api key")
    private String apiKey;

    /** 证书序列号 */
    @Excel(name = "证书序列号")
    private String snKey;

    /** 微信证书路径 */
    @Excel(name = "微信证书路径")
    private String wechatApiCert;

    /** 支付宝公钥路径 */
    @Excel(name = "支付宝公钥路径")
    private String alipayCertPath;

    /** 支付宝应用私钥 */
    @Excel(name = "支付宝应用私钥")
    private String alipayappPrivatePath;

    /** 支付宝应用公钥证书 */
    @Excel(name = "支付宝应用公钥证书")
    private String alipayappPublicPath;

    /** alipay根证书 */
    @Excel(name = "alipay根证书")
    private String alipayRootPath;

    /** 备用证书1 */
    @Excel(name = "备用证书1")
    private String b1Key;

    /** 备用证书2 */
    @Excel(name = "备用证书2")
    private String b2Key;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMchType(String mchType) 
    {
        this.mchType = mchType;
    }

    public String getMchType() 
    {
        return mchType;
    }
    public void setMchId(String mchId) 
    {
        this.mchId = mchId;
    }

    public String getMchId() 
    {
        return mchId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setMchName(String mchName) 
    {
        this.mchName = mchName;
    }

    public String getMchName() 
    {
        return mchName;
    }
    public void setApiKey(String apiKey) 
    {
        this.apiKey = apiKey;
    }

    public String getApiKey() 
    {
        return apiKey;
    }
    public void setSnKey(String snKey) 
    {
        this.snKey = snKey;
    }

    public String getSnKey() 
    {
        return snKey;
    }
    public void setWechatApiCert(String wechatApiCert) 
    {
        this.wechatApiCert = wechatApiCert;
    }

    public String getWechatApiCert() 
    {
        return wechatApiCert;
    }
    public void setAlipayCertPath(String alipayCertPath) 
    {
        this.alipayCertPath = alipayCertPath;
    }

    public String getAlipayCertPath() 
    {
        return alipayCertPath;
    }
    public void setAlipayappPrivatePath(String alipayappPrivatePath) 
    {
        this.alipayappPrivatePath = alipayappPrivatePath;
    }

    public String getAlipayappPrivatePath() 
    {
        return alipayappPrivatePath;
    }
    public void setAlipayappPublicPath(String alipayappPublicPath) 
    {
        this.alipayappPublicPath = alipayappPublicPath;
    }

    public String getAlipayappPublicPath() 
    {
        return alipayappPublicPath;
    }
    public void setAlipayRootPath(String alipayRootPath) 
    {
        this.alipayRootPath = alipayRootPath;
    }

    public String getAlipayRootPath() 
    {
        return alipayRootPath;
    }
    public void setB1Key(String b1Key) 
    {
        this.b1Key = b1Key;
    }

    public String getB1Key() 
    {
        return b1Key;
    }
    public void setB2Key(String b2Key) 
    {
        this.b2Key = b2Key;
    }

    public String getB2Key() 
    {
        return b2Key;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mchType", getMchType())
            .append("mchId", getMchId())
            .append("status", getStatus())
            .append("mchName", getMchName())
            .append("apiKey", getApiKey())
            .append("snKey", getSnKey())
            .append("wechatApiCert", getWechatApiCert())
            .append("alipayCertPath", getAlipayCertPath())
            .append("alipayappPrivatePath", getAlipayappPrivatePath())
            .append("alipayappPublicPath", getAlipayappPublicPath())
            .append("alipayRootPath", getAlipayRootPath())
            .append("b1Key", getB1Key())
            .append("b2Key", getB2Key())
            .append("remark", getRemark())
            .toString();
    }
}
