package org.abramov.spring.testovoe.taskservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.mapper.TaskMapper;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        log.debug("Получение всех задач: страница={}, размер={}", pageNumber, pageSize);

        return taskRepository.getAllTasks(pageNumber, pageSize);
    }

    @Override
    public List<Task> getAllTasksByUserIdAsOwner(UUID userId, Integer pageNumber, Integer pageSize) {
        log.debug("Получение задач по userId как владелец: {}", userId);

        return taskRepository.getAllTasksByOwnerUserId(userId, pageNumber, pageSize);
    }

    @Override
    public List<Task> getAllTasksByUserIdAsExecutor(UUID userId, Integer pageNumber, Integer pageSize) {
        log.debug("Получение задач по userId как исполнитель: {}", userId);

        return taskRepository.getAllTasksByExecutorUserId(userId, pageNumber, pageSize);
    }

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        log.debug("Получение задачи по ID: {}", taskId);

        return taskRepository.getTaskByTaskId(taskId)
                .orElseThrow(() -> {

                    log.warn("Задача с ID {} не найдена", taskId);
                    return new RuntimeException("Задание не найдено");
                });
    }

    @Override
    @Transactional
    public Task createTask(CreateTaskDto createTaskDto) {
        log.info("Создание задачи: {}", createTaskDto);

        return taskRepository.createTask(TaskMapper.toTask(createTaskDto));
    }

    @Override
    public Task updateTask(Task task) {
        log.info("Обновление задачи: {}", task);

        Task taskExisting = taskRepository.getTaskByTaskId(task.getTaskId())
                .orElseThrow(() -> {
                    log.warn("Задача с ID {} не найдена для обновления", task.getTaskId());

                    return new RuntimeException("Задание не найдено");
                });

        if (!task.getTaskName().equals(taskExisting.getTaskName())
                && taskRepository.existsTaskByTaskName(task.getTaskName())) {
            log.warn("Имя задачи '{}' уже занято другой задачей", task.getTaskName());

            throw new RuntimeException("Задача с таким именем уже существует");
        }

        taskExisting
                .setTaskName(task.getTaskName())
                .setTaskOwnerId(task.getTaskOwnerId())
                .setTaskExecutorId(task.getTaskExecutorId())
                .setTaskStatus(task.getTaskStatus())
                .setTaskCreateDate(task.getTaskCreateDate())
                .setTaskUpdateDate(task.getTaskUpdateDate());

        log.info("Задача успешно обновлена: {}", taskExisting);

        return taskRepository.createTask(taskExisting);
    }


    @Override
    public void deleteTask(UUID taskId) {
        log.warn("Удаление задачи по ID: {}", taskId);

        taskRepository.deleteTaskByTaskId(taskId);
    }

}
