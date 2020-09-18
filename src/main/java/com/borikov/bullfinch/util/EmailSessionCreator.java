package com.borikov.bullfinch.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmailSessionCreator {
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private EmailSessionCreator() {
    }

    public static Session createSession(ResourceBundle bundle) {
        String userName = bundle.getString(MAIL_USER_NAME);
        String userPasswordHidden = bundle.getString(MAIL_USER_PASSWORD);
        String userPassword = System.getenv(userPasswordHidden);
        Properties properties = convertResourceBundleToProperties(bundle);
        return Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }

    private static Properties convertResourceBundleToProperties(ResourceBundle resource) {
        Properties properties = new Properties();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resource.getString(key));
        }
        return properties;
    }
}
