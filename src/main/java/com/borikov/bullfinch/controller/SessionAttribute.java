package com.borikov.bullfinch.controller;

/**
 * The {@code SessionAttribute} class represents session attribute.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class SessionAttribute {
    public static final String ROLE = "role";
    public static final String LOGIN = "login";
    public static final String CURRENT_LOCALE = "currentLocale";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String REQUEST_ATTRIBUTE_HANDLER = "requestAttributeHandler";

    private SessionAttribute() {
    }
}
