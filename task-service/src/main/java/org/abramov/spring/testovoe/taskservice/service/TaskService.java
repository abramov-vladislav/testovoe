package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.List;
import java.util.UUID;


public interface TaskService {
    List<Task> getAllTasks(Integer pageNumber, Integer pageSize);
    Task getTaskByTaskId(UUID taskId);

}
