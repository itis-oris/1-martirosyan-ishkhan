package ru.itis.oris.semestrwork.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.oris.semestrwork.model.Complaint;
import ru.itis.oris.semestrwork.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintsRepository {

    private static final Logger log = LoggerFactory.getLogger(ComplaintsRepository.class);

    public List<Complaint> getByNameWithUser(String name, User user, int offset, int limit) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaints WHERE complaint_text LIKE ? AND user_id=? ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            statement.setInt(2, user.getId());
            statement.setInt(3, limit);
            statement.setInt(4, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    complaints.add(new Complaint(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("complaint_text"),
                            resultSet.getString("status")
                    ));
                }
            }
        }

        return complaints;
    }

    public int countResultWithUser(String name, User user) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaints WHERE complaint_text LIKE ? AND user_id=?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            statement.setInt(2, user.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    complaints.add(new Complaint(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("complaint_text"),
                            resultSet.getString("status")
                    ));
                }
            }
        }

        return complaints.size();
    }

    public void save(User user, String text) throws SQLException {
        String query = "INSERT INTO complaints (user_id, complaint_text) VALUES (?, ?)";
        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            statement.setString(2, text);
            statement.executeUpdate();
        }
    }

    public List<Complaint> getByName(String s, int offset, int limit) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaints WHERE complaint_text LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + s + "%");
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    complaints.add(new Complaint(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("complaint_text"),
                            resultSet.getString("status")
                    ));
                }
            }
        }

        return complaints;
    }

    public int countResult(String s) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaints WHERE complaint_text LIKE ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + s + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    complaints.add(new Complaint(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("complaint_text"),
                            resultSet.getString("status")
                    ));
                }
            }
        }

        return complaints.size();
    }

    public void setStatus(int complaintId) throws SQLException {
        String selectSql = "SELECT status FROM complaints WHERE id = ?";
        String updateSql = "UPDATE complaints SET status = ? WHERE id = ?";

        try (Connection connection = DBWork.getInstance().getConnection();
                PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            selectStatement.setInt(1, complaintId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                log.error("Жалоба с указанным ID не найдена.");
            }

            String currentStatus = resultSet.getString("status");
            String newStatus = getNextStatus(currentStatus);

            updateStatement.setString(1, newStatus);
            updateStatement.setInt(2, complaintId);

            updateStatement.executeUpdate();

        }
    }

    private String getNextStatus(String currentStatus) {
        switch (currentStatus) {
            case "В процессе":
                return "Рассмотрен";
            case "Рассмотрен":
                return "Выполнен";
            case "Выполнен":
                return "Отклонён";
            case "Отклонён":
                return "В процессе";
            default:
                throw new IllegalArgumentException("Неизвестный статус: " + currentStatus);
        }
    }
}
