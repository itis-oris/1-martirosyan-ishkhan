package ru.itis.oris.semestrwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.oris.semestrwork.model.Room;
import ru.itis.oris.semestrwork.repository.RoomRepository;

import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository;
    private final static Logger logger = LogManager.getLogger(RoomService.class);

    public RoomService() {
        this.roomRepository = new RoomRepository();
    }

    public Room getByNumber(int number) {
        try {
            return roomRepository.getByNumber(number);
        } catch (SQLException e) {
            logger.error("Error retrieving room by number: " + number, e);
            return null;
        }
    }

    public List<Room> getByName(String name, int offset, int limit) {
        try {
            return roomRepository.getByName(name, offset, limit);
        } catch (SQLException e) {
            logger.error("Error retrieving rooms by name: " + name, e);
            return null;
        }
    }

    public int countResult(String name) {
        try {
            return roomRepository.countResult(name);
        } catch (SQLException e) {
            logger.error("Error counting rooms by name: " + name, e);
            return 0;
        }
    }

    public void add(Room room) {
        try {
            roomRepository.add(room);
        } catch (SQLException e) {
            logger.error("Error adding room", e);
            throw new RuntimeException(e);
        }
    }
}
