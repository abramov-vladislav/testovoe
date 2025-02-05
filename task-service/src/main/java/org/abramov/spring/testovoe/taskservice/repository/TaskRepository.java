package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // Найти задачу по ее идентификатору
    Optional<Task> findByTaskId(UUID taskId);

    // Найти задачу по названию задачи
    Optional<Task> findByTaskName(String taskName);

    // Найти все задачи, где пользователь является владельцем
    List<Task> findByOwnerId(UUID ownerId);

    // Найти все задачи, где пользователь является исполнителем
    List<Task> findByExecutorId(UUID executorId);
}
