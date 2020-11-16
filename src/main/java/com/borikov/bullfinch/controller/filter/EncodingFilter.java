package com.borikov.bullfinch.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * The {@code RoleSecurityFilter} class represents encoding filter.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (!ENCODING.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(ENCODING);
            servletResponse.setCharacterEncoding(ENCODING);
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
