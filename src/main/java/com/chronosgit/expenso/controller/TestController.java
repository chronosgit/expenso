package com.chronosgit.expenso.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().write("{\"message\":\"Hello from controller!\"}");
    }
}
