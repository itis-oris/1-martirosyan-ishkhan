package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.service.ComplaintsService;

import java.io.IOException;

@WebServlet("/complaint")
public class SetComplaintStatusServlet extends HttpServlet {

    ComplaintsService complaintsService = new ComplaintsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int complaintId = Integer.parseInt(req.getParameter("id"));
        complaintsService.setStatus(complaintId);
        try {
            resp.sendRedirect("/check_complaints");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
