package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.oris.semestrwork.service.RoomService;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserService userService = new UserService();
    private final RoomService roomService = new RoomService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        String groupNumber = request.getParameter("group_number");
        Integer roomNumber = request.getParameter("room_number") != null ? Integer.parseInt(request.getParameter("room_number")) : null;

        try {
            if (userService.getByEmail(email) != null) {
                request.setAttribute("error", "Пользователь с таким email уже существует.");
                request.getRequestDispatcher("/register.ftl").forward(request, response);
                return;
            }

            String passwordHash = bCryptPasswordEncoder.encode(password);
            if (userService.registerUser(firstName, lastName, age, groupNumber, roomNumber, email, passwordHash, false)) {
                response.sendRedirect("/login");
            } else {
                request.setAttribute("error", "Ошибка при регистрации. Проверьте корректность номера комнаты.");
                request.getRequestDispatcher("/register.ftl").forward(request, response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.ftl").forward(request, response);
    }
}
