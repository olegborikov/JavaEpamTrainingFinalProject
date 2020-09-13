package com.borikov.bullfinch.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSessionCreator {
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private MailSessionCreator() {
    }

    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(MAIL_USER_NAME);
        String userPasswordHidden = configProperties.getProperty(MAIL_USER_PASSWORD);
        String userPassword = System.getenv(userPasswordHidden);
        return Session.getDefaultInstance(configProperties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
