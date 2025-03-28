package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.exception.TaskAlreadyExistsException;
import org.abramov.spring.testovoe.taskservice.exception.TaskNotFoundException;
import org.abramov.spring.testovoe.taskservice.mapper.TaskMapper;
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
    public List<Task> getAllTasks() throws TaskNotFoundException {

        try {
            return taskRepository.findAll();
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException("Задания не найдены");
        }
    }

    @Override
    public Task getTaskByTaskId(UUID taskId) throws TaskNotFoundException {

        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задача не найдена"));
    }

    @Override
    public Task createTask(CreateTaskDto createTaskDto) throws TaskAlreadyExistsException {

        boolean taskExists = taskRepository.existsTaskByTaskName(createTaskDto.getTaskName());
        if (taskExists) {
            throw new TaskAlreadyExistsException(createTaskDto.getTaskName());
        }

        return taskRepository.save(TaskMapper.toTask(createTaskDto));
    }

    @Override
    public Task updateTask(Task task) throws TaskNotFoundException {

        Task taskExisting = taskRepository.findById(task.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException("Задачи не существует"));

        taskExisting.setTaskName(task.getTaskName());
        taskExisting.setTaskOwnerId(task.getTaskOwnerId());
        taskExisting.setTaskExecutorId(task.getTaskExecutorId());
        taskExisting.setTaskStatus(task.getTaskStatus());
        taskExisting.setTaskCreateDate(task.getTaskCreateDate());
        taskExisting.setTaskUpdateDate(task.getTaskUpdateDate());

        return taskRepository.save(taskExisting);
    }

}

