package com.borikov.bullfinch.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailSessionCreator {
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private EmailSessionCreator() {
    }

    public static Session createSession(Properties properties) {
        String userName = properties.getProperty(MAIL_USER_NAME);
        String userPasswordHidden = properties.getProperty(MAIL_USER_PASSWORD);
        String userPassword = System.getenv(userPasswordHidden);
        return Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
