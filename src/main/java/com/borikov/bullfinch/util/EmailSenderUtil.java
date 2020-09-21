package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSenderUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMAIL_HEAD = "Bullfinch tattoo";
    private static final String EMAIL_BODY = "Follow link to confirm your " +
            "mail to register on site bullfinch: " +
            "http://localhost:8080/controller?commandName=confirm_email_command&login=";
    private static final String FILE_NAME = "property/mail.properties";

    private EmailSenderUtil() {
    }

    public static void sendMessage(String email, String login) {
        try {
            Properties properties = new Properties();
            InputStream inputStream = EmailSenderUtil.class.getClassLoader().getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
            Thread thread = new Thread(new EmailSenderThread(
                    email, EMAIL_HEAD, EMAIL_BODY + login, properties));
            thread.start();
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error with mail properties file", e);
        }
    }
}
