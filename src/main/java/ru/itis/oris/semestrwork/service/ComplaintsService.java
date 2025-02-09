package ru.itis.oris.semestrwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.oris.semestrwork.model.Complaint;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.repository.ComplaintsRepository;

import java.sql.SQLException;
import java.util.List;

public class ComplaintsService {
    private final ComplaintsRepository complaintsRepository;
    private final static Logger logger = LogManager.getLogger(ComplaintsService.class);

    public ComplaintsService() {
        this.complaintsRepository = new ComplaintsRepository();
    }

    public List<Complaint> getByNameWithUser(String name, User user, int offset, int limit) {
        try {
            return complaintsRepository.getByNameWithUser(name, user, offset, limit);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public int countResultWithUser(String name, User user) {
        try {
            return complaintsRepository.countResultWithUser(name, user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    public void save(User user, String complaintText) {
        try {
            complaintsRepository.save(user, complaintText);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Complaint> getByName(String s, int offset, int limit) {
        try {
            return complaintsRepository.getByName(s, offset, limit);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public int countResult(String s) {
        try {
            return complaintsRepository.countResult(s);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    public void setStatus(int complaintId) {
        try {
            complaintsRepository.setStatus(complaintId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
