package org.abramov.spring.testovoe.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Пользователь с username '" + username + "' уже существует");
    }
}
