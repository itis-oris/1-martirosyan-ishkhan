package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.Room;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.RoomService;

import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {

    RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            createTemplate(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            createTemplate(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        req.setAttribute("user", user);

        String name = req.getParameter("name");
        String pageParam = req.getParameter("page");
        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

        int pageSize = 5;
        int offset = (page - 1) * pageSize;

        List<Room> rooms = roomService.getByName(name != null ? name : "", offset, pageSize);
        int totalPages = roomService.countResult(name != null ? name : "");

        if (page == 1) {
            req.setAttribute("left_visibility", "hidden");
            req.setAttribute("right_visibility", "visible");
        } else if (page == Math.ceil((double) totalPages / pageSize)) {
            req.setAttribute("left_visibility", "visible");
            req.setAttribute("right_visibility", "hidden");
        } else {
            req.setAttribute("left_visibility", "visible");
            req.setAttribute("right_visibility", "visible");
        }
        if (totalPages <= pageSize) {
            req.setAttribute("left_visibility", "hidden");
            req.setAttribute("right_visibility", "hidden");
        }

        req.setAttribute("page", page);
        req.setAttribute("name", name);
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("rooms.ftl").forward(req, resp);
    }
}
