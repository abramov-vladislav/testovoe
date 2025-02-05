package org.abramov.spring.testovoe.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.userservice.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskDTO {
    private UUID taskId;
    private String taskName;
    private UserDTO taskOwner; // Владелец - пользователь
    private UserDTO taskExecutor; // Исполнитель - пользователь
    private Task.TaskStatus taskStatus;
    private LocalDateTime taskCreateDate;
    private LocalDateTime taskUpdateDate;

    // Метод для преобразования Task в TaskDto
    public static TaskDTO from(Task task, UserDTO owner, UserDTO executor) {
        return new TaskDTO(
                task.getTaskId(),
                task.getTaskName(),
                owner, // Передаем информацию о владельце
                executor, // Передаем информацию об исполнителе
                task.getTaskStatus(),
                task.getTaskCreateDate(),
                task.getTaskUpdateDate()
        );
    }
}
