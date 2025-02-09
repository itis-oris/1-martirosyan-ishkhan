package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                resp.sendRedirect("home");
                return;
            }
            req.getRequestDispatcher("login.ftl").forward(req, resp);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
