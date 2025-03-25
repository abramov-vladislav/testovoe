package org.abramov.spring.testovoe.taskservice.controller.dto.response;

import lombok.*;
import org.abramov.spring.testovoe.taskservice.controller.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private UUID taskId;
    private String taskName;
    private UUID taskOwnerId;
    private UUID taskExecutorId;
    private TaskStatus taskStatus;
    private LocalDateTime taskCreateDate;
    private LocalDateTime taskUpdateDate;
}
