package org.abramov.spring.testovoe.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;
    private String username;
    private String userLastName;
    private String userFirstName;
}

