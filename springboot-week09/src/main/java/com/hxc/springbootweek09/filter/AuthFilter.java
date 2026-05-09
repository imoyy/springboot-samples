package com.hxc.springbootweek09.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String auth = req.getHeader("Authorization");
        if (!"admin".equals(auth)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Unauthorized: Invalid or missing Authorization header");
            return;
        }

        System.out.println("[AuthFilter] 认证通过 -> " + req.getRequestURI());
        chain.doFilter(request, response);
    }
}
