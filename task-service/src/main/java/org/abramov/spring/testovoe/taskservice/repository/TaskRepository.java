package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Optional<Task> findByTaskId (UUID taskId);
}
