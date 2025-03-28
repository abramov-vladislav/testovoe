package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskByTaskId(UUID taskId);

    Task createTask(UUID taskId, String taskName, UUID taskOwnerId, UUID taskExecutorId,
                    TaskStatus taskStatus, LocalDateTime taskCreateDate, LocalDateTime taskUpdateDate);

    Task updateTask(UUID taskId, String taskName, UUID taskOwnerId, UUID taskExecutorId,
                    TaskStatus taskStatus, LocalDateTime taskCreateDate, LocalDateTime taskUpdateDate);
}