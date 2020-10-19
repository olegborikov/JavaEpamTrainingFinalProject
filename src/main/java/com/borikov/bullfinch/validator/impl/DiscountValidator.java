package com.borikov.bullfinch.validator.impl;

import com.borikov.bullfinch.validator.AbstractValidator;

public class DiscountValidator extends AbstractValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String DISCOUNT_PERCENT_REGEX = "^[1-9]\\d{0,1}$";

    public boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public boolean isDiscountPercentCorrect(String discountPercent) {
        return isStringCorrect(discountPercent, DISCOUNT_PERCENT_REGEX);
    }
}
