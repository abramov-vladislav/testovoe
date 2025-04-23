package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getAllTasks(Integer pageNumber, Integer pageSize);

    List<Task> getAllTasksByUserIdAsOwner(UUID userId, Integer pageNumber, Integer pageSize);

    List<Task> getAllTasksByUserIdAsExecutor(UUID userId, Integer pageNumber, Integer pageSize);

    Task getTaskByTaskId(UUID taskId);

    Task createTask(CreateTaskDto createTaskDto);

    Task updateTask(Task task);

    void deleteTask(UUID taskId);

}