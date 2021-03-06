package com.borikov.bullfinch.model.validator;

import com.borikov.bullfinch.util.RegistrationParameter;

import java.util.Map;

/**
 * The {@code UserValidator} class represents user validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class UserValidator {
    private static final String EMPTY_VALUE = "";
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String EMAIL_REGEX = "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String LOGIN_SUBSTRING_REGEX = "^[\\w.]{0,20}$";
    private static final String FIRST_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String SECOND_NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)\\p{Alnum}{8,20}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?375(24|25|29|33|44)\\d{7}|80(24|25|29|33|44)\\d{7}$";

    private UserValidator() {
    }

    /**
     * Check registration parameters for correct.
     *
     * @param registrationParameters the registration parameters
     * @return the boolean
     */
    public static boolean isRegistrationParametersCorrect(Map<String, String> registrationParameters) {
        boolean isRegistrationParametersCorrect = true;
        if (!isEmailCorrect(registrationParameters.get(RegistrationParameter.EMAIL))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.EMAIL, EMPTY_VALUE);
        }
        if (!isLoginCorrect(registrationParameters.get(RegistrationParameter.LOGIN))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.LOGIN, EMPTY_VALUE);
        }
        if (!isFirstNameCorrect(registrationParameters.get(RegistrationParameter.FIRST_NAME))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.FIRST_NAME, EMPTY_VALUE);
        }
        if (!isSecondNameCorrect(registrationParameters.get(RegistrationParameter.SECOND_NAME))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.SECOND_NAME, EMPTY_VALUE);
        }
        if (!isPhoneNumberCorrect(registrationParameters.get(RegistrationParameter.PHONE_NUMBER))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.PHONE_NUMBER, EMPTY_VALUE);
        }
        if (!isPasswordCorrect(registrationParameters.get(RegistrationParameter.PASSWORD))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.PASSWORD, EMPTY_VALUE);
        }
        if (!registrationParameters.get(RegistrationParameter.PASSWORD).equals(
                registrationParameters.get(RegistrationParameter.CONFIRMED_PASSWORD))) {
            isRegistrationParametersCorrect = false;
            registrationParameters.put(RegistrationParameter.CONFIRMED_PASSWORD, EMPTY_VALUE);
        }
        return isRegistrationParametersCorrect;
    }

    /**
     * Check id for correct.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    /**
     * Check email for correct.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    /**
     * Check login for correct.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    /**
     * Check login substring for correct.
     *
     * @param loginSubstring the loginSubstring
     * @return the boolean
     */
    public static boolean isLoginSubstringCorrect(String loginSubstring) {
        return isStringCorrect(loginSubstring, LOGIN_SUBSTRING_REGEX);
    }

    /**
     * Check password for correct.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isPasswordCorrect(String email) {
        return isStringCorrect(email, PASSWORD_REGEX);
    }

    /**
     * Check first name for correct.
     *
     * @param firstName the first name
     * @return the boolean
     */
    public static boolean isFirstNameCorrect(String firstName) {
        return isStringCorrect(firstName, FIRST_NAME_REGEX);
    }

    /**
     * Check second name for correct.
     *
     * @param secondName the second name
     * @return the boolean
     */
    public static boolean isSecondNameCorrect(String secondName) {
        return isStringCorrect(secondName, SECOND_NAME_REGEX);
    }

    /**
     * Check phone number for correct.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public static boolean isPhoneNumberCorrect(String phoneNumber) {
        return isStringCorrect(phoneNumber, PHONE_NUMBER_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}
