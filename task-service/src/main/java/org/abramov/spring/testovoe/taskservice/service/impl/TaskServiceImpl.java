package org.abramov.spring.testovoe.taskservice.service.impl;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
    public Task getTaskByTaskName(String taskName) {
        return taskRepository.findByTaskName(taskName)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getTasksByOwner(UUID userId) {
        // Используем метод репозитория для получения задач по владельцу
        return taskRepository.findByOwnerId(userId);
    }

    @Override
    public List<Task> getTasksByExecutor(UUID userId) {
        // Используем метод репозитория для получения задач по исполнителю
        return taskRepository.findByExecutorId(userId);
    }

    @Override
    public Task createTask(Task task) {
        // Присваиваем текущую дату и время
        task.setTaskCreateDate(LocalDateTime.now());
        task.setTaskUpdateDate(LocalDateTime.now());
        return taskRepository.save(task);  // Сохраняем задачу в репозитории
    }

    @Override
    public Task updateTask(UUID taskId, Task task) {
        Task existingTask = taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskOwnerUserId(task.getTaskOwnerUserId());
        existingTask.setTaskExecutorUserId(task.getTaskExecutorUserId());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setTaskUpdateDate(LocalDateTime.now());

        return taskRepository.save(existingTask);  // Сохраняем обновленную задачу
    }

    @Override
    public Task changeTaskStatus(UUID taskId, Task.TaskStatus status) {
        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTaskStatus(status);
        task.setTaskUpdateDate(LocalDateTime.now());

        return taskRepository.save(task);  // Обновляем статус задачи
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);  // Удаляем задачу
    }

    @Override
    public User getUserById(UUID userId) {
        // Взаимодействие с UserService для получения пользователя
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();  // Получаем все задачи из репозитория
    }
}

