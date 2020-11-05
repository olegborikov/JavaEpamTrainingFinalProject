package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code EmailSenderUtil} class represents email sender.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class EmailSenderUtil {
    private static final String EMAIL_HEAD = "Bullfinch tattoo";
    private static final String EMAIL_BODY = "Follow link to confirm your mail to register "
            + "on site bullfinch: %s?commandName=confirm_email_command&login=";
    private static final String FILE_NAME = "property/mail.properties";
    private static final Logger LOGGER = LogManager.getLogger();

    private EmailSenderUtil() {
    }

    /**
     * Send message.
     *
     * @param email the email
     * @param login the login
     * @param url   the url
     */
    public static void sendMessage(String email, String login, String url) {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = EmailSenderUtil.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
            Thread thread = new Thread(new EmailSenderThread(email, EMAIL_HEAD,
                    String.format(EMAIL_BODY, url) + login, properties));
            thread.start();
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error while reading properties file: {}", FILE_NAME, e);
        }
    }
}
