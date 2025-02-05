package org.abramov.spring.testovoe.taskservice.service.impl;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        return taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Получаем все задачи из репозитория
    }
}
