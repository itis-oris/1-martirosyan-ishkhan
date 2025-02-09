package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.Event;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.EventService;
import ru.itis.oris.semestrwork.service.UserService;

import java.io.IOException;

@WebServlet("/event")
public class EventDetailsServlet extends HttpServlet {
    EventService eventService = new EventService();
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Event event = eventService.getById(Integer.parseInt(req.getParameter("id")));
        User user = (User) req.getSession(false).getAttribute("user");
        req.setAttribute("event", event);
        if (userService.getByEventId(event.getId()).contains(user.getId())) {
            req.setAttribute("registered", "visible");
            req.setAttribute("not_registered", "hidden");
        } else {
            req.setAttribute("registered", "hidden");
            req.setAttribute("not_registered", "visible");
        }


        try {
            req.getRequestDispatcher("/event_detail.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
