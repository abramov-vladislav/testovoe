package org.abramov.spring.testovoe.taskservice.service;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.userservice.model.User;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    // Получить все задачи
    List<Task> getAllTasks();

    // Получить задачу по идентификатору
    Task getTaskByTaskId(UUID taskId);

    // Получить задачу по названию
    Task getTaskByTaskName(String taskName);

    // Получить все задачи, где текущий пользователь является владельцем
    List<Task> getTasksByOwner(UUID userId);

    // Получить все задачи, где текущий пользователь является исполнителем
    List<Task> getTasksByExecutor(UUID userId);

    // Создать задачу
    Task createTask(Task task);

    // Редактировать задачу
    Task updateTask(UUID taskId, Task task);

    // Изменить статус задачи
    Task changeTaskStatus(UUID taskId, Task.TaskStatus status);

    // Удалить задачу
    void deleteTask(UUID taskId);

    // Получить пользователя по его идентификатору
    User getUserById(UUID userId);
}
