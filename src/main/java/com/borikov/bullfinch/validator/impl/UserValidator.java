package com.borikov.bullfinch.validator.impl;

import com.borikov.bullfinch.validator.AbstractValidator;

public class UserValidator extends AbstractValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String EMAIL_REGEX =
            "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String FIRST_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String SECOND_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)\\p{Alnum}{8,20}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?375(24|25|29|33|44)" +
            "\\d{7}|80(24|25|29|33|44)\\d{7}$";

    public boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    public boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    public boolean isPasswordCorrect(String email) {
        return isStringCorrect(email, PASSWORD_REGEX);
    }

    public boolean isFirstNameCorrect(String firstName) {
        return isStringCorrect(firstName, FIRST_NAME_REGEX);
    }

    public boolean isSecondNameCorrect(String secondName) {
        return isStringCorrect(secondName, SECOND_NAME_REGEX);
    }

    public boolean isPhoneNumberCorrect(String phoneNumber) {
        return isStringCorrect(phoneNumber, PHONE_NUMBER_REGEX);
    }
}
