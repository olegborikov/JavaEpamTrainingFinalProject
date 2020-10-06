package com.borikov.bullfinch.validator;

public class UserValidator {
    private static final String EMAIL_REGEX =
            "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String FIRST_NAME_REGEX = "^[\\p{L}]{2,25}$";
    private static final String SECOND_NAME_REGEX = "^[\\p{L}]{2,25}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*\\d)[\\p{Alnum}]{8,20}$";
    private static final String PHONE_REGEX = "^\\+?375(24|25|29|33|44)[\\d]{7}" +
            "|80(24|25|29|33|44)[\\d]{7}$";

    public boolean isEmailCorrect(String email) {
        boolean result = false;
        if (email != null) {
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

    public boolean isLoginCorrect(String login) {
        boolean result = false;
        if (login != null) {
            result = login.matches(LOGIN_REGEX);
        }
        return result;
    }

    public boolean isFirstNameCorrect(String firstName) {
        boolean result = false;
        if (firstName != null) {
            result = firstName.matches(FIRST_NAME_REGEX);
        }
        return result;
    }

    public boolean isSecondNameCorrect(String secondName) {
        boolean result = false;
        if (secondName != null) {
            result = secondName.matches(SECOND_NAME_REGEX);
        }
        return result;
    }

    public boolean isPasswordCorrect(String email) {
        boolean result = false;
        if (email != null) {
            result = email.matches(PASSWORD_REGEX);
        }
        return result;
    }

    public boolean isPhoneCorrect(String phone) {
        boolean result = false;
        if (phone != null) {
            result = phone.matches(PHONE_REGEX);
        }
        return result;
    }
}
