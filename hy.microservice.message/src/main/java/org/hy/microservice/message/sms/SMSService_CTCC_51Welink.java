package org.hy.microservice.message.sms;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.Return;
import org.hy.common.license.Hash;
import org.hy.common.xml.XHttp;
import org.hy.common.xml.XJSON;
import org.hy.common.xml.log.Logger;
import org.hy.microservice.message.Reciver;





/**
 * 发短信的业务（电信：爱动漫）
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public class SMSService_CTCC_51Welink implements ISMSService
{
    
    private static final Logger $Logger = Logger.getLogger(SMSService_CTCC_51Welink.class);
    
    
    
    private XHttp      api;
    
    private SMSAccount account;
    
    private Hash       encrypt01;
    
    private Hash       encrypt02;
    
    
    
    public SMSService_CTCC_51Welink(XHttp i_Api ,SMSAccount i_Account)
    {
        this.api       = i_Api;
        this.account   = i_Account;
        this.encrypt01 = new Hash(1 ,4 ,null);
        this.encrypt02 = new Hash(2 ,3 ,null ,false);
    }
    
    
    
    /**
     * 发送短信
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-02-06
     * @version     v1.0
     *
     * @param i_Reciver  接收者
     * @return
     */
    public Return<Object> send(Reciver i_Reciver)
    {
        String        v_Password    = this.encrypt01.encrypt(this.account.getAccountPwd() + "SMmsEncryptKey");
        SMSRequest    v_RequestData = new SMSRequest();
        StringBuilder v_AccessKey   = new StringBuilder();
        
        v_RequestData.setAccountId(this.account.getAccountId());
        v_RequestData.setProductId(this.account.getProductId());
        v_RequestData.setPhoneNos(i_Reciver.getPhone());
        v_RequestData.setContent( i_Reciver.getMessage());
        v_RequestData.setTimestamp(Date.getNowTime().getTime() / 1000);
        v_RequestData.setRandom((long)Help.random(1 ,Integer.MAX_VALUE));
        
        v_AccessKey.append("AccountId=").append(this.account.getAccountId()).append("&");
        v_AccessKey.append("PhoneNos=") .append(i_Reciver.getPhone())       .append("&");
        v_AccessKey.append("Password=") .append(v_Password)                 .append("&");
        v_AccessKey.append("Random=")   .append(v_RequestData.getRandom())  .append("&");
        v_AccessKey.append("Timestamp=").append(v_RequestData.getTimestamp());
        
        v_RequestData.setAccessKey(this.encrypt02.encrypt(v_AccessKey.toString()).toLowerCase());
        
        try
        {
            XJSON     v_XJson = new XJSON();
            Return<?> v_Ret   = this.api.request("" ,v_XJson.toJson(v_RequestData).toJSONString());
            
            if ( v_Ret != null && v_Ret.booleanValue() && !Help.isNull(v_Ret.getParamStr()) )
            {
                SMSResponse v_Data = (SMSResponse)v_XJson.toJava(v_Ret.getParamStr() ,SMSResponse.class);
                
                if ( v_Data != null )
                {
                    if ( "succ".equals(v_Data.getResult()) )
                    {
                        return new Return<Object>(true).setParamStr(v_Data.getMsgId());
                    }
                    else
                    {
                        return new Return<Object>(false).setParamStr(v_Data.getResult() + "=" + v_Data.getReason());
                    }
                }
                else
                {
                    $Logger.error("短信发送异常：" + i_Reciver.getPhone() + i_Reciver.getMessage() + " @ " + v_Ret.getParamStr());
                }
            }
            else
            {
                $Logger.error("短信发送异常：" + i_Reciver.getPhone() + i_Reciver.getMessage() + " @ " + v_Ret.getParamStr());
            }
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
        }
        
        return new Return<Object>(false);
    }
    
}
