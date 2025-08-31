package com.chronosgit.expenso.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseSetupFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) resp;

        res.setContentType("application/json; charset=UTF-8");

        chain.doFilter(req, res);
    }
}
