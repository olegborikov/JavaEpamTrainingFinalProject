package com.borikov.bullfinch.controller.filter;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.controller.command.CommandProvider;
import com.borikov.bullfinch.controller.command.RolePermission;
import com.borikov.bullfinch.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = {"/*"})
public class RoleSecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession();
        String commandName = httpRequest.getParameter(RequestParameter.COMMAND_NAME);
        Command currentCommand = CommandProvider.defineCommand(commandName);
        String roleName = (String) httpSession.getAttribute(RequestParameter.ROLE);
        UserRole userRole;
        if (roleName != null) {
            userRole = UserRole.valueOf(roleName.toUpperCase());
        } else {
            userRole = UserRole.GUEST;
        }
        Set<Command> commands = switch (userRole) {
            case USER -> RolePermission.USER.getCommands();
            case ADMIN -> RolePermission.ADMIN.getCommands();
            default -> RolePermission.GUEST.getCommands();
        };
        if (!commands.contains(currentCommand)) {
            LOGGER.log(Level.ERROR, "Role {} has no access to {} command", roleName, commandName);
            request.setAttribute(RequestParameter.ROLE_SECURITY_ERROR_MESSAGE, true);
            httpRequest.getRequestDispatcher(PagePath.MESSAGE).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
