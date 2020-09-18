package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ResourceBundle;

public class EmailThread extends Thread {
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final ResourceBundle bundle;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TEXT_TYPE = "text/html";

    public EmailThread(String sendToEmail, String mailSubject, String mailText, ResourceBundle bundle) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.bundle = bundle;
    }

    private void initMessage() {
        Session mailSession = EmailSessionCreator.createSession(bundle);
        try {
            message = new MimeMessage(mailSession);
            message.setSubject(mailSubject);
            message.setContent(mailText, TEXT_TYPE);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Invalid address: {}", sendToEmail, e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }

    @Override
    public void run() {
        initMessage();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }
}
