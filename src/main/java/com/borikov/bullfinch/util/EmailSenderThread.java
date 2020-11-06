package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The {@code EmailSenderThread} class represents email sender thread.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
class EmailSenderThread implements Runnable {
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TEXT_TYPE = "text/html";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    /**
     * Instantiates a new Email sender thread.
     *
     * @param sendToEmail the send to email
     * @param mailSubject the mail subject
     * @param mailText    the mail text
     * @param properties  the properties
     */
    public EmailSenderThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    @Override
    public void run() {
        initMessage();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error while generating or sending message: {}", message, e);
        }
    }

    private void initMessage() {
        Session mailSession = createSession(properties);
        try {
            message = new MimeMessage(mailSession);
            message.setSubject(mailSubject);
            message.setContent(mailText, TEXT_TYPE);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Invalid address: {}", sendToEmail, e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error while generating or sending message: {}", message, e);
        }
    }

    private Session createSession(Properties properties) {
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
