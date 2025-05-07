package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.entity.UserTask;

import java.util.UUID;

public interface UserTaskService {

    void updateUser(UserTask userTask);

    void removeUser(UUID userId);

    boolean exists(UUID userId);
}
