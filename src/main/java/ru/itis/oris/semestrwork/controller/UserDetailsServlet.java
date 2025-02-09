package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/user")
public class UserDetailsServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int userId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("user", userService.getById(userId));

        try {
            req.getRequestDispatcher("user_detail.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
