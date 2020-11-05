package com.borikov.bullfinch.model.validator;

/**
 * The {@code WalletValidator} class represents wallet validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class WalletValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String ENRICH_AMOUNT_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final double MIN_BALANCE = 0.01;
    private static final double MAX_BALANCE = 99999.99;

    private WalletValidator() {
    }

    /**
     * Check id for correct.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    /**
     * Check enrich amount for correct.
     *
     * @param enrichAmount the enrich amount
     * @return the boolean
     */
    public static boolean isEnrichAmountCorrect(String enrichAmount) {
        return isStringCorrect(enrichAmount, ENRICH_AMOUNT_REGEX);
    }

    /**
     * Check balance for correct.
     *
     * @param balance the balance
     * @return the boolean
     */
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
