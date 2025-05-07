package org.abramov.spring.testovoe.taskservice.repository.impl;

import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.repository.UserTaskRepository;

import java.util.UUID;

public class UserTaskRepositoryImpl implements UserTaskRepository {

    @Override
    public void updateUser(UserTask userUpdate) {

    }

    @Override
    public void deleteById(UUID userId) {

    }

    @Override
    public boolean existsById(UUID userId) {
        return false;
    }
}
