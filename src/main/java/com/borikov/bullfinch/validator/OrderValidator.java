package com.borikov.bullfinch.validator;

public class OrderValidator {
    private static final String DATE_REGEX = "";
    private static final String DESCRIPTION_REGEX = "^[\\s\\p{L}\\d\\p{Punct}&&[^<>]]{1,1000}$";

    public boolean isDescriptionCorrect(String description) {
        boolean result = false;
        if (description != null) {
            result = description.matches(DESCRIPTION_REGEX);
        }
        return result;
    }
}
