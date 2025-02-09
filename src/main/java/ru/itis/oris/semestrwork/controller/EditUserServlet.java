package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/edit_user")
public class EditUserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int userId = Integer.parseInt(req.getParameter("id"));
        User user = userService.getById(userId);
        req.setAttribute("user", user);

        try {
            req.getRequestDispatcher("edit_user.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
