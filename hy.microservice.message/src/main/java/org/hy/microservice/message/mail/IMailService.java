package org.hy.microservice.message.mail;

import org.hy.common.Return;
import org.hy.microservice.message.Reciver;





/**
 * 发邮件的业务接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-05-10
 * @version     v1.0
 */
public interface IMailService
{
    
    /**
     * 发送邮件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2021-05-10
     * @version     v1.0
     *
     * @param i_Reciver  接收者
     * @return
     */
    public Return<Object> send(Reciver i_Reciver);
    
}
