package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.Room;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.RoomService;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/room")
public class RoomDetailServlet extends HttpServlet {

    RoomService roomService = new RoomService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int roomNumber = Integer.parseInt(req.getParameter("id"));

        List<User> users = userService.getByRoomNumber(roomNumber);
        Room room = roomService.getByNumber(roomNumber);

        req.setAttribute("room", room);
        req.setAttribute("users", users);

        try {
            req.getRequestDispatcher("/room_detail.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
