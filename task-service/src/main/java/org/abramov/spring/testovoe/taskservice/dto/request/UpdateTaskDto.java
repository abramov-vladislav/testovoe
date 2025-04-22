package org.abramov.spring.testovoe.taskservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {

    @NotBlank
    private String taskName;

    private UUID taskOwnerId;

    private UUID taskExecutorId;

    private TaskStatus taskStatus;

}
