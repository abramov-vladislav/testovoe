package org.abramov.spring.testovoe.userservice.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;
    private String username;
    private String userLastName;
    private String userFirstName;
}

