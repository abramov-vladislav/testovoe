package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.List;

public interface TaskRepository {
    List<Task> getAllTasks(Integer pageNumber, Integer pageSize);
}
