package com.lfg.email;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * Created by lifengguang on 2017/8/17.
 */
public class MailSender {

    public MailSender(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;

    @Value("${from}")
    private String userName;

    @Value("${pwd}")
    private String pwd;

    @Value("${to}")
    private String to;

    public void send() {
        //MailInfo mailInfo = new MailInfo("645765227@qq.com","ibgwanmavpzrbbai");
        System.out.println("===="+userName);
        MailInfo mailInfo = new MailInfo(userName,pwd);
        mailInfo.setNotifyTo(to);
        mailInfo.setMailHost("smtp.qq.com");
        mailInfo.setMailPort("587");
        mailInfo.setFilePath(filePath);
        MimeMessage build = mailInfo.build();
        try {
            Transport.send(build,build.getAllRecipients());
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
