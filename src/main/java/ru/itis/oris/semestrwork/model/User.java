package ru.itis.oris.semestrwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String groupNumber;
    private Integer roomNumber;
    private String email;
    private String passwordHash;
    private boolean administratorRights;

    public User(String firstName, String lastName, int age, String groupNumber, Integer roomNumber, String email, String passwordHash, boolean administratorRights) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.groupNumber = groupNumber;
        this.roomNumber = roomNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.administratorRights = administratorRights;
    }
}