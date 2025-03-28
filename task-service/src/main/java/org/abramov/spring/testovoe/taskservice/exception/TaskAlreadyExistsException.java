package org.abramov.spring.testovoe.taskservice.exception;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException(String username) {
        super("Задача с таким названием '" + username + "' уже существует");
    }
}
