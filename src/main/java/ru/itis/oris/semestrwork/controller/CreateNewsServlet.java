package ru.itis.oris.semestrwork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.itis.oris.semestrwork.model.News;
import ru.itis.oris.semestrwork.service.NewsService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/create_news")
@MultipartConfig
public class CreateNewsServlet extends HttpServlet {

    private NewsService newsService = new NewsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из формы
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Получаем файл изображения
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Имя файла

        // Указываем путь для сохранения изображения
        // Путь относительно корня проекта (webapp/static/images)
        String uploadDir = getServletContext().getRealPath("/static/images");

        // Путь для сохранения изображения
        Path filePath = Paths.get(uploadDir, fileName);

        // Сохраняем файл на сервере
        try (InputStream fileContent = filePart.getInputStream()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
        }

        // Создаем новость
        News news = new News();
        news.setTitle(title);
        news.setDescription(description);
        news.setImageUrl(fileName); // Путь к изображению для отображения в браузере

        // Сохраняем новость в базе данных
        newsService.create(news);

        // Перенаправляем на страницу новостей
        response.sendRedirect("/home");
    }
}
