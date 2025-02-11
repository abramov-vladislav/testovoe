package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    List<Task> getAllTasks(Integer pageNumber, Integer pageSize);
    Optional<Task> getTaskByTaskId(UUID taskId);
}
