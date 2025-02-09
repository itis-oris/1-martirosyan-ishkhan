package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.semestrwork.model.News;
import ru.itis.oris.semestrwork.service.NewsService;

import java.io.IOException;

@WebServlet("/news")
public class NewsDetailServlet extends HttpServlet {
    NewsService newsService = new NewsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int newsId = Integer.parseInt(req.getParameter("id"));
        News news = newsService.getById(newsId);
        req.setAttribute("newsItem", news);

        try {
            req.getRequestDispatcher("/news_detail.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
