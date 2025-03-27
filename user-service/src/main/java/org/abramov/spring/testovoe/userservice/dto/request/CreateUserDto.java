package org.abramov.spring.testovoe.userservice.dto.request;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    private String username;
    private String userLastName;
    private String userFirstName;
}

