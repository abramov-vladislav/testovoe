package org.abramov.spring.testovoe.taskservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {

    private String taskName;
    private UUID taskOwnerId;
    private UUID taskExecutorId;
    private TaskStatus taskStatus;
    private LocalDateTime taskCreateDate;
    private LocalDateTime taskUpdateDate;
}
