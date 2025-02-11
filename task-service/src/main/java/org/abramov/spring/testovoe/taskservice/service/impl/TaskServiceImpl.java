package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        return taskRepository.getAllTasks(pageNumber, pageSize);
    }

}

