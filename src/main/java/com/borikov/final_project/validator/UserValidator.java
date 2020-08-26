package com.borikov.final_project.validator;

public class UserValidator {
    private static final String EMAIL_REGEX =
            "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*\\d)[\\p{Alnum}]{8,20}$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String SURNAME_REGEX = "^\\p{L}{2,25}$";

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

    public boolean isPasswordCorrect(String email) {
        boolean result = false;
        if (email != null) {
            result = email.matches(PASSWORD_REGEX);
        }
        return result;
    }

    public boolean isNameCorrect(String name) {
        boolean result = false;
        if (name != null) {
            result = name.matches(NAME_REGEX);
        }
        return result;
    }

    public boolean isSurnameCorrect(String surname) {
        boolean result = false;
        if (surname != null) {
            result = surname.matches(SURNAME_REGEX);
        }
        return result;
    }
}
