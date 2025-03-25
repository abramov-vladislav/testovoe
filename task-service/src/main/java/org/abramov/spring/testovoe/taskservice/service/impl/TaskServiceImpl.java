package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.enums.TaskStatus;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        return taskRepository.getAllTasks(pageNumber, pageSize);
    }

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        return taskRepository.getTaskByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task createTask(UUID taskId, String taskName, UUID taskOwnerId, UUID taskExecutorId,
                           TaskStatus taskStatus, LocalDateTime taskCreateDate, LocalDateTime taskUpdateDate) {
        Task task = new Task();

        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setTaskOwnerId(taskOwnerId);
        task.setTaskExecutorId(taskExecutorId);
        task.setTaskStatus(taskStatus);
        task.setTaskCreateDate(taskCreateDate);
        task.setTaskUpdateDate(taskUpdateDate);

        return taskRepository.createTask(task);
    }

    @Override
    public Task updateTask(UUID taskId, String taskName, UUID taskOwnerId, UUID taskExecutorId,
                           TaskStatus taskStatus, LocalDateTime taskCreateDate, LocalDateTime taskUpdateDate) {
        Task task = taskRepository.getTaskByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTaskName(taskName);
        task.setTaskOwnerId(taskOwnerId);
        task.setTaskExecutorId(taskExecutorId);
        task.setTaskStatus(taskStatus);
        task.setTaskCreateDate(taskCreateDate);
        task.setTaskUpdateDate(taskUpdateDate);

        return taskRepository.updateTask(task);
    }

}

