package org.hy.microservice.message.sms;

import org.hy.common.xml.SerializableDef;





/**
 * 短信请求报文
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public class SMSRequest extends SerializableDef
{

    private static final long serialVersionUID = -1635987336680311108L;
    
    /** 账户编号 */
    private String accountId;
    
    /** 产品编码，供应商提供 */
    private String productId;
    
    /** 加密后的key */
    private String accessKey;
    
    /** Unix时间戳，精确到秒，长度10，不能含有小数及L */
    private Long   timestamp;
    
    /** 随机数，大于等于1，小于等于9223372036854775807 */
    private Long   random;
    
    /** 接收号码间用英文半角逗号“,”隔开，触发产品一次只能提交一个,其他产品一次不能超过10万个号码 */
    private String phoneNos;
    
    /** 短信内容：不超过4000字符 */
    private String content;

    
    
    /**
     * 获取：账户编号
     */
    public String getAccountId()
    {
        return accountId;
    }

    
    /**
     * 获取：产品编码，供应商提供
     */
    public String getProductId()
    {
        return productId;
    }

    
    /**
     * 获取：加密后的key
     */
    public String getAccessKey()
    {
        return accessKey;
    }

    
    /**
     * 获取：Unix时间戳，精确到秒，长度10，不能含有小数及L
     */
    public Long getTimestamp()
    {
        return timestamp;
    }

    
    /**
     * 获取：随机数，大于等于1，小于等于9223372036854775807
     */
    public Long getRandom()
    {
        return random;
    }

    
    /**
     * 获取：接收号码间用英文半角逗号“,”隔开，触发产品一次只能提交一个,其他产品一次不能超过10万个号码
     */
    public String getPhoneNos()
    {
        return phoneNos;
    }

    
    /**
     * 获取：短信内容：不超过4000字符
     */
    public String getContent()
    {
        return content;
    }

    
    /**
     * 设置：账户编号
     * 
     * @param accountId 
     */
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    
    /**
     * 设置：产品编码，供应商提供
     * 
     * @param productId 
     */
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    
    /**
     * 设置：加密后的key
     * 
     * @param accessKey 
     */
    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    
    /**
     * 设置：Unix时间戳，精确到秒，长度10，不能含有小数及L
     * 
     * @param timestamp 
     */
    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    
    /**
     * 设置：随机数，大于等于1，小于等于9223372036854775807
     * 
     * @param random 
     */
    public void setRandom(Long random)
    {
        this.random = random;
    }

    
    /**
     * 设置：接收号码间用英文半角逗号“,”隔开，触发产品一次只能提交一个,其他产品一次不能超过10万个号码
     * 
     * @param phoneNos 
     */
    public void setPhoneNos(String phoneNos)
    {
        this.phoneNos = phoneNos;
    }

    
    /**
     * 设置：短信内容：不超过4000字符
     * 
     * @param content 
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
}
