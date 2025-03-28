package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;
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
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * public class TaskDto {
     *
     *     private UUID taskId;
     *     private String taskName;
     *     private UUID taskOwnerId;
     *     private UUID taskExecutorId;
     *     private TaskStatus taskStatus;
     *     private LocalDateTime taskCreateDate;
     *     private LocalDateTime taskUpdateDate;
     * }
     */

    @Override
    public Task createTask(CreateTaskDto createTaskDto) {
        Task task = taskRepository.findByTaskName((createTaskDto.getTaskName())
                .describeConstable().orElseThrow(() -> new RuntimeException("Задача не существует"));
        task.setTaskName(createTaskDto.getTaskName());
        task.getTaskOwnerId(createTaskDto.getTaskOwnerId());



        taskRepository.save(task);
    }

    @Override
    public Task updateTask(UUID taskId, String taskName, UUID taskOwnerId, UUID taskExecutorId,
                           TaskStatus taskStatus, LocalDateTime taskCreateDate, LocalDateTime taskUpdateDate) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTaskName(taskName);
        task.setTaskOwnerId(taskOwnerId);
        task.setTaskExecutorId(taskExecutorId);
        task.setTaskStatus(taskStatus);
        task.setTaskCreateDate(taskCreateDate);
        task.setTaskUpdateDate(taskUpdateDate);

        return taskRepository.save(task);
    }

}

