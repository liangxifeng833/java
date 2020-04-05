package com.soufang.esproject.lxf;

import com.soufang.esproject.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Description: 发送邮件测试
 * Create by liangxifeng on 19-6-20
 */
public class SendMailTest extends ApplicationTests {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("786285076@qq.com");
        message.setTo("liangxifeng833@163.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

}
