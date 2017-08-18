package com.lfg.email;

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

    public void send() {
        MailInfo mailInfo = new MailInfo("lifengguang@ppdai.com","123456");
        MimeMessage build = mailInfo.build();
        try {
            Transport.send(build,build.getAllRecipients());
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }


}
