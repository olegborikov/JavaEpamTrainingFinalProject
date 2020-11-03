package com.borikov.bullfinch.model.validator;

/**
 * The type User validator.
 */
public class UserValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String EMAIL_REGEX = "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String FIRST_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String SECOND_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)\\p{Alnum}{8,20}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?375(24|25|29|33|44)\\d{7}|80(24|25|29|33|44)\\d{7}$";

    private UserValidator() {
    }

    /**
     * Is id correct boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    /**
     * Is email correct boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    /**
     * Is login correct boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    /**
     * Is password correct boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isPasswordCorrect(String email) {
        return isStringCorrect(email, PASSWORD_REGEX);
    }

    /**
     * Is first name correct boolean.
     *
     * @param firstName the first name
     * @return the boolean
     */
    public static boolean isFirstNameCorrect(String firstName) {
        return isStringCorrect(firstName, FIRST_NAME_REGEX);
    }

    /**
     * Is second name correct boolean.
     *
     * @param secondName the second name
     * @return the boolean
     */
    public static boolean isSecondNameCorrect(String secondName) {
        return isStringCorrect(secondName, SECOND_NAME_REGEX);
    }

    /**
     * Is phone number correct boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public static boolean isPhoneNumberCorrect(String phoneNumber) {
        return isStringCorrect(phoneNumber, PHONE_NUMBER_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
