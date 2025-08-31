package com.chronosgit.expenso.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chronosgit.expenso.config.MarkerConfig;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) resp;

        try {
            chain.doFilter(req, res);
        } catch (Exception e) {
            Logger l = LoggerFactory.getLogger(GlobalExceptionFilter.class);

            String msg = e.getMessage() != null ? e.getMessage() : "Internal server error";
            l.error(MarkerConfig.ERROR, msg, e);

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String json = String.format("{\"error\":\"%s\"}", "Internal server");

            res.getWriter().write(json);
        }
    }
}