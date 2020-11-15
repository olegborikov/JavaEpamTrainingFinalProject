package com.borikov.bullfinch.controller;

import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.controller.command.CommandProvider;
import com.borikov.bullfinch.model.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * The {@code FrontController} class represents front controller.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class FrontController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandName);
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        String page = command.execute(request);
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.CURRENT_PAGE, page);
        RequestAttributeHandler requestAttributeHandler = new RequestAttributeHandler();
        requestAttributeHandler.setAttributes(request);
        session.setAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER, requestAttributeHandler);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
