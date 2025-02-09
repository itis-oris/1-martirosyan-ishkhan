package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try{
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = userService.getByEmail(email);
            if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("home");
            } else {
                req.setAttribute("message", "Неверный логин или пароль");
                req.getRequestDispatcher("login.ftl").forward(req, resp);
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
