package org.abramov.spring.testovoe.taskservice.model;

import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    private UUID taskId;

    private String taskName;

    private UUID taskOwnerUserId; //владелец - пользователь
    private UUID taskExecutorUserId; //исполнитель - пользователь

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus; //статус - значение из множества (новое, назначено, в работе, выполнено)

    private LocalDateTime taskCreateDate; //дата и время создания
    private LocalDateTime taskUpdateDate; //дата и время последнего изменения

    public enum TaskStatus {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETED
    }

}
