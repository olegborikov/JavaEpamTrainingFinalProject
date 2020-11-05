package com.borikov.bullfinch.model.validator;

/**
 * The {@code DiscountValidator} class represents discount validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DiscountValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String DISCOUNT_PERCENT_REGEX = "^[1-9]\\d{0,1}$";

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
     * Check discount percent for correct.
     *
     * @param discountPercent the discount percent
     * @return the boolean
     */
    public static boolean isDiscountPercentCorrect(String discountPercent) {
        return isStringCorrect(discountPercent, DISCOUNT_PERCENT_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
