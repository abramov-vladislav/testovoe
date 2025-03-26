package org.abramov.spring.testovoe.taskservice.controller.dto.model;

import lombok.Data;
import org.abramov.spring.testovoe.taskservice.controller.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class TaskDto {
    private UUID taskId;
    private String taskName;
    private UUID taskOwnerId; //владелец - пользователь
    private UUID taskExecutorId; //исполнитель - пользователь
    private TaskStatus taskStatus; //статус - значение из множества (новое, назначено, в работе, выполнено)
    private LocalDateTime taskCreateDate; //дата и время создания
    private LocalDateTime taskUpdateDate; //дата и время последнего изменения
}
