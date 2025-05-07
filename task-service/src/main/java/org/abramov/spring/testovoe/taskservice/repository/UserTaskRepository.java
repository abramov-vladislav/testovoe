package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTaskRepository {
    void updateUser(UserTask userUpdate);

    void deleteById(UUID userId);

    boolean existsById(UUID userId);
}
