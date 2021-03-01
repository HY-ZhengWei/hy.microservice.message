package org.hy.microservice.message;

import org.hy.microservice.common.BaseViewMode;





/**
 * 消息的接收者
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public class Reciver extends BaseViewMode
{

    private static final long serialVersionUID = -523700887845896097L;
    
    /** 手机号 */
    private String phone;
    
    /** 消息内容 */
    private String message;
    
    
    
    /**
     * 获取：手机号
     */
    public String getPhone()
    {
        return phone;
    }

    
    /**
     * 获取：消息内容
     */
    public String getMessage()
    {
        return message;
    }

    
    /**
     * 设置：手机号
     * 
     * @param phone 
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    
    /**
     * 设置：消息内容
     * 
     * @param message 
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
    
}
