package org.abramov.spring.testovoe.taskservice.service;

import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.UUID;

public interface TaskService {
    Task getTaskByTaskId(UUID taskId);
}
