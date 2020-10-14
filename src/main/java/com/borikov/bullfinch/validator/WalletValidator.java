package com.borikov.bullfinch.validator;

public class WalletValidator {
    private static final String ID_REGEX = "^[\\d]{1,20}$";
    private static final String ENRICH_AMOUNT_REGEX = "^[\\d]{1,5}(\\.[\\d]{0,2})?$";
    private static final double MIN_BALANCE = 0.01;
    private static final double MAX_BALANCE = 99999.99;

    public boolean isIdCorrect(String id) {
        boolean result = false;
        if (id != null) {
            result = id.matches(ID_REGEX);
        }
        return result;
    }

    public boolean isEnrichAmountCorrect(String price) {
        boolean result = false;
        if (price != null) {
            result = price.matches(ENRICH_AMOUNT_REGEX);
        }
        return result;
    }

    public boolean isBalanceCorrect(double balance) {
        return balance >= MIN_BALANCE && balance <= MAX_BALANCE;
    }
}
