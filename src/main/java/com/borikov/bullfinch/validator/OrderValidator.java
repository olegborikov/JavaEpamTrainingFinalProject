package com.borikov.bullfinch.validator;

public class OrderValidator {
    private static final String DATE_REGEX = "^(20[\\d]{2})-(0[\\d]|1[012])-(0[\\d]|1[\\d]|2[\\d]|3[01])$";
    private static final String DESCRIPTION_REGEX = "^[\\s\\p{L}\\d\\p{Punct}&&[^<>]]{1,1000}$";
    private static final String PRICE_REGEX = "^[\\d]{1,5}(\\.[\\d]{0,2})?$";

    public boolean isDateCorrect(String date) {
        boolean result = false;
        if (date != null) {
            result = date.matches(DATE_REGEX);
        }
        return result;
    }

    public boolean isDescriptionCorrect(String description) {
        boolean result = false;
        if (description != null) {
            result = description.matches(DESCRIPTION_REGEX);
        }
        return result;
    }

    public boolean isPriceCorrect(String price) {
        boolean result = false;
        if (price != null) {
            result = price.matches(PRICE_REGEX);
        }
        return result;
    }
}
