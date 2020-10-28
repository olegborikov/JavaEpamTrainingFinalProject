package com.borikov.bullfinch.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    private static final String CODE = "UTF-8";

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (!CODE.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(CODE);
            response.setCharacterEncoding(CODE);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
