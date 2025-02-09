package ru.itis.oris.semestrwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventRegistration {
    private int id;
    private int eventId;
    private int userId;
    private java.sql.Timestamp registeredAt;
}
