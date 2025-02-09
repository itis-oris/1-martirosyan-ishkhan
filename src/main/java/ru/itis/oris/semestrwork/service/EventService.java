package ru.itis.oris.semestrwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.oris.semestrwork.model.Event;
import ru.itis.oris.semestrwork.model.User;
import ru.itis.oris.semestrwork.repository.EventsRepository;

import java.sql.SQLException;
import java.util.List;

public class EventService {
    private final EventsRepository eventsRepository;
    private final static Logger logger = LogManager.getLogger(EventService.class);

    public EventService() {
        this.eventsRepository = new EventsRepository();
    }

    public List<Event> getByName(String name, int offset, int limit) {
        try {
            return eventsRepository.getByName(name, offset, limit);
        } catch (SQLException e) {
            logger.error("Error retrieving events by name: " + name, e);
            return null;
        }
    }

    public int countResult(String name) {
        try {
            return eventsRepository.countResult(name);
        } catch (SQLException e) {
            logger.error("Error counting events by name: " + name, e);
            return 0;
        }
    }

    public Event getById(int id) {
        try {
            return eventsRepository.getById(id);
        } catch (SQLException e) {
            logger.error("Error retrieving event by id: " + id, e);
            return null;
        }
    }

    public List<Event> getByNameWithUser(String name, int offset, int limit, User user) {
        try {
            return eventsRepository.getByNameWithUser(name, offset, limit, user);
        } catch (SQLException e) {
            logger.error("Error retrieving events for user " + user.getId() + " by name: " + name, e);
            return null;
        }
    }

    public int countResultWithUser(String s, User user) {
        try {
            return eventsRepository.countResultWithUser(s, user);
        } catch (SQLException e) {
            logger.error("Error counting events for user " + user.getId() + " with name: " + s, e);
            return 0;
        }
    }

    public void add(Event event) {
        try {
            eventsRepository.add(event);
        } catch (SQLException e) {
            logger.error("Error adding event", e);
        }
    }
}
