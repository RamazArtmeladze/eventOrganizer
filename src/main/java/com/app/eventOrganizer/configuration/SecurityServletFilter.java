package com.app.eventOrganizer.configuration;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityServletFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean isAuthenticated = session != null && session.getAttribute("userDetails") != null;

        if (isAuthenticated) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}