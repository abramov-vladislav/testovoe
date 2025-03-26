package org.abramov.spring.testovoe.taskservice.controller.mapper;

import io.micrometer.observation.annotation.Observed;
import org.abramov.spring.testovoe.taskservice.controller.dto.model.TaskDto;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.springframework.stereotype.Component;

@Observed
@Component
public class TaskMapper {
    public static Task toTask(TaskDto taskDto) {
        Task task = new Task();

        task.setTaskId(taskDto.getTaskId());
        task.setTaskName(taskDto.getTaskName());
        task.setTaskOwnerId(taskDto.getTaskOwnerId());
        task.setTaskExecutorId(taskDto.getTaskExecutorId());
        task.setTaskStatus(taskDto.getTaskStatus());
        task.setTaskCreateDate(taskDto.getTaskCreateDate());
        task.setTaskUpdateDate(taskDto.getTaskUpdateDate());

        return task;
    }

    public static TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setTaskId(task.getTaskId());
        taskDto.setTaskName(task.getTaskName());
        taskDto.setTaskOwnerId(task.getTaskOwnerId());
        taskDto.setTaskExecutorId(task.getTaskExecutorId());
        taskDto.setTaskStatus(task.getTaskStatus());
        taskDto.setTaskCreateDate(task.getTaskCreateDate());
        taskDto.setTaskUpdateDate(task.getTaskUpdateDate());

        return taskDto;
    }
}
