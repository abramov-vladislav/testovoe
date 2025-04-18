package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.exception.TaskAlreadyExistsException;
import org.abramov.spring.testovoe.taskservice.mapper.TaskMapper;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    //FIXME: Добавить обработку ошибок

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        return taskRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public List<Task> getAllTasksByUserIdAsOwner(UUID userId, Integer pageNumber, Integer pageSize) {
        return taskRepository.findAllByOwnerUserId(userId, pageNumber, pageSize);
    }

    @Override
    public List<Task> getAllTasksByUserIdAsExecutor(UUID userId, Integer pageNumber, Integer pageSize) {
        return taskRepository.findAllByExecutorUserId(userId, pageNumber, pageSize);
    }

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        return taskRepository.getTaskByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task createTask(CreateTaskDto createTaskDto) {
        boolean taskExists = taskRepository.existsTaskByTaskName(createTaskDto.getTaskName());

        if (taskExists) {
            throw new TaskAlreadyExistsException(createTaskDto.getTaskName());
        }

        return taskRepository.save(TaskMapper.toTask(createTaskDto));
    }

    @Override
    public Task updateTask(Task task) {
        Task taskExisting = taskRepository.getTaskByTaskId(task.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskExisting.setTaskName(task.getTaskName());
        taskExisting.setTaskOwnerId(task.getTaskOwnerId());
        taskExisting.setTaskExecutorId(task.getTaskExecutorId());
        taskExisting.setTaskStatus(task.getTaskStatus());
        taskExisting.setTaskCreateDate(task.getTaskCreateDate());
        taskExisting.setTaskUpdateDate(task.getTaskUpdateDate());

        return taskRepository.save(taskExisting);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteTaskByTaskId(taskId);
    }

}

