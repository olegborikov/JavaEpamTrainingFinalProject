package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {// TODO: 17.09.2020 refactor to 2 classes?
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TEXT_TYPE = "text/html";
    private static final String EMAIL_HEAD = "Bullfinch tattoo";
    private static final String EMAIL_BODY = "Follow link to confirm your " +
            "mail to register on site bullfinch: " +
            "http://localhost:8080/controller?commandName=confirm_email_command&login=";
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;

    private EmailSender(String sendToEmail, String mailSubject,
                        String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Invalid address: {}", sendToEmail, e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = EmailSessionCreator.createSession(properties);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, TEXT_TYPE);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    public static void sendConfirmMessage(String email, String login) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/mail.properties"));// TODO: 13.09.2020 refactor
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmailSender sender = new EmailSender(email, EMAIL_HEAD, EMAIL_BODY + login, properties);
        sender.send();
    }
}
