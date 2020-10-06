package com.borikov.bullfinch.validator;

public class TattooValidator {
    private static final String NAME_REGEX = "^[\\p{L}]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^[\\p{L}\\d\\p{Punct}&&[^<>]]{1,1000}$";

    public boolean isNameCorrect(String name) {
        boolean result = false;
        if (name != null) {
            result = name.matches(NAME_REGEX);
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
}
