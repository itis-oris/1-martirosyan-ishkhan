package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/update_password")
public class UpdatePasswordServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = userService.getById(id);
        
        // Проверяем, совпадает ли текущий пароль с тем, что хранится в базе
        if (user != null && userService.checkPassword(user, currentPassword)) {
            if (newPassword.equals(confirmPassword)) {
                // Обновляем пароль
                userService.updatePassword(user, newPassword);
                try {
                    response.sendRedirect("/profile");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    request.setAttribute("error", "Новый пароль и подтверждение не совпадают.");
                    request.getRequestDispatcher("/change_password.ftl").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                request.setAttribute("error", "Неверный текущий пароль.");
                request.getRequestDispatcher("/change_password.ftl").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
