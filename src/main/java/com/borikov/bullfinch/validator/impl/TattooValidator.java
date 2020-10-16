package com.borikov.bullfinch.validator.impl;

import com.borikov.bullfinch.validator.AbstractValidator;

public class TattooValidator extends AbstractValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^[^<>]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^[^<>]{1,1000}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";

    public boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public boolean isNameCorrect(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    public boolean isDescriptionCorrect(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX)
                && !description.isBlank();
    }

    public boolean isPriceCorrect(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }
}
