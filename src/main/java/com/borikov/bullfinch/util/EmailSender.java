package com.borikov.bullfinch.util;

import java.util.ResourceBundle;

public class EmailSender {
    private static final String EMAIL_HEAD = "Bullfinch tattoo";
    private static final String EMAIL_BODY = "Follow link to confirm your " +
            "mail to register on site bullfinch: " +
            "http://localhost:8080/controller?commandName=confirm_email_command&login=";
    private static final String FILE_NAME = "mail";

    private EmailSender() {
    }

    public static void sendMessage(String email, String login) {
        ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);
        EmailThread sender = new EmailThread(email, EMAIL_HEAD, EMAIL_BODY + login, bundle);
        sender.start();
    }
}
