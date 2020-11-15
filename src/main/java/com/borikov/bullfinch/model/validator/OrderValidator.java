package com.borikov.bullfinch.model.validator;

/**
 * The {@code OrderValidator} class represents order validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class OrderValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";
    private static final String DATE_REGEX = "^(20[\\d]{2})-(0[\\d]|1[012])-(0[\\d]|1[\\d]|2[\\d]|3[01])$";
    private static final String DESCRIPTION_REGEX = "^[^<>]{1,250}$";

    private OrderValidator() {
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
     * Check price for correct.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceCorrect(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    /**
     * Check date for correct.
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean isDateCorrect(String date) {
        return isStringCorrect(date, DATE_REGEX);
    }

    /**
     * Check description for correct.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionCorrect(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX) && !description.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}
