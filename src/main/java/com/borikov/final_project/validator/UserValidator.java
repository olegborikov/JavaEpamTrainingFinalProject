package com.borikov.final_project.validator;

public class UserValidator {
    private static final String EMAIL_REGEX =
            "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*\\d)[\\p{Alpha}\\d]{8,20}$";

    public boolean isEmailCorrect(String email) {
        boolean result = false;
        if (email != null) {
            result = email.matches(EMAIL_REGEX);
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
}
