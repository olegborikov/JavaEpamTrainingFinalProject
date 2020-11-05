package com.borikov.bullfinch.controller.filter;

import com.borikov.bullfinch.controller.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code PageSecurityFilter} class represents page security filter.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class PageSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX);
    }

    @Override
    public void destroy() {
    }
}