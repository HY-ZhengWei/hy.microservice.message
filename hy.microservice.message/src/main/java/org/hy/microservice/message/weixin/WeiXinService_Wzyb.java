package org.hy.microservice.message.weixin;

import java.util.HashMap;
import java.util.Map;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.Return;
import org.hy.common.xml.XHttp;
import org.hy.common.xml.XJSON;
import org.hy.common.xml.log.Logger;
import org.hy.microservice.message.Reciver;





/**
 * 发微信的业务接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-05-07
 * @version     v1.0
 */
public class WeiXinService_Wzyb implements IWeiXinService
{
    private static final Logger $Logger = Logger.getLogger(WeiXinService_Wzyb.class);
    
    
    private XHttp api;
    
    private String weiXinTemplateID;
    
    
    
    public WeiXinService_Wzyb(XHttp i_Api ,String i_WeiXinTemplateID)
    {
        this.api              = i_Api;
        this.weiXinTemplateID = i_WeiXinTemplateID;
    }
    
    
    
    /**
     * 发送微信
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-05-07
     * @version     v1.0
     *
     * @param i_Reciver  接收者
     * @return
     */
    @Override
    public Return<Object> send(Reciver i_Reciver)
    {
        Map<String ,Object> v_RequestBody = new HashMap<String ,Object>();
        Map<String ,Object> v_RequestData = new HashMap<String ,Object>();
        
        v_RequestData.put("templateId" ,this.weiXinTemplateID);
        v_RequestData.put("openid"     ,i_Reciver.getPhone());
        v_RequestData.put("first"      ,Help.NVL(i_Reciver.getTitle() ,Date.getNowTime().getFull()));
        v_RequestData.put("keyword1"   ,Date.getNowTime().getFull());
        v_RequestData.put("keyword2"   ,i_Reciver.getMessage());
        v_RequestData.put("remark"     ,"　");
        v_RequestBody.put("body"       ,v_RequestData);
        
        try
        {
            XJSON     v_XJson = new XJSON();
            Return<?> v_Ret   = this.api.request("" ,v_XJson.toJson(v_RequestBody).toJSONString());
            
            if ( v_Ret != null && v_Ret.booleanValue() && !Help.isNull(v_Ret.getParamStr()) )
            {
                if ( v_Ret.getParamStr().indexOf("\"result\":\"true\"") > 0 )
                {
                    return new Return<Object>(true).setParamStr(v_Ret.getParamStr());
                }
                else
                {
                    $Logger.error("微信发送异常：" + i_Reciver.getPhone() + i_Reciver.getMessage() + " @ " + v_Ret.getParamStr());
                }
            }
            else
            {
                $Logger.error("微信发送异常：" + i_Reciver.getPhone() + i_Reciver.getMessage() + " @ " + v_Ret.getParamStr());
            }
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
        }
        
        return new Return<Object>(false);
    }
    
}
