package org.hy.microservice.message.weixin;

import org.hy.common.Return;
import org.hy.microservice.message.Reciver;





/**
 * 发微信的业务接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-05-07
 * @version     v1.0
 */
public interface IWeiXinService
{
   
    /**
     * 发送短信
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-05-07
     * @version     v1.0
     *
     * @param i_Reciver  接收者
     * @return
     */
    public Return<Object> send(Reciver i_Reciver);
    
}