package com.borikov.bullfinch.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The {@code EmailSessionCreator} class represents email session creator.
 *
 * @author Oleg Borikov
 * @version 1.0
 * @since 2020-10-16
 */
public class EmailSessionCreator {
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private EmailSessionCreator() {
    }

    /**
     * Create session.
     *
     * @param properties the properties
     * @return the session
     */
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
