package com.borikov.bullfinch.controller.filter;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.controller.command.CommandProvider;
import com.borikov.bullfinch.controller.command.CommandType;
import com.borikov.bullfinch.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE
}, urlPatterns = {"/*"})
public class GuestForwardFilter implements Filter {
    private Set<Command> commandsGuest;

    @Override
    public void init(FilterConfig config) {
        EnumSet<CommandType> commandTypesGuest = EnumSet.range(// TODO: 28.10.2020 to singleton
                CommandType.LOGIN_COMMAND, CommandType.FIND_TATTOOS_COMMAND);
        commandTypesGuest.add(CommandType.BROWSE_HOME_PAGE_COMMAND);
        commandTypesGuest.add(CommandType.SWITCH_LOCALE_COMMAND);
        commandsGuest = commandTypesGuest.stream().map(CommandType::getCommand).collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession();
        String role = (String) httpSession.getAttribute(RequestParameter.ROLE);
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        String guestRole = UserRole.GUEST.getName();
        if (guestRole.equals(role)) {
            Command command = CommandProvider.defineCommand(commandName);
            if (!(commandsGuest.contains(command))) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        commandsGuest = null;
    }
}
