package com.borikov.bullfinch.model.validator;

/**
 * The {@code TattooValidator} class represents tattoo validator.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class TattooValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^[^<>]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^[^<>]{1,1000}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";

    private TattooValidator() {
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
     * Check name for correct.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameCorrect(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
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

    /**
     * Check price for correct.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceCorrect(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}
