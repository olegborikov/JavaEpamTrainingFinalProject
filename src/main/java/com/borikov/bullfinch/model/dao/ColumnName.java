package com.borikov.bullfinch.model.dao;

/**
 * The {@code ColumnName} class represents column name.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class ColumnName {
    /**
     * Constants for discount table
     */
    public static final String DISCOUNT_ID = "discount_id";
    public static final String PERCENT = "percent";

    /**
     * Constants for image table
     */
    public static final String IMAGE_ID = "image_id";
    public static final String IMAGE_NAME = "image_name";

    /**
     * Constants for role table
     */
    public static final String ROLE_NAME = "role_name";

    /**
     * Constants for tattoo table
     */
    public static final String TATTOO_ID = "tattoo_id";
    public static final String TATTOO_NAME = "tattoo_name";
    public static final String TATTOO_DESCRIPTION = "tattoo_description";
    public static final String TATTOO_PRICE = "tattoo_price";
    public static final String IS_ALLOWED = "is_allowed";
    public static final String IS_ARCHIVED = "is_archived";

    /**
     * Constants for tattoo_order table
     */
    public static final String TATTOO_ORDER_ID = "tattoo_order_id";
    public static final String TATTOO_ORDER_PRICE = "tattoo_order_price";
    public static final String DATE = "date";
    public static final String TATTOO_ORDER_DESCRIPTION = "tattoo_order_description";
    public static final String IS_CONFIRMED = "is_confirmed";

    /**
     * Constants for user_account table
     */
    public static final String USER_ACCOUNT_ID = "user_account_id";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String IS_BLOCKED = "is_blocked";
    public static final String IS_ACTIVATED = "is_activated";

    /**
     * Constants for wallet table
     */
    public static final String WALLET_ID = "wallet_id";
    public static final String BALANCE = "balance";

    private ColumnName() {
    }
}
