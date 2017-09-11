package fish.miniblog.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2017/2/28.
 */
@Component
public class MailUtil {


    @Resource
    private JavaMailSenderImpl mailSender;

    public void send(String to, String text) {

        /*SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setFrom("1152100196@qq.com");   // 发件人
        mailMsg.setTo(to);                      // 收件人
        mailMsg.setSubject("账号激活");         // 邮件标题
        mailMsg.setText(text);                  // 发送内容

        mailSender.send(mailMsg);               // 发送*/

        MimeMessage mimeMsg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, false, "UTF-8");
//            mimeMsg.setContent(text, "text/html");
            helper.setText(text, true);       // 发送内容
            helper.setTo(to);                       // 收件人
            helper.setSubject("账号激活");          // 邮件标题
            helper.setFrom("1152100196@qq.com");   // 发件人

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMsg);
    }


}
