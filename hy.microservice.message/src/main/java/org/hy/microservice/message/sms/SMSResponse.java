package org.hy.microservice.message.sms;

import org.hy.common.xml.SerializableDef;





/**
 * 短信响应报文
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public class SMSResponse extends SerializableDef
{

    private static final long serialVersionUID = 7065054043409302564L;
    
    /** 执行结果标识。succ 表示成功 */
    private String result;
    
    /** 执行结果文字说明 */
    private String reason;
    
    /** 消息编号 */
    private String msgId;
    
    /** 单条短信内容拆分条数 */
    private Integer splitCount;

    
    
    /**
     * 获取：执行结果标识。succ 表示成功
     */
    public String getResult()
    {
        return result;
    }

    
    /**
     * 获取：执行结果文字说明
     */
    public String getReason()
    {
        return reason;
    }

    
    /**
     * 获取：消息编号
     */
    public String getMsgId()
    {
        return msgId;
    }

    
    /**
     * 获取：单条短信内容拆分条数
     */
    public Integer getSplitCount()
    {
        return splitCount;
    }

    
    /**
     * 设置：执行结果标识。succ 表示成功
     * 
     * @param result 
     */
    public void setResult(String result)
    {
        this.result = result;
    }

    
    /**
     * 设置：执行结果文字说明
     * 
     * @param reason 
     */
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    
    /**
     * 设置：消息编号
     * 
     * @param msgId 
     */
    public void setMsgId(String msgId)
    {
        this.msgId = msgId;
    }

    
    /**
     * 设置：单条短信内容拆分条数
     * 
     * @param splitCount 
     */
    public void setSplitCount(Integer splitCount)
    {
        this.splitCount = splitCount;
    }
    
}
