package com.borikov.bullfinch.model.validator;

/**
 * The type Tattoo validator.
 */
public class TattooValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^[^<>]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^[^<>]{1,1000}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";

    private TattooValidator() {
    }

    /**
     * Is id correct boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    /**
     * Is name correct boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameCorrect(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    /**
     * Is description correct boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionCorrect(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX) && !description.isBlank();
    }

    /**
     * Is price correct boolean.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceCorrect(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
