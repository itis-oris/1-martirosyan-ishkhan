package ru.itis.oris.semestrwork.repository;

import ru.itis.oris.semestrwork.model.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {

    public List<News> getByName(String name, int offset, int limit) throws SQLException {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT * FROM news WHERE title LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    News news = new News();
                    news.setId(resultSet.getInt("id"));
                    news.setTitle(resultSet.getString("title"));
                    news.setDescription(resultSet.getString("description"));
                    news.setImageUrl(resultSet.getString("image_url"));
                    newsList.add(news);
                }
            }
        }

        return newsList;
    }



    public Integer countResult(String name) throws SQLException {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT * FROM news WHERE title LIKE ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    News news = new News();
                    news.setId(resultSet.getInt("id"));
                    news.setTitle(resultSet.getString("title"));
                    news.setDescription(resultSet.getString("description"));
                    news.setImageUrl(resultSet.getString("image_url"));
                    newsList.add(news);
                }
            }
        }

        return newsList.size();
    }

    public News getById(int newsId) throws SQLException {
        String query = "SELECT * FROM news WHERE id=?";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, newsId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image_url")
                    );
                }
                else return null;
            }
        }
    }

    public void save(News news) throws SQLException {
        String insertQuery = """
                INSERT INTO news (title, description, image_url)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getDescription());
            preparedStatement.setString(3, news.getImageUrl());

            preparedStatement.executeUpdate();
        }
    }
}
