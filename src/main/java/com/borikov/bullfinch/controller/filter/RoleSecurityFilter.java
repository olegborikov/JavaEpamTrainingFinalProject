package com.borikov.bullfinch.controller.filter;

import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.*;
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandName);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            String roleName = (String) session.getAttribute(SessionAttribute.ROLE);
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
                response.sendError(FORBIDDEN_ACCESS_ERROR_NUMBER);
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
