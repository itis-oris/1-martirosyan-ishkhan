package ru.itis.oris.semestrwork.repository;

import jakarta.servlet.ServletException;
import ru.itis.oris.semestrwork.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> getByName(String name, int offset, int limit) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE first_name LIKE ? OR last_name LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            statement.setInt(3, limit);
            statement.setInt(4, offset);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("group_number"),
                            rs.getObject("room_number") != null ? rs.getInt("room_number") : null,
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getBoolean("administrator_rights")
                    ));
                }
            }
        }

        return users;
    }

    public int countResult(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("group_number"),
                            rs.getObject("room_number") != null ? rs.getInt("room_number") : null,
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getBoolean("administrator_rights")
                    ));
                }
            }
        }

        return users.size();
    }

    public User getByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email=?";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("group_number"),
                            rs.getObject("room_number") != null ? rs.getInt("room_number") : null,
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getBoolean("administrator_rights")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public List<User> getByRoomNumber(int roomNumber) throws SQLException {
        String query = "SELECT * FROM users WHERE room_number=?";
        List<User> users = new ArrayList<>();
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("group_number"),
                            rs.getObject("room_number") != null ? rs.getInt("room_number") : null,
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getBoolean("administrator_rights"))
                    );
                }
                return users;
            }
        }
    }


    public void add(User user) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, age, group_number, room_number, email, password_hash, administrator_rights) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setInt(3, user.getAge());
            pstmt.setString(4, user.getGroupNumber());
            pstmt.setObject(5, user.getRoomNumber());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPasswordHash());
            pstmt.setBoolean(8, user.isAdministratorRights());
            pstmt.executeUpdate();
        }
    }

    public void registerToEvent(User user, int eventId) throws SQLException {
        String query = "INSERT INTO event_registrations(event_id, user_id) VALUES (?, ?)";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        }
    }

    public List<Integer> getByEventId(int eventId) throws SQLException {
        String query = "SELECT * FROM event_registrations WHERE event_id=?";
        List<Integer> usersId = new ArrayList<>();
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, eventId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    usersId.add(resultSet.getInt("user_id"));
                }
            }
        }

        return usersId;
    }

    public User getById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE id=?";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("group_number"),
                            rs.getObject("room_number") != null ? rs.getInt("room_number") : null,
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getBoolean("administrator_rights")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public void edit(String firstName, String lastName, int age, String groupNumber,
                     Integer roomNumber, String email, Boolean administratorRights, int id) throws SQLException {
        String updateQuery = """
                UPDATE users
                SET first_name = ?, last_name = ?, age = ?, group_number = ?, room_number = ?, email = ?, administrator_rights = ?
                WHERE id = ?
                """;

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, groupNumber);
            if (roomNumber == null) {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(5, roomNumber);
            }
            preparedStatement.setString(6, email);
            preparedStatement.setBoolean(7, administratorRights);
            preparedStatement.setInt(8, id);

            preparedStatement.executeUpdate();

        }
    }

    public void updatePassword(int id, String newPasswordHash) throws SQLException {
        String updateQuery = """
                UPDATE users
                SET password_hash = ?
                WHERE id = ?
                """;

        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, newPasswordHash);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        }
    }
}
