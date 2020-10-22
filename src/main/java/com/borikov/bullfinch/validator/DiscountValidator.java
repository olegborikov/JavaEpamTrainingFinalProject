package com.borikov.bullfinch.validator;

public class DiscountValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String DISCOUNT_PERCENT_REGEX = "^[1-9]\\d{0,1}$";

    private DiscountValidator() {
    }

    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

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
