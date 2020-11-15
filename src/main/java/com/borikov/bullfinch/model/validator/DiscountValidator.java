package com.borikov.bullfinch.model.validator;

/**
 * The {@code DiscountValidator} class represents discount validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DiscountValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String PERCENT_REGEX = "^[1-9]\\d?$";

    private DiscountValidator() {
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
     * Check percent for correct.
     *
     * @param percent the percent
     * @return the boolean
     */
    public static boolean isPercentCorrect(String percent) {
        return isStringCorrect(percent, PERCENT_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}
