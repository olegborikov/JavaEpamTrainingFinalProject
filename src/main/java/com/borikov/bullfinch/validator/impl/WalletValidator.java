package com.borikov.bullfinch.validator.impl;

import com.borikov.bullfinch.validator.AbstractValidator;

public class WalletValidator extends AbstractValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String ENRICH_AMOUNT_REGEX =
            "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final double MIN_BALANCE = 0.01;
    private static final double MAX_BALANCE = 99999.99;

    public boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public boolean isEnrichAmountCorrect(String enrichAmount) {
        return isStringCorrect(enrichAmount, ENRICH_AMOUNT_REGEX);
    }

    public boolean isBalanceCorrect(double balance) {
        return balance >= MIN_BALANCE && balance <= MAX_BALANCE;
    }
}
