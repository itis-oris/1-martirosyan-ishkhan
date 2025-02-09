package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.Event;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.EventService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/create_event")
public class CreateEventServlet extends HttpServlet {

    EventService eventService = new EventService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String eventDateStr = request.getParameter("eventDate");
        String location = request.getParameter("location");
        User user = (User) request.getSession(false).getAttribute("user");
        int createdBy;

        try {
            createdBy = user.getId();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime eventDate = LocalDateTime.parse(eventDateStr, formatter);

            Event event = new Event();
            event.setTitle(title);
            event.setDescription(description);
            event.setEventDate(eventDate.toString().replace("T", " "));
            event.setLocation(location);
            event.setCreatedBy(createdBy);

            eventService.add(event);

            response.sendRedirect("/profile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
