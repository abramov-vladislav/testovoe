package org.abramov.spring.testovoe.taskservice.model;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private UUID taskId;
    private String taskName;
    private UUID taskOwnerUserId; //владелец - пользователь
    private UUID taskExecutorUserId; //исполнитель - пользователь
    private TaskStatus taskStatus; //статус - значение из множества (новое, назначено, в работе, выполнено)
    private LocalDateTime taskCreateDate; //дата и время создания
    private LocalDateTime taskUpdateDate; //дата и время последнего изменения

    public enum TaskStatus {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETED
    }

}
