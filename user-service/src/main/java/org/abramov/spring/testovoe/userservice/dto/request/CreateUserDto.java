package org.abramov.spring.testovoe.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String userLastName;

    @NotBlank
    private String userFirstName;
}

