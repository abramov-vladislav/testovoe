package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.request.UpdateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskByTaskId(UUID taskId);

    /**
     * Использовать createTaskDto
     */
    Task createTask(CreateTaskDto createTaskDto);

    /**
     * Использовать updateTaskDto
     */
    Task updateTask(UpdateTaskDto updateTaskDto);
}