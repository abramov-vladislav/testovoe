package org.abramov.spring.testovoe.taskservice.mapper;

import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.request.UpdateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.response.TaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TaskMapper {

    static Task toTask(CreateTaskDto createTaskDto) {
        LocalDateTime now = LocalDateTime.now();

        return new Task()
                .setTaskId(UUID.randomUUID())
                .setTaskName(createTaskDto.getTaskName())
                .setTaskOwnerId(createTaskDto.getTaskOwnerId())
                .setTaskExecutorId(createTaskDto.getTaskExecutorId())
                .setTaskStatus(TaskStatus.NEW)
                .setTaskCreateDate(now.toLocalDate())
                .setTaskUpdateDate(now.toLocalDate());
    }

    static Task toTask(UUID taskId, UpdateTaskDto updateTaskDto) {
        return new Task()
                .setTaskId(taskId)
                .setTaskName(updateTaskDto.getTaskName())
                .setTaskOwnerId(updateTaskDto.getTaskOwnerId())
                .setTaskExecutorId(updateTaskDto.getTaskExecutorId())
                .setTaskStatus(updateTaskDto.getTaskStatus())
                .setTaskUpdateDate(LocalDate.now());
    }

    static TaskDto toTaskDto(Task task) {
        return new TaskDto(
                task.getTaskId(),
                task.getTaskName(),
                task.getTaskOwnerId(),
                task.getTaskExecutorId(),
                task.getTaskStatus(),
                task.getTaskCreateDate(),
                task.getTaskUpdateDate()
        );
    }
}
