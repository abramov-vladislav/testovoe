package org.abramov.spring.testovoe.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.abramov.spring.testovoe.taskservice.controller.enums.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private UUID taskId;
    private String taskName;
    private UUID taskOwnerId; //владелец - пользователь
    private UUID taskExecutorId; //исполнитель - пользователь
    private TaskStatus taskStatus; //статус - значение из множества (новое, назначено, в работе, выполнено)
    private LocalDateTime taskCreateDate; //дата и время создания
    private LocalDateTime taskUpdateDate; //дата и время последнего изменения
}
