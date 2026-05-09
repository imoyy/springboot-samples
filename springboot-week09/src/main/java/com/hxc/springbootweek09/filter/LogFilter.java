package com.hxc.springbootweek09.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("[LogFilter] 请求前 -> " + req.getMethod() + " " + req.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("[LogFilter] 请求后 -> " + req.getMethod() + " " + req.getRequestURI());
    }
}
