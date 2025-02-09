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

@WebServlet("/register_to_event")
public class EventRegistrationServlet extends HttpServlet {
    UserService userService = new UserService();
    EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        User user = (User) req.getSession(false).getAttribute("user");
        Event event = eventService.getById(eventId);
        userService.registerToEvent(user, eventId);
        try {
            if (userService.getByEventId(event.getId()).contains(user.getId())) {
                req.setAttribute("registered", "visible");
                req.setAttribute("not_registered", "hidden");
            } else {
                req.setAttribute("registered", "hidden");
                req.setAttribute("not_registered", "visible");
            }
            req.setAttribute("event", event);
            req.getSession().setAttribute("message", "Вы успешно зарегестрировались!");

            resp.sendRedirect("/events");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
