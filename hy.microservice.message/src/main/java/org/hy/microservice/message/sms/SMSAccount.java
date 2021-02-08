package org.hy.microservice.message.sms;

import org.hy.common.xml.SerializableDef;





/**
 * 短信账号的信息 
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public class SMSAccount extends SerializableDef
{
    
    private static final long serialVersionUID = 8250009688065090263L;

    /** 账户编号 */
    private String accountId;
    
    /** 账户密码 */
    private String accountPwd;
    
    /** 产品编码，供应商提供 */
    private String productId;
    
    
    
    /**
     * 获取：账户编号
     */
    public String getAccountId()
    {
        return accountId;
    }

    
    /**
     * 获取：账户密码
     */
    public String getAccountPwd()
    {
        return accountPwd;
    }

    
    /**
     * 获取：产品编码，供应商提供
     */
    public String getProductId()
    {
        return productId;
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
     * 设置：账户密码
     * 
     * @param accountPwd 
     */
    public void setAccountPwd(String accountPwd)
    {
        this.accountPwd = accountPwd;
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
    
}
