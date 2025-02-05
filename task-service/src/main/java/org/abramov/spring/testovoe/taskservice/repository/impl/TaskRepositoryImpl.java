package org.abramov.spring.testovoe.taskservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public Optional<Task> findByTaskId(UUID taskId) {
        return Optional.empty();
    }
}
