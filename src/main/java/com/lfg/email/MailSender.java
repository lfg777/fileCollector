package com.lfg.email;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lifengguang on 2017/8/17.
 */
public class MailSender {

    public MailSender(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;


    private String userName;


    private String pwd;


    private String to;

    public void send() {
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MailSender setParams(String... args) {
        if (args.length > 0) {
            List<String> params = Arrays.asList(args);
            for (String param : params) {
                if (param.contains("-from")) {
                    setUserName(param.substring(param.indexOf("-from=")+6));
                }
                if (param.contains("-pwd")) {
                    setPwd(param.substring(param.indexOf("-pwd=")+5));
                }
                if (param.contains("-to")) {
                    setTo(param.substring(param.indexOf("-to=")+4));
                }
            }

        }
        return this;
    }
}
