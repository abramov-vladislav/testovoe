package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {

    boolean existsTaskByTaskName(String taskName);

    void deleteTaskByTaskId(UUID taskId);

    List<Task> findAll(Integer pageNumber, Integer pageSize);

    List<Task> findAllByUserId(UUID userId, Integer pageNumber, Integer pageSize);

    Task findById(UUID taskId);

    Task save(Task task);
}
