package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/update_user")
public class UpdateUserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String groupNumber = request.getParameter("groupNumber");
        String roomNumberParam = request.getParameter("roomNumber");
        Integer roomNumber = roomNumberParam.isEmpty() ? null : Integer.parseInt(roomNumberParam);
        String email = request.getParameter("email");
        boolean administratorRights = Boolean.parseBoolean(request.getParameter("administratorRights"));

        userService.edit(firstName, lastName, age, groupNumber, roomNumber, email, administratorRights, id);

        try {
            response.sendRedirect("/user?id=" + id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
