package org.abramov.spring.testovoe.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TaskCRUDDto {

    private UUID id;                  // ID задачи
    private String name;             // Наименование
    private UUID ownerId;            // ID владельца
    private UUID executorId;         // ID исполнителя
    private TaskStatus status;       // Статус задачи
    private OperationType operation; // Тип операции: INSERT, UPDATE, DELETE

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isValid() {
        return id != null && name != null && !name.isBlank() && status != null && operation != null;
    }

    public enum TaskStatus {
        NEW, ASSIGNED, IN_PROGRESS, DONE
    }

    public enum OperationType {
        INSERT, UPDATE, DELETE
    }
}
