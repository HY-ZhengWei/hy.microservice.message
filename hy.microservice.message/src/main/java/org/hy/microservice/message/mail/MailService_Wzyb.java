package org.hy.microservice.message.mail;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.Return;
import org.hy.common.mail.MailOwnerInfo;
import org.hy.common.mail.MailSendInfo;
import org.hy.common.mail.SimpleMail;
import org.hy.common.xml.log.Logger;
import org.hy.microservice.message.Reciver;





/**
 * 发邮件的业务接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2021-05-10
 * @version     v1.0
 */
public class MailService_Wzyb implements IMailService
{
    private static final Logger $Logger = Logger.getLogger(MailService_Wzyb.class);
    
    private MailOwnerInfo mail;
    
    
    
    public MailService_Wzyb(MailOwnerInfo i_Mail)
    {
        this.mail = i_Mail;
    }
    
    
    
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
    @Override
    public Return<Object> send(Reciver i_Reciver)
    {
        MailSendInfo v_SendInfo = new MailSendInfo();
        
        v_SendInfo.addEmail(  i_Reciver.getPhone());
        v_SendInfo.setSubject(Help.NVL(i_Reciver.getTitle() ,Date.getNowTime().getFull()));
        v_SendInfo.setContent(i_Reciver.getMessage());
        
        Return<Object> v_Ret = new Return<Object>(false);
        
        try
        {
            v_Ret.set(SimpleMail.sendHtmlMail(this.mail ,v_SendInfo));
            
            if ( !v_Ret.get() )
            {
                $Logger.error("邮件发送异常：" + i_Reciver.getPhone() + v_SendInfo.getSubject() + "：" + i_Reciver.getMessage());
            }
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
        }
        
        return v_Ret;
    }
    
}
