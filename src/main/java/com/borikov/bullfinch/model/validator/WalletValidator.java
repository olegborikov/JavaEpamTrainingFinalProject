package com.borikov.bullfinch.model.validator;

public class WalletValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String ENRICH_AMOUNT_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final double MIN_BALANCE = 0.01;
    private static final double MAX_BALANCE = 99999.99;

    private WalletValidator() {
    }

    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isEnrichAmountCorrect(String enrichAmount) {
        return isStringCorrect(enrichAmount, ENRICH_AMOUNT_REGEX);
    }

    public static boolean isBalanceCorrect(double balance) {
        return balance >= MIN_BALANCE && balance <= MAX_BALANCE;
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
