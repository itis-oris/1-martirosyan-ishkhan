package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.Room;
import ru.itis.oris.semestrwork.service.RoomService;

import java.io.IOException;

@WebServlet("/create_room")
public class CreateRoomServlet extends HttpServlet {

    RoomService roomService = new RoomService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setCapacity(capacity);

        roomService.add(room);

        try {
            response.sendRedirect("/rooms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
