package com.borikov.bullfinch.controller.filter;

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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * The {@code RoleSecurityFilter} class represents role security filter.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class RoleSecurityFilter implements Filter {
    private static final int FORBIDDEN_ACCESS_ERROR_NUMBER = 403;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession();
        String commandName = httpRequest.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandName);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
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
                case GUEST -> RolePermission.GUEST.getCommands();
            };
            if (!commands.contains(command)) {
                LOGGER.log(Level.ERROR, "Role {} has no access to {} command", roleName, commandName);
                httpResponse.sendError(FORBIDDEN_ACCESS_ERROR_NUMBER);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
