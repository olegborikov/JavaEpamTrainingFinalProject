package com.borikov.bullfinch.validator;

public class TattooValidator {
    private static final String ID_REGEX = "^[\\d]{1,20}$";
    private static final String NAME_REGEX = "^[\\p{L}]{2,25}$";// TODO: 09.10.2020 add \\s
    private static final String DESCRIPTION_REGEX = "^[\\s\\p{L}\\d\\p{Punct}&&[^<>]]{1,1000}$";
    private static final String PRICE_REGEX = "^[\\d]{1,5}(\\.[\\d]{0,2})?$";

    public boolean isIdCorrect(String id) {
        boolean result = false;
        if (id != null) {
            result = id.matches(ID_REGEX);
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
