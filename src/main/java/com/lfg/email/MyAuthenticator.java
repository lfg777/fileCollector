package com.lfg.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by lifengguang on 2017/8/17.
 */
public class MyAuthenticator extends Authenticator {

    public MyAuthenticator(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    private String userName="";

    private String pwd="";

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, pwd);
    }

}
