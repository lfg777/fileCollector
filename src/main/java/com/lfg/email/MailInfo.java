package com.lfg.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by lifengguang on 2017/8/17.
 */
public class MailInfo {

    public MailInfo(String username,String pwd) {
        this.username = username;
        this.password = pwd;
    }

    /** 发送邮件的服务器的IP */
    private String mailHost;
    /** 发送邮件的服务器的端口 */
    private String mailPort = "465";
    /** 发送邮件的用户名（邮箱全名称） */
    private String username;
    /** 发送邮件的密码 */
    private String password;

    /** 通知信息发送地址（多个邮件地址以";"分隔） */
    private String notifyTo;
    /** 通知信息抄送地址（多个邮件地址以";"分隔） */
    private String notifyCc;

    /** 邮件主题 */
    private String subject;
    /** 邮件内容 */
    private String content;
    /** 邮件附件的文件名 */
    private String[] attachFileNames;
    private String filePath;

    /**
     * 获取邮件参数
     *
     * @return
     * @throws GeneralSecurityException
     */
    public Properties getProperties() throws GeneralSecurityException {
        Properties props = new Properties();
        props.put("mail.smtp.host", getMailHost());
        props.put("mail.smtp.port", getMailPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.user", getUsername());
        props.put("mail.password", getPassword());
        return props;
    }

    public MimeMessage build() {
        Session session = null;
        try {
            session = Session.getDefaultInstance(getProperties(), new MyAuthenticator(username, password));
            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setFrom(new InternetAddress("lifengguang@ppdai.com"));
            // 指明邮件的收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(notifyTo, ""));
// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentBodyPart = new MimeBodyPart();
            // 邮件内容
            String result = "chrome 浏览器书签文件！";
            contentBodyPart.setContent(result, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentBodyPart);


            // 添加附件
            if (filePath != null && !"".equals(filePath)) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                // 根据附件路径获取文件,
                FileDataSource dataSource = new FileDataSource(filePath);
                attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(dataSource.getFile().getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            // 邮件的文本内容
            message.setContent(multipart);
            return message;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * @return the mailHost
     */
    public String getMailHost() {
        return mailHost;
    }

    /**
     * @param mailHost
     *
     */
    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    /**
     * @return the mailPort
     */
    public String getMailPort() {
        return mailPort;
    }

    /**
     * @param mailPort
     *
     */
    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the notifyTo
     */
    public String getNotifyTo() {
        return notifyTo;
    }

    /**
     * @param notifyTo
     *
     */
    public void setNotifyTo(String notifyTo) {
        this.notifyTo = notifyTo;
    }

    /**
     * @return the notifyCc
     */
    public String getNotifyCc() {
        return notifyCc;
    }

    /**
     * @param notifyCc
     *
     */
    public void setNotifyCc(String notifyCc) {
        this.notifyCc = notifyCc;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Returns the attachFileNames.
     */
    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    /**
     * @param attachFileNames
     *
     */
    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
