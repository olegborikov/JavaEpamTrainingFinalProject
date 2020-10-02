package com.borikov.bullfinch.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"},
        initParams = {@WebInitParam(name = "index_path", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;
    private static final String INDEX_PATH = "index_path";

    public void init(FilterConfig config) {
        indexPath = config.getInitParameter(INDEX_PATH);
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
    }

    public void destroy() {
        indexPath = null;
    }
}
