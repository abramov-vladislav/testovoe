package org.abramov.spring.testovoe.taskservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

    @NotBlank
    private String taskName;

    private UUID taskOwnerId;

    private UUID taskExecutorId;

}
