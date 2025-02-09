package ru.itis.oris.semestrwork.repository;

import ru.itis.oris.semestrwork.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    public Room getByNumber(int number) throws SQLException {
        String query = "SELECT * FROM rooms WHERE room_number=?";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, number);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("room_number"),
                            rs.getInt("capacity")
                    );
                }
                return null;
            }
        }
    }

    public List<Room> getByName(String name, int offset, int limit) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE room_number::TEXT LIKE ? ORDER BY room_number DESC LIMIT ? OFFSET ?";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rooms.add(new Room(
                            resultSet.getInt("room_number"),
                            resultSet.getInt("capacity")
                    ));
                }
            }
        }
        return rooms;
    }

    public int countResult(String name) throws SQLException {
        String query = "SELECT COUNT(*) FROM rooms WHERE room_number::TEXT LIKE ?";
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

    public void add(Room room) throws SQLException {
        String query = "INSERT INTO rooms (room_number, capacity) VALUES (?, ?)";
        try (Connection connection = DBWork.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setInt(2, room.getCapacity());
            statement.executeUpdate();
        }
    }
}
