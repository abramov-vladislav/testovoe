package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    boolean existsTaskByTaskName(String taskName);

    void deleteTaskByTaskId(UUID taskId);

    List<Task> findAll(Integer pageNumber, Integer pageSize);

    List<Task> findAllByOwnerUserId(UUID userId, Integer pageNumber, Integer pageSize);

    List<Task> findAllByExecutorUserId(UUID userId, Integer pageNumber, Integer pageSize);

    Optional<Task> getTaskByTaskId(UUID taskId);

    Task save(Task task);
}
