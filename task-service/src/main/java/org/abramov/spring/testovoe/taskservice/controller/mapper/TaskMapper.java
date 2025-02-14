package org.abramov.spring.testovoe.taskservice.controller.mapper;

import org.abramov.spring.testovoe.taskservice.controller.dto.TaskDTO;
import org.abramov.spring.testovoe.taskservice.model.Task;

public class TaskMapper {
    public static Task toTask(TaskDTO taskDTO){
        Task task = new Task();

        task.setTaskId(taskDTO.getTaskId());
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskOwnerId(taskDTO.getTaskOwnerId());
        task.setTaskExecutorId(taskDTO.getTaskExecutorId());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setTaskCreateDate(taskDTO.getTaskCreateDate());
        task.setTaskUpdateDate(taskDTO.getTaskUpdateDate());

        return task;
    }

    public static TaskDTO toTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskOwnerId(task.getTaskOwnerId());
        taskDTO.setTaskExecutorId(task.getTaskExecutorId());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setTaskCreateDate(task.getTaskCreateDate());
        taskDTO.setTaskUpdateDate(task.getTaskUpdateDate());

        return taskDTO;
    }
}
