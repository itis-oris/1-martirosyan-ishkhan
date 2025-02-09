package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.service.ComplaintsService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submitComplaint")
public class SubmitComplaintServlet extends HttpServlet {

    ComplaintsService complaintsService = new ComplaintsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");

        String complaintText = request.getParameter("complaintText");
        complaintsService.save(user, complaintText);

        response.sendRedirect("/complaints");
    }

}
