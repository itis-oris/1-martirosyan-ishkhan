package ru.itis.oris.semestrwork.repository;

import ru.itis.oris.semestrwork.model.Event;
import ru.itis.oris.semestrwork.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsRepository {

    public List<Event> getByName(String name, int offset, int limit) throws SQLException {
        List<Event> eventList = new ArrayList<>();
        String query = "SELECT * FROM events WHERE title LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    eventList.add(new Event(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("event_date"),
                            resultSet.getString("location"),
                            resultSet.getInt("created_by")
                    ));
                }
            }
        }
        return eventList;
    }

    public int countResult(String name) throws SQLException {
        String query = "SELECT COUNT(*) FROM events WHERE title LIKE ?";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
                return 0;
            }
        }
    }

    public Event getById(int id) throws SQLException {
        String query = "SELECT * FROM events WHERE id=?";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("event_date"),
                            rs.getString("location"),
                            rs.getInt("created_by")
                    );
                }
                return null;
            }
        }
    }

    public List<Event> getByNameWithUser(String name, int offset, int limit, User user) throws SQLException {
        List<Event> eventList = new ArrayList<>();
        String query = """
                SELECT e.* 
                FROM events e
                JOIN event_registrations er ON e.id = er.event_id
                WHERE e.title LIKE ? AND er.user_id = ? 
                ORDER BY id DESC LIMIT ? OFFSET ?;
                """;

        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, user.getId());
            statement.setInt(3, limit);
            statement.setInt(4, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    eventList.add(new Event(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("event_date"),
                            resultSet.getString("location"),
                            resultSet.getInt("created_by")
                    ));
                }
            }
        }
        return eventList;
    }

    public int countResultWithUser(String s, User user) throws SQLException {
        String query = """
                SELECT e.* 
                FROM events e
                JOIN event_registrations er ON e.id = er.event_id
                WHERE e.title LIKE ? AND er.user_id = ?;
                """;

        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + s + "%");
            statement.setInt(2, user.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Event> eventList = new ArrayList<>();
                while (resultSet.next()) {
                    eventList.add(new Event(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("event_date"),
                            resultSet.getString("location"),
                            resultSet.getInt("created_by")
                    ));
                }
                return eventList.size();
            }
        }
    }

    public void add(Event event) throws SQLException {
        String query = "INSERT INTO events (title, description, event_date, location, created_by) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setString(3, event.getEventDate());
            statement.setString(4, event.getLocation());
            statement.setInt(5, event.getCreatedBy());

            statement.executeUpdate();
        }
    }
}
