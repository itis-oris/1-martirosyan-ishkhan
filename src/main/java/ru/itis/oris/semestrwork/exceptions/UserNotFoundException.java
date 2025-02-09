package ru.itis.oris.semestrwork.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Ошибка при получении пользователей");
    }
}
