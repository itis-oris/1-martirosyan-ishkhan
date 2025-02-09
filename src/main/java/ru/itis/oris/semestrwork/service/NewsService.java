package ru.itis.oris.semestrwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.oris.semestrwork.model.News;
import ru.itis.oris.semestrwork.repository.NewsRepository;

import java.sql.SQLException;
import java.util.List;

public class NewsService {

    private final NewsRepository newsRepository;
    final static Logger logger = LogManager.getLogger(EventService.class);


    public NewsService() {
        this.newsRepository = new NewsRepository();
    }

    public List<News> getByName(String name, int offset, int limit) {
        try {
            return newsRepository.getByName(name, offset, limit);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Integer countResults(String s) {
        try {
            return newsRepository.countResult(s);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    public News getById(int newsId) {
        try {
            return newsRepository.getById(newsId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void create(News news) {
        try {
            newsRepository.save(news);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
