package org.hy.microservice.message;

import org.hy.common.Help;
import org.hy.common.Return;
import org.hy.common.app.Param;
import org.hy.common.xml.log.Logger;
import org.hy.microservice.common.BaseResponse;
import org.hy.microservice.message.sms.ISMSService;
import org.hy.microservice.message.user.UserSSO;
import org.hy.microservice.message.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





/**
 * 短消息的控制层
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
@Controller
@RequestMapping("message")
public class MessageController
{
    
    private static final Logger $Logger = new Logger(MessageController.class);
    
    
    
    @Autowired
    @Qualifier("UserService")
    public UserService userService;
    
    @Autowired
    @Qualifier("MS_Message_IsCheckToken")
    public Param isCheckToken;
    
    @Autowired
    @Qualifier("MS_Message_Service_SMSService")
    private ISMSService smsService;
    
    
    
    /**
     * 发短信
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-02-06
     * @version     v1.0
     *
     * @param i_Token    认证票据号
     * @param i_Reciver  接收者
     * @return
     */
    @RequestMapping(value="sendSMS" ,method={RequestMethod.POST} ,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<Reciver> sendSMS(@RequestParam("token") String i_Token ,@RequestBody Reciver i_Reciver)
    {
        BaseResponse<Reciver> v_RetResp = new BaseResponse<Reciver>();
        
        if ( i_Reciver == null )
        {
            return v_RetResp.setCode("-1").setMessage("未收到任何参数");
        }
        
        if ( Help.isNull(i_Reciver.getPhone()) ) 
        {
            return v_RetResp.setCode("-2").setMessage("手机号为空");
        }
        
        if ( Help.isNull(i_Reciver.getMessage()) ) 
        {
            return v_RetResp.setCode("-3").setMessage("消息内容为空");
        }
        
        
        UserSSO v_User = null;
        if ( isCheckToken != null && Boolean.parseBoolean(isCheckToken.getValue()) )
        {
            // 验证票据及用户登录状态
            if ( Help.isNull(i_Token) ) 
            {
                return v_RetResp.setCode("-901").setMessage("非法访问");
            }
            
            v_User = this.userService.getUser(i_Token);
            if ( v_User == null ) 
            {
                return v_RetResp.setCode("-901").setMessage("非法访问");
            }
        }
        
        
        Return<Object> v_SendRet = null;
        try
        {
            v_SendRet = this.smsService.send(i_Reciver);
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
        }
        
        if ( v_SendRet.booleanValue() )
        {
            if ( v_User != null )
            {
                $Logger.info("用户（" + v_User.getUserName() + v_User.getUserId() + "）发短信给" + i_Reciver.getPhone() + "：" + i_Reciver.getMessage() + "，成功");
            }
            else
            {
                $Logger.info("发短信给" + i_Reciver.getPhone() + "：" + i_Reciver.getMessage() + "，成功");
            }
            
            return v_RetResp;
        }
        else
        {
            if ( v_User != null )
            {
                $Logger.error("用户（" + v_User.getUserName() + v_User.getUserId() + "）发短信给" + i_Reciver.getPhone() + "：" + i_Reciver.getMessage() + "，异常。"  + v_SendRet.getParamStr());
            }
            else
            {
                $Logger.error("发短信给" + i_Reciver.getPhone() + "：" + i_Reciver.getMessage() + "，异常。" + v_SendRet.getParamStr());
            }
            
            return v_RetResp.setCode("-999").setMessage(Help.NVL(v_SendRet.getParamStr() ,"系统异常"));
        }
    }
    
}
