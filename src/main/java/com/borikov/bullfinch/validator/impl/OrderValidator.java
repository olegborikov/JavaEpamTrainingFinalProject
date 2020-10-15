package com.borikov.bullfinch.validator.impl;

import com.borikov.bullfinch.validator.AbstractValidator;

public class OrderValidator extends AbstractValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final String DATE_REGEX =
            "^(20[\\d]{2})-(0[\\d]|1[012])-(0[\\d]|1[\\d]|2[\\d]|3[01])$";
    private static final String DESCRIPTION_REGEX = "^.[^<>]{1,1000}$";

    public boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public boolean isPriceCorrect(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    public boolean isDateCorrect(String date) {
        return isStringCorrect(date, DATE_REGEX);
    }

    public boolean isDescriptionCorrect(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX)
                && !description.isBlank();
    }
}
