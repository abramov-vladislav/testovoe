package org.abramov.spring.testovoe.taskservice.dto.request;

import lombok.*;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    private UUID taskId;
    private String taskName;
    private UUID taskOwnerId;
    private UUID taskExecutorId;
    private TaskStatus taskStatus;
    private LocalDateTime taskCreateDate;
    private LocalDateTime taskUpdateDate;
}
