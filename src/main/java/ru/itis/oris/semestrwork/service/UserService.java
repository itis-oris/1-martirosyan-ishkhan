package ru.itis.oris.semestrwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final RoomService roomService = new RoomService();
    private final static Logger logger = LogManager.getLogger(UserService.class);

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getByEmail(String email) {
        try {
            return userRepository.getByEmail(email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public boolean registerUser(String firstName, String lastName, int age, String groupNumber, Integer roomNumber,
                                String email, String passwordHash, boolean administratorRights) {
        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setGroupNumber(groupNumber);
            user.setRoomNumber(roomNumber);
            user.setEmail(email);
            user.setPasswordHash(passwordHash);
            user.setAdministratorRights(administratorRights);
            if (roomService.getByNumber(roomNumber) != null &&
                    roomService.getByNumber(roomNumber).getCapacity() <= getByRoomNumber(roomNumber).size())
                return false;
            userRepository.add(user);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public void registerToEvent(User user, int eventId) {
        try {
            userRepository.registerToEvent(user, eventId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Integer> getByEventId(int eventId) {
        try {
            return userRepository.getByEventId(eventId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<User> getByRoomNumber(int roomNumber) {
        try {
            return userRepository.getByRoomNumber(roomNumber);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public int countResult(String name) {
        try {
            return userRepository.countResult(name);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    public List<User> getByName(String name, int offset, int limit) {
        try {
            return userRepository.getByName(name, offset, limit);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public User getById(int userId) {
        try {
            return userRepository.getById(userId);
        } catch (SQLException e) {
            return null;
        }
    }

    public void edit(String firstName, String lastName, int age, String groupNumber,
                     Integer roomNumber, String email, Boolean administratorRights, int id) {
        try {
            userRepository.edit(firstName, lastName, age, groupNumber, roomNumber, email, administratorRights, id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean checkPassword(User user, String currentPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(currentPassword, user.getPasswordHash());
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            userRepository.updatePassword(user.getId(), bCryptPasswordEncoder.encode(newPassword));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
