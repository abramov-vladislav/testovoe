package org.abramov.spring.testovoe.userservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;

    @NotBlank
    private String username;

    @NotBlank
    private String userLastName;

    @NotBlank
    private String userFirstName;
}

