package com.borikov.bullfinch.model.exception;

/**
 * The {@code DaoException} class represents dao exception.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DaoException extends Exception {
    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
