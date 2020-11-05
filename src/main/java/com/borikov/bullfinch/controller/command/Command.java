package com.borikov.bullfinch.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface represents command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface Command {
    /**
     * Execute command.
     *
     * @param request the request
     * @return the string containing page path
     */
    String execute(HttpServletRequest request);
}
