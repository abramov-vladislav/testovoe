package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    List<Task> getAllTasks(Integer pageNumber, Integer pageSize);

    Optional<Task> getTaskByTaskId(UUID taskId);

    List<Task> getAllTasksByOwnerUserId(UUID userId, Integer pageNumber, Integer pageSize);

    List<Task> getAllTaskslByExecutorUserId(UUID userId, Integer pageNumber, Integer pageSize);

    boolean existsTaskByTaskName(String taskName);

    Task createTask(Task task);

    void deleteTaskByTaskId(UUID taskId);
}
