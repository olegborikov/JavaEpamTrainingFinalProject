package com.borikov.bullfinch.model.exception;

/**
 * The {@code ServiceException} class represents service exception.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class ServiceException extends Exception {
    /**
     * Instantiates a new Service exception.
     */
    public ServiceException() {
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param cause the cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
