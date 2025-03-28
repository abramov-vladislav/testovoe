package org.abramov.spring.testovoe.taskservice.mapper;

import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.request.UpdateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.response.TaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;

import java.util.UUID;

public interface TaskMapper {
    static Task toTask(CreateTaskDto createTaskDto) {
        return new Task()
                .setTaskName(createTaskDto.getTaskName())
                .setTaskOwnerId(createTaskDto.getTaskOwnerId())
                .setTaskExecutorId(createTaskDto.getTaskExecutorId())
                .setTaskStatus(createTaskDto.getTaskStatus())
                .setTaskCreateDate(createTaskDto.getTaskCreateDate())
                .setTaskUpdateDate(createTaskDto.getTaskUpdateDate());
    }

    static Task toTask(UUID taskId, UpdateTaskDto updateTaskDto) {
        return new Task()
                .setTaskId(taskId)
                .setTaskName(updateTaskDto.getTaskName())
                .setTaskOwnerId(updateTaskDto.getTaskOwnerId())
                .setTaskExecutorId(updateTaskDto.getTaskExecutorId())
                .setTaskStatus(updateTaskDto.getTaskStatus())
                .setTaskCreateDate(updateTaskDto.getTaskCreateDate())
                .setTaskUpdateDate(updateTaskDto.getTaskUpdateDate());
    }

    static TaskDto toTaskDto(Task task) {
        return new TaskDto(task.getTaskId(), task.getTaskName(), task.getTaskOwnerId(), task.getTaskExecutorId(),
                task.getTaskStatus(), task.getTaskCreateDate(), task.getTaskUpdateDate());
    }
}
