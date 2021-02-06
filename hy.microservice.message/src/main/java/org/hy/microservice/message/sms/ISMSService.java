package org.hy.microservice.message.sms;

import org.hy.common.Return;
import org.hy.microservice.message.Reciver;





/**
 * 发短信的业务接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-02-06
 * @version     v1.0
 */
public interface ISMSService
{
   
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
    public Return<Object> send(Reciver i_Reciver);
    
}
