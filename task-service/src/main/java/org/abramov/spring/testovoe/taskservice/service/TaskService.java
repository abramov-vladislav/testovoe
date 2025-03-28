package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskByTaskId(UUID taskId);

    Task createTask(CreateTaskDto createTaskDto);

    Task updateTask(Task task);
}